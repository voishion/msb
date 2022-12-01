package com.meishubao.java8.multitask;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * CompleteFuture多任务
 *
 * @author lilu
 */
@Slf4j
public class CompleteFutureMultiTask {

    //定长10线程池
    static ExecutorService executor;

    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("多任务线程-%d").build();
        executor = new ThreadPoolExecutor(3, 100, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Data
    static class MultiTaskResult<P, R> {

        private Boolean success;
        private P param;
        private R result;
        private Throwable throwable;

        public static <P, R> MultiTaskResult<P, R> success(P param, R result) {
            MultiTaskResult<P, R> r = new MultiTaskResult<>();
            r.setSuccess(true);
            r.setParam(param);
            r.setResult(result);
            return r;
        }

        public static <P, R> MultiTaskResult<P, R> fail(P param, Throwable throwable) {
            MultiTaskResult<P, R> r = new MultiTaskResult<>();
            r.setSuccess(false);
            r.setParam(param);
            r.setThrowable(throwable);
            return r;
        }

    }

    static class TestTask implements Supplier<String> {

        private final String accountId;

        TestTask(String accountId) {
            this.accountId = accountId;
        }

        @Override
        public String get() {
            // mock finding account from database
            String startTime = DateTime.now().toString(DatePattern.NORM_DATETIME_MS_PATTERN);
            Stopwatch stopwatch = Stopwatch.createStarted();
            try {
                if (accountId.startsWith("1")) {
                    //任务1耗时3秒
                    Thread.sleep(4000);
                } else if (accountId.startsWith("2")) {
                    //任务2耗时1秒,还出错
                    Thread.sleep(2000);
                    throw new RuntimeException("出异常了");
                } else {
                    //其它任务耗时1秒
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("任务:{}, start time:{}, exec time:{}", accountId, startTime, stopwatch.stop());
            }
            return "account" + accountId;
        }
    }

    public static CompletableFuture<MultiTaskResult<String, String>> findAccount(String accountId) {
        return CompletableFuture.supplyAsync(new TestTask(accountId), executor).handle((r, throwable) -> {
            if (Objects.nonNull(throwable)) {
                log.error("任务:{}异常, error=>{}", accountId, throwable.getMessage());
                return MultiTaskResult.fail(accountId, throwable);
            } else {
                return MultiTaskResult.success(accountId, r);
            }
        });
    }

    public static List<MultiTaskResult<String, String>> batchProcess(List<String> list) {
        List<MultiTaskResult<String, String>> result = null;

        // 并行根据accountId查找对应account
        List<CompletableFuture<MultiTaskResult<String, String>>> futures = list.stream().map(CompleteFutureMultiTask::findAccount).collect(Collectors.toList());

        // 使用allOf方法来表示所有的并行任务
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        // 下面的方法可以帮助我们获得所有子任务的处理结果
        CompletableFuture<List<MultiTaskResult<String, String>>> finalResults = allFutures.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));

        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            result = finalResults.get();
            log.info("exec time:" + stopwatch.stop());
        } catch (InterruptedException | ExecutionException e) {
            log.error("error=>{}", Throwables.getStackTraceAsString(e));
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("11111");
        list.add("22222");
        list.add("33333");
        List<MultiTaskResult<String, String>> results = batchProcess(list);
        System.out.println(JSONUtil.toJsonStr(results));
    }

}

package com.meishubao.okay.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture学习
 * <ul>
 *     <li><a href="https://cloud.tencent.com/developer/article/1870268">CompletableFuture 使用介绍<a/></li>
 *     <li><a href="https://cloud.tencent.com/developer/article/1194877">Java8新的异步编程方式 CompletableFuture(一)<a/></li>
 *     <li><a href="https://cloud.tencent.com/developer/article/1194879">Java8新的异步编程方式 CompletableFuture(二)<a/></li>
 *     <li><a href="https://cloud.tencent.com/developer/article/1194905">Java8新的异步编程方式 CompletableFuture(三)<a/></li>
 * </ul>
 *
 * CompletableFuture的静态工厂方法
 * <ul>
 *     <li><code>runAsync(Runnable runnable)</code>：使用ForkJoinPool.commonPool()作为它的线程池执行异步代码</li>
 *     <li><code>runAsync(Runnable runnable, Executor executor)</code>：使用指定的thread pool执行异步代码</li>
 *     <li><code>supplyAsync(Supplier supplier)</code>：使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值</li>
 *     <li><code>supplyAsync(Supplier supplier, Executor executor)</code>：使用指定的thread pool执行异步代码，异步操作有返回值</li>
 *     <li><code>complete(T t)</code>：完成异步执行，并返回future的结果</li>
 *     <li><code>completeExceptionally(Throwable ex)</code>：异步执行不正常的结束</li>
 * </ul>
 *
 * @author lilu
 */
public class StudyCompletableFuture {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        base();
//        thenApply();
//        thenCompose();
//        thenCombine();
//        thenAcceptBoth();
//        whenComplete();
//        handle();
//        thenAccept();
//        either();
//        allOf();
//        anyOf();
//        exceptionally();
    }

    public static void base() {
        try {
            CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println("消费了，不返回"));
            future1.get();
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "消费了，要返回");
            String result = future2.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void thenApply() {
        // map
        // thenApply的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<Double> future1 = CompletableFuture.supplyAsync(() -> "10")
                .thenApply(Integer::parseInt)
                .thenApply(i->i*10.0);
        try {
            System.out.println(future1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void thenCompose() {
        // flatMap
        // thenCompose可以用于组合多个CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "100"))
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> Double.parseDouble(s)));
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void thenCombine() {
        // 组合
        // 现在有CompletableFuture<T>、CompletableFuture<U>和一个函数(T,U)->V，thenCombine就是将CompletableFuture<T>和CompletableFuture<U>变为CompletableFuture<V>。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Double> future = future1.thenCombine(future2, (s, i) -> Double.parseDouble(s + i));
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void thenAcceptBoth() {
        // thenAcceptBoth跟thenCombine类似，但是返回CompletableFuture<Void>类型。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<Void> future = future1.thenAcceptBoth(future2, (s, i) -> System.out.println(Double.parseDouble(s + i)));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void thenAccept() {
        // 纯消费(执行Action)
        // thenAccept()是只会对计算结果进行消费而不会返回任何结果的方法。
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .thenAccept(System.out::print);
    }

    public static void either() {
        // Either 表示的是两个CompletableFuture，当其中任意一个CompletableFuture计算完成的时候就会执行。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });

        CompletableFuture<Void> future = future1.acceptEither(future2, str -> System.out.println("The future is " + str));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 执行结果：The future is from future1 或者 The future is from future2。
        // 因为future1和future2，执行的顺序是随机的。
        // applyToEither 跟 acceptEither 类似。
    }

    public static void allOf() {
        // 在所有Future对象完成后结束，并返回一个future。
        // allOf()方法所返回的CompletableFuture，并不能组合前面多个CompletableFuture的计算结果。于是我们借助Java 8的Stream来组合多个future的结果。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "tony");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "tom");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "june");

        CompletableFuture.allOf(future1, future2, future3)
                // 任务 A 执行完执行 B，B 需要 A 的结果，同时任务 B 有返回值
                .thenApply(v -> Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.joining("-")))
                // 任务 A 执行完执行 B，B 需要 A 的结果，但是任务 B 不返回值
                .thenAccept(System.out::print);
    }

    public static void anyOf() {
        // 在任何一个Future对象结束后结束，并返回一个future。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future3";
        });

        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3);

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 使用anyOf()时，只要某一个future完成，就结束了。所以执行结果可能是"from future1"、"from future2"、"from future3"中的任意一个。
        // anyOf 和 acceptEither、applyToEither的区别在于，后两者只能使用在两个future中，而anyOf可以使用在多个future中。
    }

    public static void exceptionally() {
        // 只有当CompletableFuture抛出异常的时候，才会触发这个exceptionally的计算，调用function计算值。
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
                    int i = 4 / 2;
                    //i = i / 0;
                    return String.valueOf(i);
                }).exceptionally(ex -> "errorResultA")
                .thenApply(resultA -> resultA + " resultB")
                .thenApply(resultB -> resultB + " resultC")
                .thenApply(resultC -> resultC + " resultD");
        System.out.println(future.join());
    }

    public static void handle() {
        // 执行完Action可以做转换
        // 只有当CompletableFuture抛出异常的时候，才会触发这个exceptionally的计算，调用function计算值。
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "resultA")
                .thenApply(resultA -> resultA + " resultB")
                // 任务 C 抛出异常
                .thenApply(resultB -> {
                    throw new RuntimeException();
                })
                // 任务 D
                .thenApply(resultC -> resultC + " resultD")
                // 处理任意阶段的返回值或异常
                .handle((re, throwable) -> {
                    if (throwable != null) {
                        return "errorResult";
                    }
                    return re;
                });
        System.out.println(future.join());
        // handle 方法来处理任务 C 的执行结果，上面的代码中，re 和 throwable 必然有一个是 null，它们分别代表正常的执行结果和异常的情况
        // 当然，它们也可以都为 null，因为如果它作用的那个 CompletableFuture 实例没有返回值的时候，re 就是 null

        CompletableFuture<Double> future1 = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(s -> s + "100")
                .handle((s, t) -> s != null ? Double.parseDouble(s) : 0);
        try {
            System.out.println(future1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void whenComplete() {
        // 计算结果完成时的处理
        // 当CompletableFuture完成计算结果后，我们可能需要对结果进行一些处理。
        // whenComplete相似的方法是handle，handle的用法在上一篇文章中也已经介绍过
        CompletableFuture.supplyAsync(() -> "resultA")
                .thenApply(resultA -> resultA + " resultB")
                // 任务 C 抛出异常
                .thenApply(resultB -> {
                    throw new RuntimeException();
                })
                // 任务 D
                .thenApply(resultC -> resultC + " resultD")
                // 处理任意阶段的返回值或异常
                .whenComplete((re, throwable) -> {
                    if (throwable != null) {
                        System.out.println("errorResult");
                    } else {
                        System.out.println(re);
                    }
                });

        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s->s+" World")
                .thenApply(s->s+ "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete((result, throwable) -> System.out.println(result));
    }

}

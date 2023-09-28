package com.meishubao.powerjob.processors;

import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;

@Component
public class NoticeProcessors implements BasicProcessor {

    @Override
    public ProcessResult process(TaskContext taskContext) throws Exception {
        System.out.println("我被执行了，taskContext" + taskContext);
        boolean success = true;
        return new ProcessResult(success, taskContext + ": " + success);
    }

}
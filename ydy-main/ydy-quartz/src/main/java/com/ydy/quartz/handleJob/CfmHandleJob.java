package com.ydy.quartz.handleJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CfmHandleJob implements Job {

    private static Logger LOGGER = LoggerFactory.getLogger(CfmHandleJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("确认处理任务开始{}，参数为{}",System.currentTimeMillis(),context);
    }
}

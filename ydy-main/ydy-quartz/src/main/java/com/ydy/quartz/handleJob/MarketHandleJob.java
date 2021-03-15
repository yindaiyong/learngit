package com.ydy.quartz.handleJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketHandleJob implements Job {

    private static Logger LOGGER = LoggerFactory.getLogger(MarketHandleJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("处理行情任务开始{}，参数为{}",System.currentTimeMillis(),context);
    }
}

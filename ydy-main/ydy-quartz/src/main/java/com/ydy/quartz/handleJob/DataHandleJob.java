package com.ydy.quartz.handleJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DataHandleJob extends QuartzJobBean {

    private static Logger LOGGER = LoggerFactory.getLogger(DataHandleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("处理申请任务开始{}，参数为{}",System.currentTimeMillis(),context);
    }
}

package com.dexcoder.demo.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.DisallowConcurrentExecution;
import com.dexcoder.demo.model.ScheduleJob;
import com.dexcoder.demo.vo.ScheduleJobVo;

/**
 * 任务工厂类，有状态
 *
 * Created by liyd on 12/19/14.
 */
@DisallowConcurrentExecution
public class JobSyncFactory implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobSyncFactory.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOG.info("JobSyncFactory execute");

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJobVo.JOB_PARAM_KEY);

        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.ydy.quartz.handleJob;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 定时任务管理
 * @author: dy.yin 2021/3/10 9:25
 */
@Component
public class QuartzManager {

    private static Logger LOGGER = LoggerFactory.getLogger(QuartzManager.class);

    private static String JOB_GROUP_NAME = "SAMS_JOBGROUP_NAME";

    private static String TRIGGER_GROUP_NAME = "SAMS_TRIGGERGROUP_NAME";

    @Autowired
    private Scheduler scheduler;

    /**
     * 添加定时任务
     * @Title: addJob
     * @author: dy.yin 2021/3/10 9:35
     * @param: [jobName, jobClass, cron]
     * @return: void
     * @throws
     */
    public void addJob(String jobName,String triggerName, Class jobClass, String cron) {
        LOGGER.info("新增任务开始。。。。");
        try {
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP_NAME).build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, TRIGGER_GROUP_NAME);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("新增任务结束。。。。");
    }

    /**
     * 修改定时任务的时间
     * @Title: modifyJobTime
     * @author: dy.yin 2021/3/10 14:51
     * @param: [scheduler, jobName, cron]
     * @return: void
     * @throws
     */
    public void modifyJobTime(String jobName,String triggerName, String cron) {
        LOGGER.info("修改任务开始。。。。");
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerKey);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("修改任务结束。。。。");
    }

    /**
     * 删除一个任务
     * @Title: removeJob
     * @author: dy.yin 2021/3/10 14:59
     * @param: [scheduler, jobName]
     * @return: void
     * @throws
     */
    public void removeJob(String jobName) {
        LOGGER.info("删除{}任务开始。。。。",jobName);
        try {
            scheduler.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("删除{}任务结束。。。。",jobName);
    }


    /**
     * 初始化加载所有的自动化处理任务
     * @Title: init
     * @author: dy.yin 2021/3/10 15:25
     * @return: void
     * @throws
     */
    @PostConstruct
    public void init() {
        /**
         * 1、查询数据库配置任务
         * 2、循环添加任务到触发器
         * 3、界面提供修改任务执行的cron表达式（停止任务可以修改其他参数）
         * 4、启动和停止任务调用添加任务和移除任务方法
         */
        //0 0/15 14-23 * * ? *   从下午2点开始,到23点结束,每15分钟执行一次
        addJob("行情处理", "行情处理",MarketHandleJob.class,"0/1 * * * * ? * ");
        addJob("数据处理","数据处理", DataHandleJob.class,"0/10 * * * * ? * ");
        addJob("确认处理","确认处理", CfmHandleJob.class,"0/20 * * * * ? * ");
    }

}

package cn.edu.pku.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;


public class ScriptsSchedule {
	
	public static String path = "./scripts/test.py";

	public static void main(String[] args) throws SchedulerException {

//		调度定义
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

//		任务  ScriptExecute
		JobDetail jobDetail = JobBuilder.newJob(ScriptExecute.class).withIdentity("test1", "group1").build();		
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		
		Email email = new Email("ICS", "subject", "message ", null);
		jobDataMap.put("Email", email);
		jobDataMap.put("Script", path);
		
//		触发器
		SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl("simpleTrigger");

		simpleTrigger.setStartTime(new Date(System.currentTimeMillis()));
		simpleTrigger.setRepeatInterval(1000);
		simpleTrigger.setRepeatCount(10);
		
//		任务2
		JobDetail jobDetail2 = JobBuilder.newJob(ScriptExecute.class).withIdentity("test2", "group2").build();		
		JobDataMap jobDataMap2 = jobDetail2.getJobDataMap();
		
		Email email2 = new Email("test", "subject", "message ", null);
		jobDataMap2.put("Email", email2);
		jobDataMap2.put("Script", path);

//		触发器2
		SimpleTriggerImpl simpleTrigger2 = new SimpleTriggerImpl("simpleTrigger2");

		simpleTrigger2.setStartTime(new Date(System.currentTimeMillis()));
		simpleTrigger2.setRepeatInterval(2000);
		simpleTrigger2.setRepeatCount(5);
		
		
		
		

		scheduler.scheduleJob(jobDetail, simpleTrigger);
		scheduler.scheduleJob(jobDetail2, simpleTrigger2);

		scheduler.start();
		
//		终止
//		scheduler.stop();
	}

}

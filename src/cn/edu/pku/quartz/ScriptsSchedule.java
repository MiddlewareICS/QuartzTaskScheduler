package cn.edu.pku.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;


public class ScriptsSchedule {
	
	public static String path = "./scripts/test.js";

	public static void main(String[] args) throws SchedulerException {

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		JobDetail jobDetail = newJob(ScriptExecute.class).withIdentity("test1", "group1").build();
		
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Email email = new Email("zjbpoping@gmail.com", "subject", "message ", null);
		jobDataMap.put("Email", email);
		jobDataMap.put("Script", path);

		SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl("simpleTrigger");

		simpleTrigger.setStartTime(new Date(System.currentTimeMillis()));
		simpleTrigger.setRepeatInterval(5000);
		simpleTrigger.setRepeatCount(10);

		scheduler.scheduleJob(jobDetail, simpleTrigger);

		JobDetail jobDetail2 = newJob(ScriptExecute.class).withIdentity("test2", "group2").build();

		scheduler.start();
	}

}

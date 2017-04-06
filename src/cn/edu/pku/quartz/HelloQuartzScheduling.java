package cn.edu.pku.quartz;

import org.quartz.JobDetail;

import static org.quartz.JobBuilder.newJob;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class HelloQuartzScheduling {

	public static void main(String[] args) throws SchedulerException {

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		JobDetail jobDetail = newJob(HelloQuartzJob.class).withIdentity("test1", "group1").build();

		SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl("simpleTrigger");

		simpleTrigger.setStartTime(new Date(System.currentTimeMillis()));
		simpleTrigger.setRepeatInterval(5000);
		simpleTrigger.setRepeatCount(10);

		scheduler.scheduleJob(jobDetail, simpleTrigger);

		JobDetail jobDetail2 = newJob(HelloQuartzJob.class).withIdentity("test2", "group2").build();

		SimpleTriggerImpl simpleTrigger2 = new SimpleTriggerImpl("simpleTrigger2");

		simpleTrigger2.setStartTime(new Date(System.currentTimeMillis()));
		simpleTrigger2.setRepeatInterval(10000);
		simpleTrigger2.setRepeatCount(10);

		scheduler.scheduleJob(jobDetail2, simpleTrigger2);

		scheduler.start();
	}

}

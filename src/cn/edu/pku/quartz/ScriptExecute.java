package cn.edu.pku.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScriptExecute implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		
//		JobDataMap
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Email email = (Email) jobDataMap.get("Email");
		System.out.print(email.toEmail + "\n");
		String path = (String) jobDataMap.get("Script");
		System.out.println(path + "\n");
		
	}

}

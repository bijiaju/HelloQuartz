package com.beet.test.HelloQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class HelloJob implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		//打印当前的执行时间  
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前execution时间为："+sdf.format(date));		
		// 编写具体的业务逻辑
	 	System.out.println("hello world");		
		
		/*Trigger trigger = context.getTrigger();
		System.out.println("start time is "+trigger.getStartTime());		
		System.out.println("end time is "+trigger.getEndTime());		*/
		
	}

}

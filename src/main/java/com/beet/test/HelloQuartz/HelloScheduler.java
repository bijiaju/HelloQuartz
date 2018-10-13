package com.beet.test.HelloQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		//创建一个jobdetail实例，将该实例与hellojob class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1").build();
	/*	System.out.println("jobDetail的名字："+jobDetail.getKey().getName());		
		System.out.println("jobDetail的group："+jobDetail.getKey().getGroup());		
		System.out.println("jobDetail的jobclass："+jobDetail.getJobClass().getName());		*/
		
		//打印当前的执行时间  
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时间为："+sdf.format(date));		
		date.setTime(date.getTime()+3000);//开始3s后执行
		
		Date endDate = new Date();
		endDate.setTime(endDate.getTime()+6000);
		//创建一个Trigger的实例，定义改job立即执行，并且每隔2秒钟重复一次，直到永远
		CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *")).build();
		//创建schedule实例
		StdSchedulerFactory sfact = new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		Date scheduleDate = scheduler.scheduleJob(jobDetail, trigger);
		System.out.println("开始执行时间为："+sdf.format(scheduleDate));		
		//scheudle执行2s挂起
		Thread.sleep(2000);
		scheduler.standby();//挂起
		//挂起3s重新启动
		Thread.sleep(3000);
		scheduler.start();
	}

}

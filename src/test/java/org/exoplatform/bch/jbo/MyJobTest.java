package org.exoplatform.bch.jbo;

import org.exoplatform.bch.job.MyJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 * exo@exoplatform.com
 * 10/30/15
 */
public class MyJobTest {

  @Test
  public void jobScheduled_executionLongerThanRepeatInterval_nextExecutionIsDropped() throws SchedulerException, InterruptedException {
    //Given
    MyJob.executionCount = 0;

    JobDetail job = JobBuilder.newJob(MyJob.class)
        .withIdentity("dummyJobName", "group1").build();

    Trigger trigger = TriggerBuilder
        .newTrigger()
        .withIdentity("dummyTriggerName", "group1")
        .withSchedule(
            SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever().withMisfireHandlingInstructionNextWithRemainingCount())
        .build();

    Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    scheduler.start();
    scheduler.scheduleJob(job, trigger);
    //When
    //Then
    Thread.sleep(15*1000);
    assertThat(MyJob.executionCount, is(2));
  }
}

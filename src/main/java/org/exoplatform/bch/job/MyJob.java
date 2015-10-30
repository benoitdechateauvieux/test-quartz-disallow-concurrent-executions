package org.exoplatform.bch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by The eXo Platform SAS Author : eXoPlatform exo@exoplatform.com
 * 10/30/15
 */
@DisallowConcurrentExecution
public class MyJob implements Job {
  public static final int     WAIT_IN_SEC = 10;
  private static final Logger LOG         = LoggerFactory.getLogger(MyJob.class);
  public static int executionCount = 0;

  public void execute(JobExecutionContext context) throws JobExecutionException {
    LOG.info("Job starts. Current execution={}", executionCount);

    if (executionCount==0) {
      try {
        Thread.sleep(WAIT_IN_SEC * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    LOG.info("Job ends. Current execution={}", executionCount++);
  }
}

package com.github.joshelser.hbase.replication;

import org.apache.hadoop.hbase.replication.HBaseReplicationEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingReplicationEndpoint extends HBaseReplicationEndpoint {
  private static final Logger LOG = LoggerFactory.getLogger(LoggingReplicationEndpoint.class);


  @Override
  public boolean replicate(ReplicateContext replicateContext) {
    LOG.info("replicate() called");
    replicateContext.getEntries().stream().forEach((e) -> LOG.info("Replacing {} -> {}", e.getKey(), e.getEdit()));
    return true;
  }

  @Override
  public void doStart() {
    LOG.info("Starting endpoint");
    super.doStart();
  }

  @Override
  public void doStop() {
    LOG.info("Stopping endpoint");
    super.doStop();
  }

  @Override
  public boolean canReplicateToSameCluster() {
    return true;
  }
}

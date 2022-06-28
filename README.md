## HBase Logging Replication Endpoint

Creates an HBase replication endpoint which just logs all edits to replicate at INFO inside the RegionServer.

```
$ mvn package
```

Copy to your HBase installation. Repeat for all RegionServers, e.g.

```
$ cp target/hbase-logging-replication-endpoint-0.0.1-SNAPSHOT.jar $HBASE_HOME/lib/
```

Add a peer in the HBase shell to the local hbase. Find the cluster key from the HBase UI (note, `CLUSTER_KEY` is only
    required in newer versions of HBase)

```
hbase> add_peer '1', ENDPOINT_CLASSNAME => 'com.github.joshelser.hbase.replication.LoggingReplicationEndpoint',
  CLUSTER_KEY => 'localhost:2181:/hbase'
```

Configure a table for replication (via `peer_tableCFs` or the `REPLICATION_SCOPE` on the family), write some data to
that table, and watch the RegionServer log.


```
2022-06-28 16:55:46,273 INFO  [regionserver/localhost/127.0.0.1:60020.replicationSource,1.replicationSource.localhost%2C60020%2C1656449545057.default,1] replication.LoggingReplicationEndpoint: Replicating j2/881068abf2d3799180e597003b8ecf08/18 -> [#edits: 1 = <r1/f:q2/1656449745444/Put/vlen=5/seqid=0; >]
```

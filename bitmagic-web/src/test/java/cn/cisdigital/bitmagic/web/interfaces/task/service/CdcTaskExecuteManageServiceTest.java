package cn.cisdigital.bitmagic.web.interfaces.task.service;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.third.kafka.connect.ConnectorInfo;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Properties;

import static org.junit.Assert.*;

public class CdcTaskExecuteManageServiceTest extends OAuthLocalhostTest {

    @Resource
    private CdcTaskExecuteManageService cdcTaskExecuteManageService;

    @Test
    public void submitDbzTask() {
        Long taskId = 193L;
        Properties properties = cdcTaskExecuteManageService.buildDbzProperties(taskId);
        ConnectorInfo connectorInfo = cdcTaskExecuteManageService.submitDbzTask(properties);
        System.out.println(connectorInfo);
        /*
        ConnectorInfo(name=cdc_137, config={snapshot.locking.mode=minimal, connector.class=io.debezium.connector.mysql.MySqlConnector, transforms=Reroute, include.schema.changes=true, transforms.Reroute.topic.replacement=cdc_server_137_total, poll.interval.ms=1000, database.history.store.only.captured.tables.ddl=true, binlog.buffer.size=0, snapshot.lock.timeout.ms=10000, database.user=root, sanitize.field.names=false, offset.storage=org.apache.kafka.connect.storage.KafkaOffsetBackingStore, database.history.kafka.bootstrap.servers=dev-node1:9092,dev-node2:9092,dev-node3:9092, source.struct.version=v2, internal.implementation=legacy, transforms.Reroute.topic.regex=(.*)(cdc_lp)(.*), enable.time.adjuster=true, inconsistent.schema.handling.mode=fail, min.row.count.to.stream.results=1000, database.password=123@DataBench, name=cdc_137, max.batch.size=2048, connect.keep.alive=true, snapshot.mode=when_needed, connect.timeout.ms=30000, max.queue.size=8192, tasks.max=1, database.history.kafka.topic=cdc_server_137_changes.inventory, provide.transaction.metadata=false, tombstones.on.delete=true, binary.handling.mode=bytes, decimal.handling.mode=precise, snapshot.new.tables=parallel, offset.storage.partitions=1, database.history.skip.unparseable.ddl=true, table.ignore.builtin=true, bigint.unsigned.handling.mode=long, transforms.Reroute.type=io.debezium.transforms.ByLogicalTableRouter, max.queue.size.in.bytes=0, time.precision.mode=adaptive_time_microseconds, read.only=false, offset.flush.timeout.ms=5000, database.server.name=cdc_server_137, event.processing.failure.handling.mode=fail, database.port=31333, offset.flush.interval.ms=10000, database.ssl.mode=disabled, database.hostname=10.73.13.59, table.include.list=cdc_lp.test_table, include.query=false, database.include.list=cdc_lp}, tasks=[ConnectorInfo.Tasks(connector=cdc_137, task=0)], type=SOURCE)
         */
    }

    @Test
    public void stopDbzTask() {
        String taskName = "cdc_193";
        cdcTaskExecuteManageService.cancelDbzTask(taskName);
    }
}
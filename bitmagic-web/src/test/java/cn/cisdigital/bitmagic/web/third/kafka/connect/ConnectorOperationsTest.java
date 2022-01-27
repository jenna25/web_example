package cn.cisdigital.bitmagic.web.third.kafka.connect;

import cn.cisdigital.bitmagic.web.third.kafka.KafkaClient;
import cn.cisdigital.bitmagic.web.third.kafka.impl.KafkaClientImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectorOperationsTest {

    private static ConnectorOperations connectorOperations;

    private static final String CONNECTOR_NAME = "engine-test-mysql";

    @BeforeClass
    public static void init() {
        KafkaClient kafkaClient = new KafkaClientImpl(5, "http://10.73.13.51:8083");
        connectorOperations = kafkaClient.connectors();
    }

    @Test
    public void activeList() {
        final List<String> content = connectorOperations.activeList();
        // ["inventory-connector"]
        System.out.println(content);
    }

    @Test
    public void create() {
        final HashMap<String, String> props = new HashMap<>();
        props.put("name", CONNECTOR_NAME);
        props.put("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.put("tasks.max", "1");
        props.put("database.hostname", "localhost");
        props.put("database.port", "3306");
        props.put("database.user", "root");
        props.put("database.password", "123456");
        props.put("database.server.name", "mnoserver");
        props.put("database.include.list", "mno");
        props.put("table.include.list", "mno.tb_test,mno.users");
        props.put("snapshot.mode", "when_needed");
        props.put("database.history.kafka.bootstrap.servers", "10.75.20.211:9092");
        props.put("database.history.kafka.topic", "schema-changes.mno");
        props.put("transforms", "Reroute");
        props.put("transforms.Reroute.type", "io.debezium.transforms.ByLogicalTableRouter");
        props.put("transforms.Reroute.topic.regex", "(.*)");
        props.put("transforms.Reroute.topic.replacement", "mno");
        final ConnectorInfo content = connectorOperations.create(CONNECTOR_NAME, props);
        // 
        System.out.println(content);
    }

    @Test
    public void info() {
        final ConnectorInfo content = connectorOperations.info(CONNECTOR_NAME);
        // {"name":"inventory-connector","config":{"connector.class":"io.debezium.connector.mysql.MySqlConnector","database.user":"root","tasks.max":"1","database.hostname":"localhost","database.password":"123456","database.history.kafka.bootstrap.servers":"localhost:9092","database.history.kafka.topic":"schema-changes.inventory","name":"inventory-connector","database.server.name":"dbserver1","database.port":"3306","database.include.list":"mno"},"tasks":[{"connector":"inventory-connector","task":0}],"type":"source"}
        System.out.println(content);
    }

    @Test
    public void config() {
        final Map<String, String> content = connectorOperations.config(CONNECTOR_NAME);
        System.out.println(content);
    }

    @Test
    public void updateConfig() {
        final HashMap<String, String> props = new HashMap<>();
        props.put("name", CONNECTOR_NAME);
        props.put("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.put("tasks.max", "1");
        props.put("database.hostname", "localhost");
        props.put("database.port", "3306");
        props.put("database.user", "root");
        props.put("database.password", "123456");
        props.put("database.server.name", "mnoserver");
        props.put("database.include.list", "mno");
        props.put("table.include.list", "mno.tb_test,mno.users,mno.users1");
        props.put("snapshot.mode", "when_needed");
        props.put("database.history.kafka.bootstrap.servers", "10.75.20.211:9092");
        props.put("database.history.kafka.topic", "schema-changes.mno");
        props.put("transforms", "Reroute");
        props.put("transforms.Reroute.type", "io.debezium.transforms.ByLogicalTableRouter");
        props.put("transforms.Reroute.topic.regex", "(.*)");
        props.put("transforms.Reroute.topic.replacement", "mno");
        final ConnectorInfo content = connectorOperations.updateConfig(CONNECTOR_NAME, props);
        // {"name":"engine-test-mysql","config":{"connector.class":"io.debezium.connector.mysql.MySqlConnector","database.user":"root","tasks.max":"1","database.hostname":"localhost","database.password":"123456","database.history.kafka.bootstrap.servers":"10.75.20.211:9092","database.history.kafka.topic":"schema-changes.mno","name":"engine-test-mysql","database.server.name":"mno-server","database.port":"3306","database.include.list":"mno"},"tasks":[{"connector":"engine-test-mysql","task":0}],"type":"source"}
        System.out.println(content);
    }

    @Test
    public void status() {
        final ConnectorStatusInfo content = connectorOperations.status(CONNECTOR_NAME);
        // {"name":"inventory-connector","connector":{"state":"RUNNING","worker_id":"127.0.0.1:8083"},"tasks":[{"id":0,"state":"RUNNING","worker_id":"127.0.0.1:8083"}],"type":"source"}
        System.out.println(content);
    }

    @Test
    public void tasks() {
        final List<ConnectorTask> content = connectorOperations.tasks(CONNECTOR_NAME);
        // [{"id":{"connector":"inventory-connector","task":0},"config":{"connector.class":"io.debezium.connector.mysql.MySqlConnector","database.user":"root","task.class":"io.debezium.connector.mysql.MySqlConnectorTask","tasks.max":"1","database.hostname":"localhost","database.history.kafka.bootstrap.servers":"localhost:9092","database.history.kafka.topic":"schema-changes.inventory","database.password":"123456","name":"inventory-connector","database.server.name":"dbserver1","database.port":"3306","database.include.list":"mno"}}]
        System.out.println(content);
    }

    @Test
    public void taskStatus() {
        final ConnectorTaskStatus content = connectorOperations.taskStatus(CONNECTOR_NAME, 1);
        // {"id":0,"state":"RUNNING","worker_id":"127.0.0.1:8083"}
        System.out.println(content);
    }

    @Test
    public void pause() {
        connectorOperations.pause(CONNECTOR_NAME);
    }

    @Test
    public void resume() {
        connectorOperations.resume(CONNECTOR_NAME);
    }

    @Test
    public void restart() {
        connectorOperations.restart(CONNECTOR_NAME);
    }

    @Test
    public void taskRestart() {
        connectorOperations.taskRestart(CONNECTOR_NAME, 0);
    }

    @Test
    public void delete() {
        connectorOperations.delete(CONNECTOR_NAME);
    }
}
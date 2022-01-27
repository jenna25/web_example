package cn.cisdigital.bitmagic.web.third.kafka.plugins;

import cn.cisdigital.bitmagic.web.third.kafka.KafkaClient;
import cn.cisdigital.bitmagic.web.third.kafka.impl.KafkaClientImpl;
import cn.cisdigital.bitmagic.web.third.kafka.impl.PluginsOperationsImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


public class PluginsOperationsTest {

    private static PluginsOperations pluginsOperations;

    @BeforeClass
    public static void init() {
        KafkaClient kafkaClient = new KafkaClientImpl(5, "http://10.75.20.211:8083");
        pluginsOperations = kafkaClient.plugins();
    }

    @Test
    public void plugins() {
        final List<PluginsInfo> plugins = pluginsOperations.plugins();
        // [{"class":"io.debezium.connector.mysql.MySqlConnector","type":"source","version":"1.7.0.Final"},{"class":"org.apache.kafka.connect.file.FileStreamSinkConnector","type":"sink","version":"2.2.1"},{"class":"org.apache.kafka.connect.file.FileStreamSourceConnector","type":"source","version":"2.2.1"}]
        System.out.println(plugins);
    }

    @Test
    public void pluginsValidate() {
        final HashMap<String, String> props = new HashMap<>();
        props.put("name1", "engine-test-mysql22");
        props.put("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.put("tasks.max", "1");
        props.put("database.hostname", "localhost");
        props.put("database.port", "3306");
        props.put("database.user", "root");
        props.put("database.password", "123456");
        props.put("database.server.name", "mno-server");
        props.put("database.include.list", "mno");
        props.put("database.history.kafka.bootstrap.servers", "10.75.20.2111:9092");
        props.put("database.history.kafka.topic", "schema-changes.mno");
        final PluginsValidateResult plugins = pluginsOperations.pluginsValidate("source", props);
        System.out.println(plugins);
    }

    @Test
    public void name() {
        final String name = PluginsOperationsImpl.normalizedPluginName("io.debezium.connector.mysql.MySqlConnector");
        final String name1 = PluginsOperationsImpl.normalizedPluginName("io.debezium.connector.mysql.MySql");
        System.out.println(name);
        System.out.println(name1);
        final boolean b = !name.endsWith(name1);
        System.out.println(b);
    }
}
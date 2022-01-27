package cn.cisdigital.bitmagic.web.service;

import io.debezium.connector.mysql.MySqlConnector;
import io.debezium.relational.history.KafkaDatabaseHistory;
import org.apache.kafka.connect.storage.KafkaOffsetBackingStore;

import java.util.Properties;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * Description：
 *
 * @author Happy Peel
 * @version 1.0.0
 * @date 2021/10/29 下午4:48
 */
public class DebeziumMysqlPropertiesKafka {

    public static Properties genProps(int taskId) {

        // 配置
        Properties props = new Properties();
//        props.setProperty("snapshot.new.tables", "parallel");
//        props.setProperty("internal.implementation", "legacy");

        props.setProperty("signal.data.collection", "cdc_test.debezium_signal");
//
        props.setProperty("transforms", "Reroute");
        props.setProperty("transforms.Reroute.topic.replacement", "cdc_server_" + taskId + "_total");
        props.setProperty("transforms.Reroute.type", "io.debezium.transforms.ByLogicalTableRouter");
//        props.setProperty("transforms.Reroute.topic.regex", "(.*)");
        props.setProperty("transforms.Reroute.topic.regex", "(.*)(cdc_test)(.*)");


        // begin engine properties
        // 连接器的唯一名称。尝试使用相同名称再次注册失败。所有 Kafka Connect 连接器都需要此属性。
        props.setProperty("name", "cdc_test_" + taskId);
        // 连接器的 Java 类的名称。始终io.debezium.connector.mysql.MySqlConnector为 MySQL 连接器指定 。
        props.setProperty("connector.class", MySqlConnector.class.getCanonicalName());
        // 应为此连接器创建的最大任务数。MySQL 连接器始终使用单个任务，因此不使用此值，因此默认值始终是可接受的。
        // 非必填
        props.setProperty("tasks.max", "1");
//        props.setProperty("config.action.reload", "1");
        // MySQL 数据库服务器的 IP 地址或主机名。
        props.setProperty("database.hostname", "10.75.20.213");
        // MySQL 数据库服务器的整数端口号。
        props.setProperty("database.port", String.valueOf(30006));
        // 连接到 MySQL 数据库服务器时要使用的 MySQL 用户的名称。
        props.setProperty("database.user", "root");
        // 连接到 MySQL 数据库服务器时使用的密码。
        props.setProperty("database.password", "goodluck");
        // 标识并为 Debezium 捕获更改的特定 MySQL 数据库服务器/集群提供命名空间的逻辑名称。
        // 逻辑名称在所有其他连接器中应该是唯一的，因为它用作所有接收此连接器发出的事件的 Kafka 主题名称的前缀。
        // 数据库服务器逻辑名称中只能使用字母数字字符、连字符、点和下划线。
        props.setProperty("database.server.name", "cdc_server_" + taskId);
        // 此数据库客户端的数字 ID，在 MySQL 集群中所有当前运行的数据库进程中必须是唯一的。
        // 此连接器作为另一台服务器（具有此唯一 ID）加入 MySQL 数据库集群，以便它可以读取 binlog。默认情况下，会生成 5400 到 6400 之间的随机数，但建议明确设置一个值。
        // 非必填
//        props.setProperty("database.server.id", "100");
        // 一个可选的、以逗号分隔的正则表达式列表，与要为其捕获更改的数据库的名称相匹配。
        // 连接器不会捕获名称不在database.include.list. 默认情况下，连接器捕获所有数据库中的更改。
        // 不要同时设置database.exclude.list连接器配置属性。
//        props.setProperty("database.include.list", "cdc_test,cdc-jh");
//        props.setProperty("database.exclude.list", "when_needed");
        // 一个可选的、以逗号分隔的正则表达式列表，与您要捕获其更改的表的完全限定表标识符相匹配。
        // 连接器不会捕获任何未包含在table.include.list.
        // 每个标识符的格式为databaseName。表名。默认情况下，连接器会捕获每个数据库中每个非系统表中的更改，这些更改正在被捕获。不
        // 要同时指定table.exclude.list连接器配置属性。
        props.setProperty("table.include.list", "wl_test.customers1,cdc-jh.table2,cdc_test.customers2,cdc_test.www,cdc_test.debezium_signal");

        // column相关配置省略: https://debezium.io/documentation/reference/1.7/connectors/mysql.html#mysql-connector-properties

        // 时间、日期和时间戳可以用不同类型的精度表示，包括：
        //adaptive_time_microseconds（默认）根据数据库列的类型使用毫秒、微秒或纳秒精度值捕获与数据库中完全相同的日期、日期时间和时间戳值，但 TIME 类型的字段除外，它们总是以微秒为单位捕获。
        //adaptive（已弃用）根据数据库列的类型使用毫秒、微秒或纳秒精度值准确地捕获数据库中的时间和时间戳值。
        //connect 总是使用 Kafka Connect 内置的时间、日期和时间戳表示来表示时间和时间戳值，无论数据库列的精度如何，它们都使用毫秒精度。
        props.setProperty("time.precision.mode", "adaptive_time_microseconds");
        // 指定连接器应如何处理DECIMAL和NUMERIC列的值：
        //precise（默认）使用java.math.BigDecimal二进制形式的更改事件中表示的值精确表示它们。
        //double 使用double值表示它们，这可能会导致精度损失，但更易于使用。
        //string 将值编码为格式化字符串，这很容易使用，但会丢失有关真实类型的语义信息。
        props.setProperty("decimal.handling.mode", "precise");
        // 指定 BIGINT UNSIGNED 列应如何在更改事件中表示。可能的设置有：
        //long 使用 Java 的 表示值long，这可能无法提供精确度，但在消费者中易于使用。long通常是首选设置。
        //precise 用于java.math.BigDecimal表示值，这些值通过使用二进制表示和 Kafka Connect 的org.apache.kafka.connect.data.Decimal类型在更改事件中编码。
        // 在处理大于 2^63 的值时使用此设置，因为这些值无法通过使用long.
        props.setProperty("bigint.unsigned.handling.mode", "long");
        // 指定如何二进制列，例如blob，binary，varbinary，应在变更事件来表示。可能的设置：
        //bytes 将二进制数据表示为字节数组。
        //base64 将二进制数据表示为 base64 编码的字符串。
        //hex 将二进制数据表示为十六进制编码 (base16) 字符串。
        props.setProperty("binary.handling.mode", "bytes");
        // 布尔值，指定连接器是否应将数据库架构中的更改发布到与数据库服务器 ID 同名的 Kafka 主题。
        // 使用包含数据库名称且其值包含 DDL 语句的键来记录每个模式更改。这与连接器内部记录数据库历史(database.history)的方式无关。
        props.setProperty("include.schema.changes", "true");
        // 布尔值，指定连接器是否应包括生成更改事件的原始 SQL 查询。
        //如果您将此选项设置为 ，true那么您还必须将该binlog_rows_query_log_events选项设置为 来配置 MySQL ON。当include.query是 时true，对于快照进程生成的事件不存在查询。
        //设置include.query为true可能会公开通过在更改事件中包含原始 SQL 语句而显式排除或屏蔽的表或字段。因此，默认设置为false。
        props.setProperty("include.query", "false");
        // 指定连接器在二进制日志事件反序列化期间应如何对异常做出反应。
        //fail 传播异常，指示有问题的事件及其二进制日志偏移，并导致连接器停止。
        //warn 记录有问题的事件及其二进制日志偏移，然后跳过该事件。
        //ignore 传递有问题的事件并且不记录任何内容。
        props.setProperty("event.processing.failure.handling.mode", "fail");
        // 指定连接器应如何对与内部架构表示中不存在的表相关的二进制日志事件做出反应。即内部表示与数据库不一致。
        //fail 抛出一个异常，指示有问题的事件及其二进制日志偏移，并导致连接器停止。
        //warn 记录有问题的事件及其二进制日志偏移量并跳过该事件。
        //skip 传递有问题的事件并且不记录任何内容。
        props.setProperty("inconsistent.schema.handling.mode", "fail");
        // 正整数值，指定阻塞队列的最大大小，从数据库日志读取的更改事件在写入 Kafka 之前放置到该队列中。
        // 例如，当写入 Kafka 很慢或 Kafka 不可用时，此队列可以为 binlog 读取器提供背压。
        // 出现在队列中的事件不包括在此连接器定期记录的偏移量中。默认为 8192，并且应始终大于max.batch.size属性指定的最大批量大小。
        props.setProperty("max.queue.size", "8192");
        // 指定在此连接器的每次迭代期间应处理的每批事件的最大大小的正整数值。默认为 2048。
        props.setProperty("max.batch.size", "2048");
        // 阻塞队列的最大大小（以字节为单位）的 Long 值。该功能默认是禁用的，如果它设置为正的 long 值，它将被激活。
        props.setProperty("max.queue.size.in.bytes", "0");
        // 正整数值，指定连接器在开始处理一批事件之前应等待新更改事件出现的毫秒数。默认为 1000 毫秒或 1 秒。
        props.setProperty("poll.interval.ms", "1000");
        // 正整数值，指定此连接器在尝试连接到 MySQL 数据库服务器后超时前应等待的最长时间（以毫秒为单位）。默认为 30 秒。
        props.setProperty("connect.timeout.ms", "30000");

        // gtid 事务相关配置省略: https://debezium.io/documentation/reference/1.7/connectors/mysql.html#mysql-connector-properties

        // 控制删除事件后是否跟随逻辑删除事件。
        //true- 删除操作由删除事件和随后的逻辑删除事件表示。
        //false- 仅发出删除事件。
        //删除源记录后，发出墓碑事件（默认行为）允许 Kafka 完全删除与已删除行的键相关的所有事件，以防为主题启用日志压缩。
        props.setProperty("tombstones.on.delete", "true");

        // 一个布尔值，指定是否应使用单独的线程来确保与 MySQL 服务器/集群的连接保持活动状态。
        props.setProperty("connect.keep.alive", "true");
        // 一个布尔值，指定是否应忽略内置系统表。无论表包含和排除列表如何，这都适用。默认情况下，系统表被排除在捕获其更改之外，并且在对任何系统表进行更改时不会生成任何事件。
        props.setProperty("table.ignore.builtin", "true");
        // 指定是否使用加密连接。可能的设置有：
        //disabled 指定使用未加密的连接。
        //preferred 如果服务器支持安全连接，则建立加密连接。如果服务器不支持安全连接，则回退到未加密的连接。
        //required 如果由于任何原因无法建立连接，则建立加密连接或失败。
        //verify_ca 行为类似于required但另外它会根据配置的证书颁发机构 (CA) 证书验证服务器 TLS 证书，如果服务器 TLS 证书与任何有效的 CA 证书不匹配，则会失败。
        //verify_identity 行为类似于verify_ca但另外验证服务器证书是否与远程连接的主机匹配。
        props.setProperty("database.ssl.mode", "disabled");
        // 二进制日志阅读器使用的前瞻缓冲区的大小。默认设置为0禁用缓冲。
        //在特定情况下，MySQL binlog 中可能包含一条ROLLBACK语句完成的未提交数据。典型的例子是在单个事务中使用保存点或混合临时和常规表更改。
        //当检测到交易开始时，Debezium 尝试前滚 binlog 位置并找到COMMIT或ROLLBACK因此它可以确定是否从事务中流式传输更改。binlog 缓冲区的大小定义了 Debezium 在搜索事务边界时可以缓冲的事务中的最大更改数。如果事务的大小大于缓冲区，则 Debezium 必须倒带并重新读取流式传输时未放入缓冲区的事件。
        //注意：此功能正在孵化中。鼓励反馈。预计此功能还没有完全完善。
//        props.setProperty("binlog.buffer.size", "0");
        // 指定连接器启动时运行快照的条件。可能的设置有：
        //initial- 连接器仅在没有为逻辑服务器名称记录偏移量时才运行快照。
        //initial_only- 连接器仅在没有为逻辑服务器名称记录偏移量时才运行快照，然后停止；即它不会从二进制日志中读取更改事件。
        //when_needed- 连接器在它认为必要时在启动时运行快照。也就是说，当没有可用的偏移量时，或者当先前记录的偏移量指定了服务器中不可用的 binlog 位置或 GTID 时。
        //never- 连接器从不使用快照。首次使用逻辑服务器名称启动时，连接器从二进制日志的开头读取。请谨慎配置此行为。只有当binlog保证包含数据库的整个历史时才有效。
        //schema_only- 连接器运行模式的快照，而不是数据。当您不需要主题包含一致的数据快照但需要它们仅包含自连接器启动以来的更改时，此设置很有用。
        //schema_only_recovery- 这是已捕获更改的连接器的恢复设置。当您重新启动连接器时，此设置将启用损坏或丢失的数据库历史主题的恢复。您可能会定期将其设置为“清理”一个意外增长的数据库历史主题。数据库历史主题需要无限保留。
        props.setProperty("snapshot.mode", "when_needed");
//        props.setProperty("snapshot.mode", "initial");

        // 控制连接器是否持有全局 MySQL 读锁以及持有多长时间，以防止在连接器执行快照时对数据库进行任何更新。可能的设置有：
        //minimal- 连接器仅对快照的初始部分持有全局读取锁定，在此期间连接器读取数据库架构和其他元数据。快照中的剩余工作涉及从每个表中选择所有行。连接器可以通过使用 REPEATABLE READ 事务以一致的方式执行此操作。即使不再持有全局读锁并且其他 MySQL 客户端正在更新数据库，情况也是如此。
        //minimal_percona- 连接器持有全局备份锁仅针对快照的初始部分，在此期间连接器读取数据库架构和其他元数据。快照中的剩余工作涉及从每个表中选择所有行。连接器可以通过使用 REPEATABLE READ 事务以一致的方式执行此操作。即使不再持有全局备份锁并且其他 MySQL 客户端正在更新数据库，情况也是如此。此模式不会将表刷新到磁盘，不会被长时间运行的读取阻塞，并且仅在 Percona Server 中可用。
        //extended- 在快照期间阻止所有写入。如果有客户端正在提交 MySQL 从 REPEATABLE READ 语义中排除的操作，请使用此设置。
        //none- 防止连接器在快照期间获取任何表锁。虽然所有快照模式都允许使用此设置，但当且仅当快照运行时没有发生架构更改时，才可以安全使用。对于使用 MyISAM 引擎定义的表，尽管在 MyISAM 获取表锁时设置了此属性，但这些表仍将被锁定。这种行为与 InnoDB 引擎不同，后者获取行级锁。
        props.setProperty("snapshot.locking.mode", "minimal");
        // 该模式的匹配名称中指定正则表达式的一个可选的，用逗号分隔的列表table.include.list，而您想要采取快照。默认: table.include.list中指定的所有表
//        props.setProperty("snapshot.include.collection.list", "minimal");
        // 指定要包含在快照中的表行。如果您希望快照仅包含表中行的子集，请使用该属性。此属性仅影响快照。它不适用于连接器从日志中读取的事件。
        //该属性包含格式为 的全限定表名称的逗号分隔列表<databaseName>.<tableName>。例如，
        //"snapshot.select.statement.overrides": "inventory.products,customers.orders"
        //对于列表中的每个表，添加一个进一步的配置属性，指定SELECT连接器在获取快照时在表上运行的语句。指定的SELECT语句确定要包含在快照中的表行的子集。使用以下格式指定此SELECT语句属性的名称：. 例如， 。 例子：
        //snapshot.select.statement.overrides.<databaseName>.<tableName>snapshot.select.statement.overrides.customers.orders
        //如果您希望快照仅包含未软删除的记录，请从customers.orders包含软删除列的表中delete_flag添加以下属性：
        //"snapshot.select.statement.overrides": "customer.orders",
        //"snapshot.select.statement.overrides.customer.orders": "SELECT * FROM [customers].[orders] WHERE delete_flag = 0 ORDER BY id DESC"
        //在生成的快照中，连接器仅包含delete_flag = 0.
//        props.setProperty("snapshot.select.statement.overrides", "minimal");
        // 在快照期间，连接器查询连接器配置为捕获更改的每个表。连接器使用每个查询结果生成一个读取事件，该事件包含该表中所有行的数据。
        // 此属性确定 MySQL 连接器是将表的结果放入内存（速度快但需要大量内存）还是流式传输结果（速度较慢但适用于非常大的表）。此属性的设置指定在连接器传输结果之前表必须包含的最小行数。
        //要在快照期间跳过所有表大小检查并始终流式传输所有结果，请将此属性设置为0。
        props.setProperty("min.row.count.to.stream.results", "1000");
        // 建立到数据库的 JDBC 连接（而不是读取事务日志的连接）时要执行的 SQL 语句的分号分隔列表。要将分号指定为 SQL 语句中的字符而不是分隔符，请使用两个分号 ( ;;)。
        //连接器可能会自行决定建立 JDBC 连接，因此此属性仅用于配置会话参数。它不是用于执行 DML 语句。
//        props.setProperty("database.initial.statements", "");
        // 连接器启动时执行快照之前连接器应等待的时间间隔（以毫秒为单位）。如果您在集群中启动多个连接器，此属性可用于避免快照中断，这可能会导致连接器重新平衡。
//        props.setProperty("snapshot.delay.ms", "");
        // 在快照期间，连接器分批读取表内容。此属性指定批处理中的最大行数。
//        props.setProperty("snapshot.fetch.size", "");
        // 指定执行快照时等待获取表锁的最长时间（以毫秒为单位）的正整数。如果连接器无法在此时间间隔内获取表锁，则快照将失败。见MySQL的连接器如何进行数据库快照。
        props.setProperty("snapshot.lock.timeout.ms", "10000");
        // 布尔值，指示连接器是否将 2 位年份规范转换为 4 位数字。false当转换完全委托给数据库时设置为。
        //MySQL 允许用户插入 2 位或 4 位的年份值。对于 2 位值，该值将映射到 1970 - 2069 范围内的年份。默认行为是连接器进行转换。
        props.setProperty("enable.time.adjuster", "true");
        // sourceDebezium 事件中块的模式版本。Debezium 0.10 对source块的结构进行了一些重大更改，以统一所有连接器的暴露结构。
        //通过将此选项设置为v1，可以生成早期版本中使用的结构。但是，不建议使用此设置，并计划在未来的 Debezium 版本中删除此设置。
        props.setProperty("source.struct.version", "v2");
        // 指示是否对字段名称进行清理以符合Avro 命名要求。true如果连接器配置将key.converter或value.converter属性设置为 Avro 转换器。false如果不。
        props.setProperty("sanitize.field.names", "false");
        // 流期间要跳过的以逗号分隔的操作类型列表。以下值是可能的：c用于插入/创建、u用于更新、d用于删除。默认情况下，不跳过任何操作。
//        props.setProperty("skipped.operations", "");
        // 用于向连接器发送信号的数据集合的完全限定名称。名称格式为database-name.table-name。
//        props.setProperty("signal.data.collection", "");
        // 切换到替代增量快照水印实现，以避免写入信号数据收集
        props.setProperty("read.only", "false");
        // 确定连接器是否生成具有事务边界的事件并使用事务元数据丰富更改事件信封。指定true是否希望连接器执行此操作。有关详细信息，请参阅交易元数据。
        props.setProperty("provide.transaction.metadata", "false");

        // @see: io.debezium.embedded.EmbeddedEngine
        // 负责连接器偏移持久性的 Java 类的名称。它必须实现<…>.OffsetBackingStore接口。
//        props.setProperty("offset.storage", FileOffsetBackingStore.class.getCanonicalName());
        props.setProperty("offset.storage", KafkaOffsetBackingStore.class.getCanonicalName());
        // 要存储偏移量的文件路径。在需要时offset.storage被设置为<…>.FileOffsetBackingStore。
//        props.setProperty("offset.storage.file.filename", "./storage/offset.dat");

        // 要存储偏移量的 Kafka 主题的名称。在需要时offset.storage被设置为<…>.KafkaOffsetBackingStore。
        props.setProperty("offset.storage.topic", "cdc_server_" + taskId + "_offset");
        // 创建偏移存储主题时使用的分区数。在需要时offset.storage被设置为<…>.KafkaOffsetBackingStore。
        props.setProperty("offset.storage.partitions", "1");
        // 创建偏移存储主题时使用的复制因子。在需要时offset.storage被设置为<…>.KafkaOffsetBackingStore。
        props.setProperty("offset.storage.replication.factor", "1");
        // 提交策略的 Java 类的名称。它定义了何时必须根据处理的事件数量和自上次提交以来经过的时间触发偏移提交。这个类必须实现接口<…>.OffsetCommitPolicy。默认值是基于时间间隔的定期提交策略。
        props.setProperty("offset.commit.policy", "PeriodicCommitOffsetPolicy");

        // 尝试提交偏移量的时间间隔。默认值为 1 分钟。
        props.setProperty("offset.flush.interval.ms", "10000");
        // 在取消进程和恢复要在未来尝试提交的偏移数据之前，等待记录刷新和分区偏移数据提交到偏移存储的最大毫秒数。默认值为 5 秒。
        props.setProperty("offset.flush.timeout.ms", "5000");

        // see: io.debezium.relational.history.DatabaseHistory

        // 连接器存储数据库架构历史的 Kafka 主题的全名。
        props.setProperty("database.history.kafka.topic", "cdc_server_" + taskId + "_history");
        // 连接器用于建立与 Kafka 集群的初始连接的主机/端口对列表。此连接用于检索连接器先前存储的数据库架构历史记录，并用于编写从源数据库读取的每个 DDL 语句。
        // 每对都应该指向 Kafka Connect 进程使用的同一个 Kafka 集群。
        props.setProperty("database.history.kafka.bootstrap.servers", "dev-node1:9092,dev-node2:9092,dev-node3:9092");
        // 一个整数值，指定连接器在启动/恢复期间轮询持久数据时应等待的最大毫秒数。默认值为 100 毫秒。
//        props.setProperty("database.history.kafka.recovery.poll.interval.ms", "100");
        // 在连接器恢复失败并出现错误之前，连接器应尝试读取持久历史数据的最大次数。未收到数据后等待的最长时间为recovery.attemptsx recovery.poll.interval.ms。
//        props.setProperty("database.history.kafka.recovery.attempts", "4");
        // 负责持久化数据库历史(指的是schema变更)的 Java 类的名称。它必须实现<…>.DatabaseHistory接口。
//        props.setProperty("database.history", FileDatabaseHistory.class.getCanonicalName());
        props.setProperty("database.history", KafkaDatabaseHistory.class.getCanonicalName());
        // 存储数据库历史记录的文件的路径。在需要时database.history被设置为<…>.FileDatabaseHistory。
//        props.setProperty("database.history.file.filename", "./storage/dbhistory.dat");
        // 一个布尔值，指定连接器是否应忽略格式错误或未知的数据库语句或停止处理以便人工修复问题。安全默认值是false. 应谨慎使用跳过，因为在处理 binlog 时可能会导致数据丢失或损坏。
        props.setProperty("database.history.skip.unparseable.ddl", "false");
        // 一个布尔值，指定连接器是否应记录所有 DDL 语句true仅记录与 Debezium 正在捕获其更改的表相关的那些 DDL 语句。
        // true小心设置为，因为如果您更改捕获其更改的表，则可能需要丢失数据。安全默认值是false.
        props.setProperty("database.history.store.only.captured.tables.ddl", "false");
        return props;
    }
}
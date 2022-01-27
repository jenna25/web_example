package cn.cisdigital.bitmagic.web.third.metadata;

import cn.cisdigital.bitmagic.web.common.constants.CdcTaskConstants;
import cn.cisdigital.bitmagic.web.common.enums.EnumDataSourceType;
import cn.cisdigital.bitmagic.web.common.enums.EnumMySqlDataType;
import cn.cisdigital.bitmagic.web.common.exception.CdcTaskException;
import cn.cisdigital.bitmagic.web.component.task.vo.step4.Step4ColumnMappingInfo;
import cn.cisdigital.bitmagic.web.third.metadata.dto.CatalogResultDto;
import cn.cisdigital.bitmagic.web.third.metadata.dto.TableResultDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Copyright (C), 2019-2022, 中冶赛迪重庆信息技术有限公司
 * Description:
 *
 * @author xin.d.li
 * @date 2021-11-24 8:35
 */
@Slf4j
public class MetaDataRemoteClientTest {

    private MetaDataRemoteClient client = null;

    @Before
    public void setUp() throws Exception {
        client = new MetaDataRemoteHttpClientImpl(20, "http://data-assets-process-api.d.cisdigital.cn");
    }

    @Test
    public void catalog() {
        CatalogResultDto catalogResult = client.catalog("34453c0c710d429ea8e07dbc66dee7b6", "1629956754924");
        log.info("catalogs: {}", catalogResult);
        Assert.assertNotNull(catalogResult);
    }

    @Test
    public void listCatalog() {
        List<CatalogResultDto> catalogResult = client.listCatalog("34453c0c710d429ea8e07dbc66dee7b6");
        log.info("catalogs: {}", catalogResult);
        Assert.assertNotNull(catalogResult);
    }

    @Test
    public void listDatabase() {
        List<String> dbs = client.listDatabase("34453c0c710d429ea8e07dbc66dee7b6", "HIVE_INNER_JDBC_CATALOG_34453c0c710d429ea8e07dbc66dee7b6");
        log.info("databases: {}", dbs);
        Assert.assertNotNull(dbs);
    }

    @Test
    public void listTable() {
        List<String> dbs = client.listTable("34453c0c710d429ea8e07dbc66dee7b6", "HIVE_INNER_JDBC_CATALOG_34453c0c710d429ea8e07dbc66dee7b6", "business_z4zp");
        log.info("tables: {}", dbs);
        Assert.assertNotNull(dbs);
    }

    @Test
    public void getTable() {
        TableResultDto table = client.getTable("34453c0c710d429ea8e07dbc66dee7b6", "1638758665169", "wl_test", "batch_code");
        log.info("table: {}", table);
        Assert.assertNotNull(table);

        for (TableResultDto.Field field : table.getFields()
             ) {
            Step4ColumnMappingInfo column = new Step4ColumnMappingInfo();

            testConvertToEnumMysqlDbType(EnumDataSourceType.MYSQL, field, column);
        }
    }

    private static void testConvertToEnumMysqlDbType(EnumDataSourceType sourceType, TableResultDto.Field sourceField, Step4ColumnMappingInfo column) {
        switch (sourceType) {
            case MYSQL:
                EnumMySqlDataType mysqlDataType = null;
                if (sourceField.getType().indexOf(CdcTaskConstants.MYSQL_TYPE_UNSIGNED) > 0) {
                    mysqlDataType = EnumMySqlDataType.valueOf(sourceField.getType().replace(CdcTaskConstants.MYSQL_TYPE_UNSIGNED, ""),
                            sourceField.getSize(), Boolean.TRUE, null);
                } else {
                    mysqlDataType = EnumMySqlDataType.valueOf(sourceField.getType(),
                            sourceField.getSize(), null, null);
                }

                column.setTargetTypeValue(mysqlDataType.getJdbcType());
                column.setTargeType(mysqlDataType.getJdbcTypeName());
                break;
            default:
                throw CdcTaskException.STEP4_NOTSUPPORT_DATA_SOURCE_TYPE;
        }
    }

    @Test
    public void deleteTable() {
        Boolean success = client.deleteTable("34453c0c710d429ea8e07dbc66dee7b6", "HIVE_INNER_JDBC_CATALOG_34453c0c710d429ea8e07dbc66dee7b6", "business_z4zp", "test_del");
        log.info("delete success: {}", success);
        Assert.assertTrue(success);
    }
}
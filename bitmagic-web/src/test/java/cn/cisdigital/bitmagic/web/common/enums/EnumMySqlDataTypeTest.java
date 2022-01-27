package cn.cisdigital.bitmagic.web.common.enums;

import org.junit.Assert;
import org.junit.Test;

public class EnumMySqlDataTypeTest {

    @Test
    public void valueOfTest(){
        // BIT
        EnumMySqlDataType e = EnumMySqlDataType.valueOf("BIT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.BIT_0 == e);
        e = EnumMySqlDataType.valueOf("BIT", 1, null, null);
        Assert.assertTrue(EnumMySqlDataType.BIT_0 == e);
        e = EnumMySqlDataType.valueOf("bit", 2, null, null);
        Assert.assertTrue(EnumMySqlDataType.BIT_N == e);

        // TINYINT
        e = EnumMySqlDataType.valueOf("TINYINT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.TINYINT == e);

        // SMALLINT
        e = EnumMySqlDataType.valueOf("SMALLINT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.SMALLINT == e);

        // MEDIUMINT
        e = EnumMySqlDataType.valueOf("MEDIUMINT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.MEDIUMINT == e);

        // Unsigned_INT
        e = EnumMySqlDataType.valueOf("INT", null, true, null);
        Assert.assertTrue(EnumMySqlDataType.Unsigned_INT == e);

        // INT
        e = EnumMySqlDataType.valueOf("INT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.INT == e);
        e = EnumMySqlDataType.valueOf("INT", null, Boolean.FALSE, null);
        Assert.assertTrue(EnumMySqlDataType.INT == e);

        // Unsigned_INTEGER
        e = EnumMySqlDataType.valueOf("INTEGER", null, true, null);
        Assert.assertTrue(EnumMySqlDataType.Unsigned_INTEGER == e);

        // INTEGER
        e = EnumMySqlDataType.valueOf("INTEGER", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.INTEGER== e);
        e = EnumMySqlDataType.valueOf("INTEGER", null, Boolean.FALSE, null);
        Assert.assertTrue(EnumMySqlDataType.INTEGER == e);

        // Unsigned_BIGINT
        e = EnumMySqlDataType.valueOf("BIGINT", null, true, null);
        Assert.assertTrue(EnumMySqlDataType.Unsigned_BIGINT == e);

        // BIGINT
        e = EnumMySqlDataType.valueOf("BIGINT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.BIGINT== e);
        e = EnumMySqlDataType.valueOf("BIGINT", null, Boolean.FALSE, null);
        Assert.assertTrue(EnumMySqlDataType.BIGINT == e);

        // FLOAT
        e = EnumMySqlDataType.valueOf("FLOAT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.FLOAT== e);

        // DOUBLE
        e = EnumMySqlDataType.valueOf("DOUBLE", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.DOUBLE== e);

        // DECIMAL
        e = EnumMySqlDataType.valueOf("DECIMAL", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.DECIMAL== e);

        // DATE
        e = EnumMySqlDataType.valueOf("DATE", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.DATE== e);

        // DATETIME
        e = EnumMySqlDataType.valueOf("DATETIME", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.DATETIME== e);

        // TIMESTAMP
        e = EnumMySqlDataType.valueOf("TIMESTAMP", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.TIMESTAMP== e);

        // TIME
        e = EnumMySqlDataType.valueOf("TIME", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.TIME== e);

        // YEAR
        e = EnumMySqlDataType.valueOf("YEAR", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.YEAR== e);

        // CHAR
        e = EnumMySqlDataType.valueOf("CHAR", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.CHAR== e);
        e = EnumMySqlDataType.valueOf("CHAR", null, null, false);
        Assert.assertTrue(EnumMySqlDataType.CHAR== e);

        // VARCHAR
        e = EnumMySqlDataType.valueOf("VARCHAR", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.VARCHAR== e);
        e = EnumMySqlDataType.valueOf("VARCHAR", null, null, false);
        Assert.assertTrue(EnumMySqlDataType.VARCHAR== e);

        // BINARY
        e = EnumMySqlDataType.valueOf("BINARY", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.BINARY== e);

        // VARBINARY
        e = EnumMySqlDataType.valueOf("VARBINARY", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.VARBINARY== e);

        // CHAR_BINARY
        e = EnumMySqlDataType.valueOf("CHAR", null, null, true);
        Assert.assertTrue(EnumMySqlDataType.CHAR_BINARY== e);

        // VARCHAR_BINARY
        e = EnumMySqlDataType.valueOf("VARCHAR", null, null, true);
        Assert.assertTrue(EnumMySqlDataType.VARCHAR_BINARY== e);

        // VARBINARY
        e = EnumMySqlDataType.valueOf("VARBINARY", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.VARBINARY== e);

        // BLOB
        e = EnumMySqlDataType.valueOf("BLOB", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.BLOB== e);

        // TINYBLOB
        e = EnumMySqlDataType.valueOf("TINYBLOB", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.TINYBLOB== e);

        // MEDIUMBLOB
        e = EnumMySqlDataType.valueOf("MEDIUMBLOB", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.MEDIUMBLOB== e);

        // LONGBLOB
        e = EnumMySqlDataType.valueOf("LONGBLOB", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.LONGBLOB== e);

        // TEXT
        e = EnumMySqlDataType.valueOf("TEXT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.TEXT== e);

        // TINYTEXT
        e = EnumMySqlDataType.valueOf("TINYTEXT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.TINYTEXT== e);

        // MEDIUMTEXT
        e = EnumMySqlDataType.valueOf("MEDIUMTEXT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.MEDIUMTEXT== e);

        // LONGTEXT
        e = EnumMySqlDataType.valueOf("LONGTEXT", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.LONGTEXT== e);

        // JSON
        e = EnumMySqlDataType.valueOf("JSON", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.JSON== e);

        // GEOMETRY
        e = EnumMySqlDataType.valueOf("GEOMETRY", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.GEOMETRY== e);

        // ENUM
        e = EnumMySqlDataType.valueOf("ENUM", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.ENUM== e);

        // SET
        e = EnumMySqlDataType.valueOf("SET", null, null, null);
        Assert.assertTrue(EnumMySqlDataType.SET== e);
    }
}

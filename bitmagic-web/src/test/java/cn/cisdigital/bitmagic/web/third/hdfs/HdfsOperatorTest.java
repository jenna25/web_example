package cn.cisdigital.bitmagic.web.third.hdfs;

import cn.cisdigital.bitmagic.web.third.hdfs.conf.HdfsConnectProperties;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HdfsOperatorTest  {
    @Test
    public void readHaHdfsFileTest() throws IOException {
        HdfsConnectProperties properties = getProperties();
        HdfsOperator hdfsOperator = new HdfsOperator(HdfsConfiguratorHelper.getConf(properties), properties);
        hdfsOperator.readFile("/common-lib/datax/core.json", "dev", System.out);
        System.out.println();
    }

    @Test
    public void pathExistsOrNotExistsTest() throws IOException {
        HdfsConnectProperties properties = getProperties();
        HdfsOperator hdfsOperator = new HdfsOperator(HdfsConfiguratorHelper.getConf(properties), properties);
        hdfsOperator.readFile("/common-lib/datax/core.json", "dev", System.out);

        boolean fileExist = hdfsOperator.exist("/common-lib/datax/core.json", "dev");
        Assert.assertTrue(fileExist == true);

        boolean fileNotExist = hdfsOperator.exist("/common-lib/datax/core_not_exists.json", "dev");
        Assert.assertTrue(fileNotExist == false);

        boolean dirExist = hdfsOperator.exist("/common-lib/datax", "dev");
        Assert.assertTrue(dirExist == true);

        boolean dirNotExist = hdfsOperator.exist("/common-lib/datax_not_exists_dir", "dev");
        Assert.assertTrue(dirNotExist == false);
    }

    @Test
    public void deleteFileRecursiveTest() throws IOException {
        HdfsConnectProperties properties = getProperties();
        HdfsOperator hdfsOperator = new HdfsOperator(HdfsConfiguratorHelper.getConf(properties), properties);

        boolean deleteFileRecursive = hdfsOperator.deleteFileRecursive("/tmp/images", "hdfs");
        System.out.println("delete=" + deleteFileRecursive);
    }

    public static HdfsConnectProperties getProperties() {
        HdfsConnectProperties properties = new HdfsConnectProperties();
        properties.setUser("dev");
        properties.setNamenode("hdfs://dev-master2.segma.tech:8020");
        properties.setNameservices("");
        properties.setNamenodeAlias("");
        // ?????????standby????????????rpc?????????????????????debug??????????????????" Operation category READ is not supported in state standby"
        // ??????????????????active???????????????????????????????????????????????????????????????????????????
        properties.setRpcAddresses("");
        return properties;
    }
}
package cn.cisdigital.bitmagic.web.third.debezium;

import org.junit.Test;

public class MySqlValidatorTest {

    @Test
    public void validate() {
        Validator validator = new MySqlValidator("localhost", 3306, "root", "123456");
        validator.validate();
    }
}
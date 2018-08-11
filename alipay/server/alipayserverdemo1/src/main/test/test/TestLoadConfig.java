package test;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

public class TestLoadConfig {

    @Test
    public void test() throws Exception {
        Configuration cfg = new PropertiesConfiguration("alipay.properties");
        System.out.println(cfg.getString("alipay_app_private_key"));
    }
}

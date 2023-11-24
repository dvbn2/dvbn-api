package com.dvbn.api;

import com.dvbn.api.client.ApiClient;
import com.dvbn.api.config.WxOpenConfig;
import javax.annotation.Resource;

import com.dvbn.api.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 *

 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Resource
    private ApiClient apiClient;
    @Test
    void contextLoads() {
        System.out.println(wxOpenConfig);
    }

    @Test
    void apiTest() {
        User user = new User();
        user.setName("dvbn");
        String namePut = apiClient.getNamePut(user);
        System.out.println(namePut);
    }

}

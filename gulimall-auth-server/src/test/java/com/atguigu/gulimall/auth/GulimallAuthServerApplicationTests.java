package com.atguigu.gulimall.auth;

import com.atguigu.gulimall.auth.feign.ThirdPartyFeignSeivice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallAuthServerApplicationTests {

    @Autowired
    ThirdPartyFeignSeivice thirdPartyFeignSeivice;

    @Test
    public void test() {
        thirdPartyFeignSeivice.sendCode("18351871258","123456");
    }

    @Test
    void contextLoads() {
    }

}

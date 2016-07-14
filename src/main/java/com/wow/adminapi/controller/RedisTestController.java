package com.wow.adminapi.controller;

import com.wow.common.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengzhiqing on 16/7/7.
 */
@RestController
@RequestMapping("/v1/redis")
public class RedisTestController {

    //private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);

    @RequestMapping(value = "/redis", produces = "application/json;charset=UTF-8")
    public Object testRedis() {
        if (!RedisUtil.exists("123")) {
            System.out.println("start to set 123 to redis");
            RedisUtil.set("123", "测试");

        }
        return RedisUtil.get("123");
    }
}

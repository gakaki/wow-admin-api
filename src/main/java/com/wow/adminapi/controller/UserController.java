package com.wow.adminapi.controller;

import com.wow.user.model.EndUser;
import com.wow.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1.0/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{endUserId}", method = RequestMethod.GET)
    public EndUser getUser(@PathVariable Integer endUserId) {
        logger.info("get user, id=" + endUserId);
        return userService.getEndUserById(endUserId);
    }

    /**
     * 用户注册(创建新用户)
     * @param newEndUser
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public int register(@RequestBody EndUser newEndUser) {
        logger.info("user registering...");
        return userService.register(newEndUser);
    }

    /**
     * 修改用户信息
     * @param endUserId
     * @return
     */
    @RequestMapping(value = "/{endUserId}", method = RequestMethod.PUT)
    public int updateUser(@RequestBody EndUser updatedEndUser, @PathVariable Integer endUserId) {
        logger.info("update user, userId=" + endUserId);
        if (updatedEndUser.getId() == null) {
            updatedEndUser.setId(endUserId);
        }
        return userService.updateEndUser(updatedEndUser);
    }

    @RequestMapping(value = "/{endUserId}", method = RequestMethod.DELETE)
    public int deleteUser(@PathVariable Integer endUserId) {
        logger.info("delete user ...");
        return userService.deleteUser(endUserId);
    }
}
package com.sw.crm.settings.service;

import com.sw.crm.exception.LoginException;
import com.sw.crm.settings.domain.User;

import java.util.List;

/**
 * @MASTER 统治世界的霸主 常狗狗
 * @date 2021/4/29
 */
public interface UserService {

    User login(String act, String pwd, String ip) throws LoginException;
    List<User> getUserList();
}

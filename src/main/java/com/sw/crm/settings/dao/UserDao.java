package com.sw.crm.settings.dao;

import com.sw.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @MASTER 统治世界的霸主 常狗狗
 * @date 2021/4/29
 */
public interface UserDao {
    User login(Map<String, String> map);

    List<User> getUserList();
}

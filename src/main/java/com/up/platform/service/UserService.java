package com.up.platform.service;

import com.up.platform.bo.PageBO;
import com.up.platform.entity.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int editUser(User user);

    int deleteUser(Integer id);

    List<User> findAllUser(PageBO page);

    Integer checkUserPassword(String name, String password);
}

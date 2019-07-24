package com.up.platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.up.platform.bo.PageBO;
import com.up.platform.entity.User;
import com.up.platform.mapper.UserMapper;
import com.up.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int editUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> findAllUser(PageBO page) {
        PageHelper.startPage(page.getPageIndex(), page.getPageLimit());
        return userMapper.selectAllUser();
    }

    @Override
    public Integer checkUserPassword(String name, String password) {
        User user = userMapper.selectByUserName(name);
        if (password.equals(user.getUserPassword())) {
            return user.getId();
        } else {
            return 0;
        }
    }
}

package com.soft1851.mosoteach.service.impl;

import com.soft1851.mosoteach.Repository.UserRepository;
import com.soft1851.mosoteach.domain.entity.User;
import com.soft1851.mosoteach.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Date 2020/9/16  10:20
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User selectById(Long id) {
        return userRepository.findUserByUserId(id);
    }

    @Override
    public List<User> selectAll() {
        List<User> studentList= null;

        return studentList;
    }
}

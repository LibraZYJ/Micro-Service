package com.soft1851.mosoteach.service;

import com.soft1851.mosoteach.common.ResponseResult;
import com.soft1851.mosoteach.domain.entity.User;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName StudentService
 * @Description TODO
 * @Date 2020/9/16  10:20
 * @Version 1.0
 **/
public interface UserService {
    User selectById(Long id);
    List<User> selectAll();
}

package com.soft1851.mosoteach.Repository;

import com.soft1851.mosoteach.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class StudentRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    void selectAll(){
        List<User> userList= userRepository.findAll();
        userList.forEach(System.out::println);
    }
}
package com.soft1851.mosoteach.Repository;

import com.soft1851.mosoteach.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yujie_Zhao
 * @ClassName StudentRepository
 * @Description TODO
 * @Date 2020/9/16  10:39
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserId(Long id);
}

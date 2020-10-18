package com.soft1851.mosoteach.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Yujie_Zhao
 * @ClassName User
 * @Description TODO
 * @Date 2020/9/16  12:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 32)
    private String userName;

    @Column(nullable = false)
    private String userAvatar;

    @Column(nullable = false)
    private String className;
}

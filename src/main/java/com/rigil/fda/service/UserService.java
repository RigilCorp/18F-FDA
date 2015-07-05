package com.rigil.fda.service;

import com.rigil.fda.dao.entity.UserEntity;

public interface UserService {

    UserEntity findByEmail(String email);
    UserEntity findByPhone(String phone);
    UserEntity save(UserEntity userEntity);
    void alertNotifications(String email);
    String getPhoneEmail(String phone);

}
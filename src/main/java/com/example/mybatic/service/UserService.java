package com.example.mybatic.service;

import com.example.mybatic.model.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public void adduser(UserEntity user);
    public List<UserEntity> getAllUser();
    public UserEntity getUserById(int id);
    public void updateUser(UserEntity userEntity, int id);
    public void deleteUser(int id);
}

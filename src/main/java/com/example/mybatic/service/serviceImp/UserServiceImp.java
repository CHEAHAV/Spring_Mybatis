package com.example.mybatic.service.serviceImp;

import com.example.mybatic.model.entities.UserEntity;
import com.example.mybatic.repository.UserRepository;
import com.example.mybatic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;


    @Override
    public void adduser(UserEntity userEntity) {
        userRepository.insertUser(userEntity);
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserEntity getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void updateUser(UserEntity userEntity, int id) {
        userRepository.updateUser(userEntity, id);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteUserById(id);
    }
}

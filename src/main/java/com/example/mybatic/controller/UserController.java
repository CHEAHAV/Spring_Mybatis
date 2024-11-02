package com.example.mybatic.controller;

import com.example.mybatic.model.entities.UserEntity;
import com.example.mybatic.model.request.UserRequest;
import com.example.mybatic.model.response.ResponseObject;
import com.example.mybatic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject<UserEntity>> addUser(@ModelAttribute UserRequest userRequest) {
        System.out.println(userRequest.toString());
        String path_dir = "public/photos/";
        ResponseObject responseObject;
        try{
            Random rand = new Random();
            String fileName = rand.nextInt(999999)+"_"+userRequest.getPhotos().getOriginalFilename();
            Files.copy(userRequest.getPhotos().getInputStream(), Paths.get(path_dir + fileName));
            UserEntity user = new UserEntity();
            user.setUser_id(userRequest.getUser_id());
            user.setName(userRequest.getName());
            user.setGender(userRequest.getGender());
            user.setEmail(userRequest.getEmail());
            user.setPhotos(fileName);
            user.setPro_id(userRequest.getPro_id());
            userService.adduser(user);
        }catch (Exception e){
            e.printStackTrace();
             responseObject= ResponseObject
                    .builder()
                    .message(e.getMessage())
                    .status(HttpStatus.valueOf(400))
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return new ResponseEntity<ResponseObject<UserEntity>>(responseObject, HttpStatus.valueOf(400));
        }
        responseObject = ResponseObject
                .builder()
                .message("User added successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }
    public String getPublicProfile(String profileName) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        return url + "/photos/" + profileName;
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseObject<List<UserEntity>>> getUser(@ModelAttribute UserRequest userRequest) {
        List<UserEntity> userEntities = userService.getAllUser();
        for(UserEntity userEntity : userEntities){
            userEntity.setPhotos(getPublicProfile(userEntity.getPhotos()));
        }
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("User get successfully")
                .payload(userEntities)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<ResponseObject<UserEntity>> getUserById(@PathVariable Integer id) {
        UserEntity userEntity = userService.getUserById(id);
        userEntity.setPhotos(getPublicProfile(userEntity.getPhotos()));
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("User get successfully")
                .payload(userEntity)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseObject<UserEntity>> updateUser(@ModelAttribute UserRequest userRequest, @PathVariable int id) {
        System.out.println(userRequest.toString());
        String path_dir = "public/photos/";
        ResponseObject responseObject;
        try {
            UserEntity userEntity = userService.getUserById(id);
            try {
                String fileName;
                if (userRequest.getPhotos() != null && !userRequest.getPhotos().getOriginalFilename().isEmpty()) {
                    String uniqueFileName = UUID.randomUUID() + "_" + userRequest.getPhotos().getOriginalFilename();
                    Path newFilePath = Paths.get(path_dir + uniqueFileName);
                    Files.copy(userRequest.getPhotos().getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
                    if (userEntity.getPhotos() != null && !userEntity.getPhotos().isEmpty()) {
                        Path oldFilePath = Paths.get(path_dir + userEntity.getPhotos());
                        if (Files.exists(oldFilePath)) {
                            Files.delete(oldFilePath);
                        }
                    }
                    fileName = uniqueFileName;
                } else {
                    fileName = userEntity.getPhotos();
                }
                userEntity.setUser_id(userRequest.getUser_id());
                userEntity.setName(userRequest.getName());
                userEntity.setGender(userRequest.getGender());
                userEntity.setEmail(userRequest.getEmail());
                userEntity.setPhotos(fileName);
                userEntity.setPro_id(userRequest.getPro_id());
                userService.updateUser(userEntity, id);
                responseObject = ResponseObject.builder()
                        .message("User updated successfully")
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .status(HttpStatus.CREATED)
                        .build();
                return new ResponseEntity<>(responseObject, HttpStatus.CREATED);

            } catch (Exception e) {
                e.printStackTrace();
                responseObject = ResponseObject.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .build();
                return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseObject<UserEntity>> deleteUser(@PathVariable int id) {
        UserEntity userEntity = userService.getUserById(id);
        String path_dir = "public/photos/";
        Path photoPath = Paths.get(path_dir + userEntity.getPhotos());
        if (Files.exists(photoPath)) {
            try {
                Files.delete(photoPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Photo not found at: " + photoPath);
        }
        userService.deleteUser(id);
        ResponseObject responseObject = ResponseObject.builder()
                .message("User deleted successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


}

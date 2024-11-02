package com.example.mybatic.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private int user_id;
    private String name;
    private String gender;
    private String email;
    private MultipartFile photos;
    private int pro_id;
}

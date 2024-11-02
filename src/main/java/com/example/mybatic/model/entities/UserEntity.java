package com.example.mybatic.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int user_id;
    private String name;
    private String gender;
    private String email;
    private String photos;
    private int pro_id;
    ProductEntity product;
}

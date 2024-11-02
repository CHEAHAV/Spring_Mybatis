package com.example.mybatic.model.request;

import com.example.mybatic.model.entities.ColorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private int pro_id;
    private String name;
    private float price;
    private MultipartFile image;
    private int color_id;
    ColorEntity color;
}

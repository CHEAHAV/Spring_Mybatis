package com.example.mybatic.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private int pro_id;
    private String name;
    private float price;
    private String image;
    private int color_id;
    ColorEntity color;
}

package com.example.mybatic.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorRequest {
    private int color_id;
    private String one;
    private String two;
    private String three;
    private int pro_id;
}

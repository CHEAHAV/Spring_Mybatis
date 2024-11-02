package com.example.mybatic.service;

import com.example.mybatic.model.entities.ColorEntity;
import com.example.mybatic.model.request.ColorRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ColorService {
    public void add(ColorEntity colorEntity);
    public List<ColorEntity> getAllColors();
    public ColorEntity getColorById(int id);
    public void updateColor(int id, ColorRequest colorRequest);
    public void deleteColor(int id);
}

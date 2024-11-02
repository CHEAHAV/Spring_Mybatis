package com.example.mybatic.service.serviceImp;

import com.example.mybatic.model.entities.ColorEntity;
import com.example.mybatic.model.request.ColorRequest;
import com.example.mybatic.repository.ColorRepository;
import com.example.mybatic.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ColorServiceImp implements ColorService {
    private ColorRepository colorRepository;
    @Override
    public void add(ColorEntity colorEntity) {
        colorRepository.insertColor(colorEntity);

    }

    @Override
    public List<ColorEntity> getAllColors() {
        return colorRepository.getAllColor();
    }

    @Override
    public ColorEntity getColorById(int id) {
        return colorRepository.getColorById(id);
    }

    @Override
    public void updateColor(int id, ColorRequest colorRequest) {
        colorRepository.updateColor(colorRequest, id);
    }

    @Override
    public void deleteColor(int id) {
        colorRepository.deleteColor(id);
    }

}

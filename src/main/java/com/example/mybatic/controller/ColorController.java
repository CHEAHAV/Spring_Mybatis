package com.example.mybatic.controller;

import com.example.mybatic.model.entities.ColorEntity;
import com.example.mybatic.model.request.ColorRequest;
import com.example.mybatic.model.response.ResponseObject;
import com.example.mybatic.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/color")
public class ColorController {
    private final ColorService colorService;
    @PostMapping("/add")
    public ResponseEntity <ResponseObject<List<ColorEntity>>> addColor (@RequestBody ColorEntity colorEntity) {
        colorService.add(colorEntity);
       ResponseObject responseObject = ResponseObject
               .builder()
               .message("Add Success")
               .status(HttpStatus.CREATED)
               .timestamp(new Timestamp(System.currentTimeMillis()))
               .build();
       return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity <ResponseObject<List<ColorEntity>>> getAllColor () {
        List<ColorEntity> colorEntities = colorService.getAllColors();
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("Get Success")
                .payload(colorEntities)
                .status(HttpStatus.valueOf(200))
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(403));
    }
    @GetMapping("/getcolorbyid/{id}")
    public ResponseEntity <ResponseObject<ColorEntity>> getColorById (@PathVariable int id) {
        ColorEntity colorEntity = colorService.getColorById(id);
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("Get Color Success")
                .payload(colorEntity)
                .status(HttpStatus.valueOf(200))
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity <ResponseObject> updateColor (@PathVariable int id, @RequestBody ColorRequest colorRequest) {
        colorService.updateColor(id, colorRequest);
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("Update Success")
                .status(HttpStatus.CREATED)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity <ResponseObject> deleteColor (@PathVariable int id) {
        colorService.deleteColor(id);
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("Delete Success")
                .status(HttpStatus.valueOf(200))
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(403));

    }
}

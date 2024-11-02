package com.example.mybatic.controller;

import com.example.mybatic.model.entities.ProductEntity;
import com.example.mybatic.model.entities.UserEntity;
import com.example.mybatic.model.request.ProductRequest;
import com.example.mybatic.model.request.UserRequest;
import com.example.mybatic.model.response.ResponseObject;
import com.example.mybatic.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/add")
    public ResponseEntity <ResponseObject<ProductService>> AddProduct(@ModelAttribute ProductRequest productRequest) {
        String dir_path = "public/image/";
        ResponseObject responseObject;
        try{
            Random rand = new Random();
            String fileName = rand.nextInt(99999) + "_" + productRequest.getImage().getOriginalFilename();
            Files.copy(productRequest.getImage().getInputStream(), Paths.get(dir_path + fileName));
            ProductEntity productEntity = new ProductEntity();
            productEntity.setPro_id(productRequest.getPro_id());
            productEntity.setName(productRequest.getName());
            productEntity.setPrice(productRequest.getPrice());
            productEntity.setImage(fileName);
            productEntity.setColor_id(productRequest.getColor_id());
            productService.AddProduct(productEntity);
        } catch (Exception e) {
            e.printStackTrace();
            responseObject = ResponseObject
                    .builder()
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return new ResponseEntity<>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
        }
          responseObject = ResponseObject
                .builder()
                .message("Add success")
                .status(HttpStatus.CREATED)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }
    public String getPublicImage(String imageName){
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        return url + "/image/" + imageName;
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseObject<List<ProductEntity>>> getProduct() {
        List<ProductEntity> productEntities = productService.getAllProducts();
        for (ProductEntity productEntity : productEntities) {
            productEntity.setImage(getPublicImage(productEntity.getImage()));
        }
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("Get success")
                .payload(productEntities)
                .status(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getproductbyid/{id}")
    public ResponseEntity<ResponseObject<ProductEntity>> getProductById(@PathVariable int id) {
        ProductEntity productEntity = productService.getProductById(id);
        productEntity.setImage(getPublicImage(productEntity.getImage()));
        ResponseObject responseObject = ResponseObject
                .builder()
                .message("Get success")
                .payload(productEntity)
                .status(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseObject<UserEntity>> updateUser(@ModelAttribute ProductRequest productRequest, @PathVariable int id) {
        System.out.println(productRequest.toString());
        String path_dir = "public/image/";
        ResponseObject responseObject;
        try {
            ProductEntity productEntity = productService.getProductById(id);
            try {
                String fileName;
                if (productRequest.getImage() != null && !productRequest.getImage().getOriginalFilename().isEmpty()) {
                    String uniqueFileName = UUID.randomUUID() + "_" + productRequest.getImage().getOriginalFilename();
                    Path newFilePath = Paths.get(path_dir + uniqueFileName);
                    Files.copy(productRequest.getImage().getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
                    if (productEntity.getImage() != null && !productEntity.getImage().isEmpty()) {
                        Path oldFilePath = Paths.get(path_dir + productEntity.getImage());
                        if (Files.exists(oldFilePath)) {
                            Files.delete(oldFilePath);
                        }
                    }
                    fileName = uniqueFileName;
                } else {
                    fileName = productEntity.getName();
                }
                productEntity.setPro_id(productRequest.getPro_id());
                productEntity.setName(productRequest.getName());
                productEntity.setPrice(productRequest.getPrice());
                productEntity.setImage(fileName);
                productEntity.setColor_id(productRequest.getColor_id());
                productService.updateProductById(productEntity, id);
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
    public ResponseEntity<ResponseObject<ProductEntity>> deleteProduct(@PathVariable int id) {
        ProductEntity productEntity = productService.getProductById(id);
        String path_dir = "public/image/";
        Path imagePath = Paths.get(path_dir + productEntity.getImage());
        if (Files.exists(imagePath)) {
            try {
                Files.delete(imagePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Photo not found at: " + imagePath);
        }
        productService.deleteProductById(id);
        ResponseObject responseObject = ResponseObject.builder()
                .message("User deleted successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}

package com.example.mybatic.service;

import com.example.mybatic.model.entities.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public void AddProduct (ProductEntity productEntity);
    public List<ProductEntity> getAllProducts();
    public ProductEntity getProductById(int id);
    public void deleteProductById(int id);
    public void updateProductById(ProductEntity productEntity, int id);
}

package com.example.mybatic.service.serviceImp;

import com.example.mybatic.model.entities.ProductEntity;
import com.example.mybatic.repository.ProductRepository;
import com.example.mybatic.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public void AddProduct(ProductEntity productEntity) {
        productRepository.insertProduct(productEntity);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findProductAll();
    }

    @Override
    public ProductEntity getProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public void updateProductById(ProductEntity productEntity, int id) {
        productRepository.updateProduct(productEntity, id);

    }
}

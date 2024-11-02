package com.example.mybatic.repository;

import com.example.mybatic.model.entities.ProductEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductRepository{
    @Insert("INSERT INTO tb_product (pro_id, name, price, image, color_id) VALUES (#{pro.pro_id}, #{pro.name}, #{pro.price}," +
            " #{pro.image}, #{pro.color_id})")
    public void insertProduct(@Param("pro")ProductEntity productEntity);

    @Select("SELECT * FROM tb_product ORDER BY pro_id ASC ")
    @Result(property = "color_id", column = "color_id")
    @Result(property = "color", column = "color_id",
    one = @One (select = "com.example.mybatic.repository.ColorRepository.getColorById"))
    public List<ProductEntity> findProductAll();

    @Select("SELECT * FROM tb_product WHERE pro_id = #{pro_id}")
    @Result(property = "color_id", column = "color_id")
    @Result(property = "color", column = "color_id",
            one = @One (select = "com.example.mybatic.repository.ColorRepository.getColorById"))
    public ProductEntity findProductById(@Param("pro_id")int id);

    @Select("UPDATE tb_product SET pro_id = #{pro.pro_id}, name = #{pro.name}, price = #{pro.price}, image = #{pro.image} WHERE pro_id = #{pro_id}")
    public void updateProduct(@Param("pro")ProductEntity productEntity, @Param("pro_id") int id);

    @Select("DELETE FROM tb_product WHERE pro_id = #{pro_id}")
    public void deleteProductById(@Param("pro_id")int id);
}

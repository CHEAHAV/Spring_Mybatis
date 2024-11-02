package com.example.mybatic.repository;

import com.example.mybatic.model.entities.ColorEntity;
import com.example.mybatic.model.request.ColorRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ColorRepository {
    @Insert("INSERT INTO tb_color (color_id, one, two, three) VALUES (#{color.color_id}, #{color.one}, #{color.two}, #{color.three})")
    public void insertColor(@Param("color") ColorEntity color);

    @Select("SELECT * FROM tb_color")
    public List<ColorEntity> getAllColor();

    @Select("SELECT * FROM tb_color WHERE color_id = #{color_id}")
    public ColorEntity getColorById(@Param("color_id") int id);

    @Select("UPDATE tb_color SET color_id = #{color.color_id}, one = #{color.one}, two = #{color.two}, three = #{color.three}" +
            " WHERE color_id = #{color_id}")
    public void updateColor(@Param("color") ColorRequest colorRequest, @Param("color_id") int id);

    @Select("DELETE FROM tb_color WHERE color_id = #{color_id}")
    public void deleteColor(@Param("color_id") int id);

}

package com.example.mybatic.repository;

import com.example.mybatic.model.entities.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {
    @Insert("INSERT INTO tb_user (user_id, name, gender, email, photos, pro_id) VALUES (#{user.user_id}," +
            "#{user.name}, #{user.gender}, #{user.email}, #{user.photos}, #{user.pro_id}) ")
    public void insertUser(@Param("user")UserEntity userEntity);

    @Select("SELECT * FROM tb_user")
    @Results(id = "like", value = {
            @Result(property = "pro_id", column = "pro_id"),
            @Result(property = "product", column = "pro_id",
                    one = @One (select = "com.example.mybatic.repository.ProductRepository.findProductById"))
    })
    public List<UserEntity> getAllUsers();

    @Select("SELECT * FROM tb_user WHERE user_id = #{user_id}")
    @ResultMap("like")
    public UserEntity getUserById(@Param("user_id")int user_id);

    @Select("UPDATE tb_user SET user_id = #{user.user_id}, name = #{user.name}, gender = #{user.gender}, email = #{user.email}," +
            " photos = #{user.photos} WHERE user_id = #{user_id}")
    public void updateUser(@Param("user")UserEntity userEntity, @Param("user_id") int id);

    @Select("DELETE FROM tb_user WHERE user_id = #{user_id}")
    public void deleteUserById(@Param("user_id")int id);
}

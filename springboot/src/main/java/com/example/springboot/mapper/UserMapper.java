package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    //    @Select("SELECT * from sys_user")
//    List<User> findAll();
//
//    int update(User user);
//    @Insert("INSERT into sys_user(username, password, email, phone) VALUES (#{username} , #{password}, #{email}, #{phone})")
//    int insert(User user);
//    @Delete("delete from sys_user where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize,String username);
//    @Select("select count(*) from sys_user where username like concat('%',#{username},'%')")
//    Integer selectTotal(String username);
}

package com.example.user.mapper;

import com.example.user.mapper.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
*  @author author
*/
@Mapper
public interface UserMapper {

    int insertuser(User object);

    int updateuser(User object);

    User getUser(@Param("userName")String userName);

    User getUserById(@Param("userId") Integer userId);
}
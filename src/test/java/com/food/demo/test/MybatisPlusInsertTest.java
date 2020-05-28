package com.food.demo.test;

import com.food.demo.mapper.SysUserMapper;
import com.food.demo.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusInsertTest {
    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void test(){
        SysUser user =new SysUser();
        user.setPhone("18888888888");
        int id=userMapper.insert(user);
    }
}

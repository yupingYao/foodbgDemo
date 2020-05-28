package com.food.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.food.demo.mapper.SysUserMapper;
import com.food.demo.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MybatisPlusDeleteTest {

    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void testdeleteById(){

        // 返回删除的记录数
        int i = userMapper.deleteById(2);
        System.out.println(i);
    }


    @Test
    public void testdeleteBatchIds(){

        // 返回删除的记录数
        int i = userMapper.deleteBatchIds(Arrays.asList(2,3));
        System.out.println(i);
    }



    @Test
    public void testdeleteByMap(){

        //todo : map 写的是数据表中的列名，而非实体类的属性名。
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id", 4);
        // 返回删除的记录数
        int i = userMapper.deleteByMap(paramMap);
        System.out.println(i);
    }


    @Test
    public void testdelete(){

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getId,5);
        // 开始删除，返回删除的记录数
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);
    }
}

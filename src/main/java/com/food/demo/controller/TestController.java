package com.food.demo.controller;

import com.food.demo.mapper.SysUserMapper;
import com.food.demo.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private SysUserMapper userMapper;

    @RequestMapping("/")
    @ResponseBody
    public String test(){
        SysUser user = userMapper.selectById(1);
        System.out.print(user.getId());
        return "hello";
    }
}

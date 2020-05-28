package com.food.demo.test;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.food.demo.mapper.SysUserMapper;
import com.food.demo.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusUpdateTest {

    @Autowired
    private SysUserMapper userMapper;


    @Test
    public void testupdateById(){
        //如果实体类某个属性为 null，不会更新该属性
        SysUser userInfo = new SysUser();
        userInfo.setId(1);
        userInfo.setPhone("18601670983");
        // 开始修改，返回影响的记录数
        int i = userMapper.updateById(userInfo);

    }


    @Test
    public void testupdate(){

        /*LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        //查询修改条件
        updateWrapper.eq(SysUser::getId,1);
        // 将满足条件的记录密码都设置为8888
        SysUser userInfo = new SysUser();
        userInfo.setPassWord("8888");
        // 开始修改，返回影响的记录数
        int i = userMapper.update(userInfo, updateWrapper);*/

        //我们也可以通过 updateWrapper 的 set 方法直接设置字段值

        /*LambdaUpdateWrapper<SysUser> updateWrapper2 = new LambdaUpdateWrapper<>();
        updateWrapper2.eq(SysUser::getId,1)
                .set(SysUser::getPassWord, "8888")
                .set(SysUser::getEmail, "123456789@qq.com");

        // 开始修改，返回影响的记录数
        int h = userMapper.update(null, updateWrapper2);*/

        /*********** 二者可以结合使用的，下面效果等效于上面的 ****************/

        /*LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(SysUser::getId,1)
                .set(SysUser::getEmail, null);
        // 将满足条件的记录密码都设置为8888
        SysUser userInfo = new SysUser();
        userInfo.setPassWord("8888");
        // 开始修改，返回影响的记录数
        int i = userMapper.update(userInfo, updateWrapper);*/

        /*********** 而也可通过 updateWrapper 的 setSql 方法可以直接设置 set 部分的 sql，下面的效果同上面是一样的：****************/

        /*LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(SysUser::getId,1)
                .setSql("age = null");
        // 将满足条件的记录密码都设置为8888
        SysUser userInfo = new SysUser();
        userInfo.setPassWord("8888");
        // 开始修改，返回影响的记录数
        int i = userMapper.update(userInfo, updateWrapper);*/

        /** 上面每次编写完构造语句后都要将对象传递给 mapper 的 update 方法，比较麻烦，MyBatisPlus 提供了函数式编程方式： **/

        /*boolean success = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(SysUser::getId,1)
                .set(SysUser::getPassWord, "8888")
                .set(SysUser::getEmail, null)
                .update();*/

        /** 二者可以结合使用的，下面效果等效于上面的 **/

        /*SysUser userInfo = new SysUser();
        userInfo.setPassWord("8888");
        boolean success2 = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(SysUser::getId,1)
                .set(SysUser::getEmail, null)
                .update(userInfo);*/

        /** 下面是结合 setSql 使用： **/

        /*boolean success3 = new LambdaUpdateChainWrapper<>(userMapper)
                .like(SysUser::getId,1)
                .setSql("pass_word = '8888'")
                .setSql("age = null")
                .update();*/

        /** 等同于  **/

        SysUser userInfo = new SysUser();
        userInfo.setPassWord("8888");
        boolean success4 = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(SysUser::getId,1)
                .setSql("email = null")
                .update(userInfo);


    }


}

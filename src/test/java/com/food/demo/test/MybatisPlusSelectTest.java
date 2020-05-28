package com.food.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.food.demo.mapper.SysUserMapper;
import com.food.demo.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusSelectTest {

    @Autowired
    private SysUserMapper userMapper;


    /**
     * @Description: selectByid 通过id查询
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void testselectByid(){

        SysUser user = userMapper.selectById(1);
        System.out.print(user.getId());
    }


    /**
     * @Description: selectOne 自定义查询条件查询，返回单条记录  todo 注意：条件查询在库中存在多条记录时会报错
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void testselectOne(){

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1);
        SysUser user = userMapper.selectOne(queryWrapper);
        System.out.print(user.getId());
    }


    /**
     * @Description: selectBatchIds 通过id集批量查询返回List集合
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void testselectBatchIds(){

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        List<SysUser> user = userMapper.selectBatchIds(ids);
        System.out.print(user.get(0).getId());
    }

    /**
     * @Description: selectByMap返回List集合
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void testselectByMap(){


        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id", 1);
        List<SysUser> user = userMapper.selectByMap(paramMap);
        System.out.print(user.get(0).getId());
    }

    /**
     * @Description: selectList返回List集合  todo 注意:selectList 使用与 selectOne 类似，不同的是当数据库中符合传入的条件的记录有多条，使用 selectOne 会报错。这时就要使用 selectList。
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void testselectList(){

        QueryWrapper<SysUser> queryWrapper =new QueryWrapper();
        queryWrapper.eq("id",1);
        List<SysUser> user = userMapper.selectList(queryWrapper);
        System.out.print(user.get(0).getId());

        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getId,1);
        List<SysUser> user2 = userMapper.selectList(lambdaQueryWrapper);
        System.out.print(user.get(0).getId());

    }

    /**
     * MyBatis-Plus 提供了 4 种方式创建 lambda 条件构造器，前三种分别如下：
     *
     * LambdaQueryWrapper<UserInfo> lqw1 = new QueryWrapper<UserInfo>().lambda();
     * LambdaQueryWrapper<UserInfo> lqw2= new LambdaQueryWrapper<>();
     * LambdaQueryWrapper<UserInfo> lqw3 = Wrappers.lambdaQuery();
     *
     * 无论是之前的 lambda 构造器还是 queryWrapper，每次编写完条件构造语句后都要将对象传递
     * 给 mapper 的 selectList 方法，比较麻烦，MyBatisPlus 提供了第四种函数式编程方式，不用每次都传。
     *
     *
     * List<UserInfo> userInfos = new LambdaQueryChainWrapper<>(userInfoMapper)
     *         .like(UserInfo::getUserName,"ha")
     *         .lt(UserInfo::getAge,40)
     *         .list();
     *
     * 我们也可以将对象直接以构造参数的形式传递给 QueryWrapper，MyBatisPlus 会自动根据实体对象中的属性
     * 自动构建相应查询的 SQL 语句：
     *
     *
     * // 查询条件：名字为'hangge'并且年龄为22
     * UserInfo userInfo = new UserInfo();
     * userInfo.setUserName("hangge");
     * userInfo.setAge(22);
     * QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>(userInfo);
     * // 开始查询
     * List<UserInfo> users = userInfoMapper.selectList(queryWrapper);
     * System.out.println(users);
     */



    /**
     * @Description: selectMaps 返回一个List<Map> todo selectMaps 的用法和上面的 selectList 很像，都是传入一个查询构造器进行查询，然后返回一个 List。不同在于 selectMaps 返回的 List 里面是 Map
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void selectMaps(){

        SysUser userInfo = new SysUser();
        userInfo.setId(1);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //todo : Map 里的 key 为表字段名，而不是对应实体类的属性名。
        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        System.out.println(users);

    }


    /**
     * @Description: selectObjs 返回一个list,但是只有第一个字段的值
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void selectObjs(){

        SysUser userInfo = new SysUser();
        userInfo.setId(1);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //todo : 用法和前面的 selectList 很像，都是传入一个查询构造器进行查询，然后返回一个 List。不同在于 selectObjs 返回的 List 里面只有返回的第一个字段值
        List<Object> users = userMapper.selectObjs(queryWrapper);
        System.out.println(users);

    }


    /**
     * @Description: selectCount 使用查询构造器，查询总记录数
     * @param: []
     * @return: void
     * @Author: yaoyp
     * @date: 2020/5/28 0028
     */
    @Test
    public void selectCount(){

        SysUser userInfo = new SysUser();
        userInfo.setId(1);
        /**
         *  selectCount 的用法和前面的 selectList 很像，都是传入一个查询构造器进行查询，
         *   不同的是 selectCount 返回的是一个 Integer 值（符合条件的记录数）：
         */
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);

    }



}

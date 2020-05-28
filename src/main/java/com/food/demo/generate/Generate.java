package com.food.demo.generate;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generate {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "E:/git/foodbgdemo/";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("yaoyp");
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        // 是否打开输出目录
        gc.setOpen(false);
        // 时间采用java 8，（操作工具类：JavaLib => DateTimeUtils）
        gc.setDateType(DateType.TIME_PACK);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(false);
        // XML columList
        gc.setBaseColumnList(false);
        //是否生成 kotlin 代码
        gc.setKotlin(false);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        //gc.setEntityName(Config.FILE_NAME_MODEL);
        //gc.setMapperName(Config.FILE_NAME_DAO);
        //gc.setXmlName(Config.FILE_NAME_XML);
        //gc.setServiceName(Config.FILE_NAME_SERVICE);
        //gc.setServiceImplName(Config.FILE_NAME_SERVICE_IMPL);
        //gc.setControllerName(Config.FILE_NAME_CONTROLLER);
        // 主键类型
        //gc.setIdType(IdType.ASSIGN_ID);
        // 是否覆盖已有文件
        gc.setFileOverride(false);
        // model swagger2
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=UTF-8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1996");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(null);
        // 这个地址是生成的配置文件的包路径
        pc.setEntity("com.food.demo.model");
        pc.setXml(null);
        //pc.setController("com.cikers.ps.controller");
        pc.setMapper("com.food.demo.mapper");
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return "E:/git/foodbgdemo/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        //配置自定义输出模板
        // 不需要其他的类型时，直接设置为null就不会成对应的模版了
        //templateConfig.setEntity("...");
        templateConfig.setService(null);
        templateConfig.setController(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setXml(null);
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也
        // 可以自定义模板名称 只要放到目录下，名字不变 就会采用这个模版 下面这句有没有无所谓
        // 模版去github上看地址：
        /**https://github.com/baomidou/mybatis-plus/tree/3.0/mybatis-plus-generator/src/main/resources/templates*/
        //templateConfig.setEntity("/templates/entity.java");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true); // 全局大写命名 ORACLE 注意
        strategy.setSkipView(false); // 是否跳过视图
        //strategy.setDbColumnUnderline(true);
        // 此处可以修改为您的表前缀(数组)
        //strategy.setTablePrefix(tablePrefixes);
        // 字段前缀
        //strategy.setFieldPrefix(fieldPrefixes);
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //修改替换成你需要的表名，多个表名传数组
        strategy.setInclude(scanner("表名"));
        // 排除生成的表
        //.setExclude(new String[]{"test"});
        // lombok实体
        strategy.setEntityLombokModel(true);
        //【实体】是否为构建者模型（默认 false）
        strategy.setEntityBuilderModel(true);
        // 【实体】是否生成字段常量（默认 false）// 可通过常量名获取数据库字段名 // 3.x支持lambda表达式
        strategy.setEntityColumnConstant(false);
        // 逻辑删除属性名称
        //strategy.setLogicDeleteFieldName(Config.FIELD_LOGIC_DELETE_NAME);
        //strategy.setEntityTableFieldAnnotationEnable;
        //strategy.entityTableFieldAnnotationEnable(true);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setRestControllerStyle(false);
        //strategy.setSuperControllerClass("com.cikers.ps.controller.MysqlController");
        strategy.setInclude(scanner("表名"));
        // 设置继承的父类字段
        //strategy.setSuperEntityColumns("id","modifiedBy","modifiedOn","createdBy","createdOn");
        //strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();

    }

}

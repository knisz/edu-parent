package com.zwh.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zwh.eduservice.mapper")
public class EduConfig {
//    高版本不用配置逻辑删除插件了
//    @Bean
//    public ISqlInjector sqlInjector(){
//        return new LogicSqlInjector();
//        }
//    }
}

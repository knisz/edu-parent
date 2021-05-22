package com.zwh.eduservice; //包名必须和启动类包名一致

import com.zwh.eduservice.entity.EduTeacher;
import com.zwh.eduservice.service.EduTeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {
    @Autowired
    private EduTeacherService teacherService;

    @Test
    public void myTest1() {
        List<EduTeacher> list = teacherService.list(null);
        System.out.println(list);
    }


}

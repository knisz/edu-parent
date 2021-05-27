package com.zwh.eduservice.controller;


import com.zwh.commonutils.R;
import com.zwh.eduservice.entity.vo.CourseInfoVo;
import com.zwh.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    EduCourseService courseService;

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.saveCourseInfo(courseInfoVo);
        return R.ok();
    }


}


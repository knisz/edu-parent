package com.zwh.eduservice.service;

import com.zwh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-27
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfoVo courseInfoVo);
}

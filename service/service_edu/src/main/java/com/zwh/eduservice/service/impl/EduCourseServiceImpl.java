package com.zwh.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwh.eduservice.entity.EduCourse;
import com.zwh.eduservice.entity.EduCourseDescription;
import com.zwh.eduservice.entity.vo.CourseInfoVo;
import com.zwh.eduservice.mapper.EduCourseMapper;
import com.zwh.eduservice.service.EduCourseDescriptionService;
import com.zwh.eduservice.service.EduCourseService;
import com.zwh.servicebase.exceptionhandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-27
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;
    //添加课程信息
    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {

        //向课程表添加信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if(insert==0){
            throw new MyException(20001,"添加课程失败!!");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //向课程简介表加数据
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        eduCourseDescription.setId(cid);
        descriptionService.save(eduCourseDescription);
    }
}

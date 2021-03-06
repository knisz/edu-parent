package com.zwh.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwh.commonutils.JwtUtils;
import com.zwh.commonutils.R;
import com.zwh.commonutils.ordervo.CourseWebVoOrder;
import com.zwh.eduservice.client.OrdersClient;
import com.zwh.eduservice.entity.EduCourse;
import com.zwh.eduservice.entity.chapter.ChapterVo;
import com.zwh.eduservice.entity.frontvo.CourseFrontVo;
import com.zwh.eduservice.entity.frontvo.CourseWebVo;
import com.zwh.eduservice.service.EduChapterService;
import com.zwh.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //查询用户是否购买此课程 TODO
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        //boolean buyCourse = ordersClient.isBuyCourse(courseId, memberIdByJwtToken);
        boolean buyCourse = false;
        //boolean buyCourse = ordersClient.isBuy();

        return R.ok().data("courseWebVo",courseWebVo)
                .data("chapterVideoList",chapterVideoList)
                .data("isBug",buyCourse);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}













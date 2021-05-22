package com.zwh.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwh.commonutils.R;
import com.zwh.eduservice.entity.EduTeacher;
import com.zwh.eduservice.entity.vo.TeacherQuery;
import com.zwh.eduservice.service.EduTeacherService;
import com.zwh.servicebase.exceptionhandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-21
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("/findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);

        return R.ok().data("items", list);
    }

    @GetMapping("/pageTeacher/{current}/{limit}")
    public R findPage(@PathVariable(name = "current") int current,
                      @PathVariable(name = "limit") int limit) {
        Page<EduTeacher> list = new Page<>(current, limit);
        teacherService.page(list, null);
        return R.ok().data("items", list);//法1

        //return R.ok().data("total",list.getTotal()).data("rows",list.getRecords());//法2

//        Map<String,Object> map = new HashMap<>();//法3
//        map.put("total",list.getTotal());
//        map.put("rows",list.getRecords());
//        return R.ok().data(map);
    }

    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R findPageCondition(@PathVariable(name = "current") int current,
                               @PathVariable(name = "limit") int limit,
                               @RequestBody(required = false) TeacherQuery teacherQuery) {   //使用RequestBody必须Post请求
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        teacherService.page(pageTeacher, queryWrapper);
        return R.ok().data("items", pageTeacher);//法1
    }

    @PostMapping("/add")
    public R AddTeacher(@RequestBody EduTeacher teacher) {
        if (teacherService.save(teacher)) {
            return R.ok();
        }
        return R.error();
    }

    @DeleteMapping("/{id}")
    public R removeTeacher(@PathVariable String id) {
        if (teacherService.removeById(id)) {
            return R.ok();
        }
        return R.error();
    }

    @PutMapping("/{id}")
    public R updateTeacher(@PathVariable String id,
                           @RequestBody EduTeacher teacher) {
        teacher.setId(id);

        if (teacherService.updateById(teacher)) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/errTest")
    public R errTest(int number) {
        int i;
        try {
            i = 10 / number;
        } catch (Exception e) {
            throw new MyException(20001, "触发异常！");
        }
        return R.ok().data("result",String.valueOf(i));
    }

}


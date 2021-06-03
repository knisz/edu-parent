package com.zwh.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwh.commonutils.R;
import com.zwh.eduservice.entity.EduComment;
import com.zwh.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-02
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    EduCommentService commentService;

    @GetMapping("/page/{courseId}/{current}/{limit}")
    public R findAll(@PathVariable long courseId,
                     @PathVariable long current,
                     @PathVariable long limit) {
        Page<EduComment> commentPage = new Page<>(current, limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.orderByDesc("gmt_create");
        commentService.page(commentPage, wrapper);
        return R.ok().data("items", commentPage);
    }

    @PostMapping("/comment")
    public R addComment(@RequestBody EduComment comment) {
        commentService.save(comment);
        return R.ok();
    }


}


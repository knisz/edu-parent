package com.zwh.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.eduservice.entity.EduChapter;
import com.zwh.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-27
 */
public interface EduChapterService extends IService<EduChapter> {

    boolean deleteChapter(String chapterId);

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}

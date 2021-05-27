package com.zwh.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwh.eduservice.entity.EduSubject;
import com.zwh.eduservice.entity.excel.SubjectData;
import com.zwh.eduservice.entity.subject.OneSubject;
import com.zwh.eduservice.entity.subject.TwoSubject;
import com.zwh.eduservice.listener.SubjectExcelListener;
import com.zwh.eduservice.mapper.EduSubjectMapper;
import com.zwh.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-27
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

//    @Autowired    //不需要这样，直接使用baseMapper即可
//    EduSubjectMapper subjectMapper;

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllTwoSubject() {
        //1.查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");         //等于0的是一级分类
        wrapperOne.orderByAsc("sort", "id");       //排序
        List<EduSubject> subjectsOne = baseMapper.selectList(wrapperOne);   //或者 this.list(wrapperOne)也可以

        //2.查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");        //不等于0的是二级分类
        wrapperOne.orderByAsc("sort", "id");
        List<EduSubject> subjectsTwo = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3.封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
        //封装到要求的list集合里面 List<OneSubject> finalSubjectList
        subjectsOne.forEach((sub) -> {
            OneSubject oneSubject = new OneSubject();
            //sub值复制到对应oneSubject对象里面
            //oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(sub, oneSubject);
            finalSubjectList.add(oneSubject);
        });

        //4.封装二级分类
        subjectsTwo.forEach((sub)->{
            for (OneSubject oneSubject : finalSubjectList) {
                if(oneSubject.getId().equals(sub.getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(sub, twoSubject);
                    oneSubject.getChildren().add(twoSubject);
                }
            }
        });
        return finalSubjectList;
    }
}

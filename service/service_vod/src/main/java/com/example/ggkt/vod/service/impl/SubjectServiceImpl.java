package com.example.ggkt.vod.service.impl;


import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ggkt.exception.ExampleException;
import com.example.ggkt.vod.listener.SubjectListener;
import com.example.ggkt.vod.mapper.SubjectMapper;
import com.example.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-11
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectListener subjectListener;

    @Override
    public List<Subject> selectSubjectList(Long id) {

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);

        for (Subject subject : subjectList) {
            if (isChildren(subject.getId())) {
                subject.setHasChildren(true);
            }
        }

        return subjectList;
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            List<Subject> subjectList = baseMapper.selectList(null);

            List<SubjectEeVo> subjectEeVoList = new ArrayList<>();
            for (Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject, subjectEeVo);
                subjectEeVoList.add(subjectEeVo);
            }

            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEeVoList);

        } catch (Exception e) {

        }
    }

    @Override
    public void importData(MultipartFile multipartFile) {
        try {
            EasyExcel.read(multipartFile.getInputStream(), SubjectEeVo.class, subjectListener)
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            throw new ExampleException(20001, "导入课程失败");
        }
    }

    private boolean isChildren(Long id) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

}

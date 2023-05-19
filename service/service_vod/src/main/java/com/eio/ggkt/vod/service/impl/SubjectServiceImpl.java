package com.eio.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eio.ggkt.exception.GgktException;
import com.eio.ggkt.model.vod.Subject;
import com.eio.ggkt.vo.vod.SubjectEeVo;
import com.eio.ggkt.vod.listener.SubjectListener;
import com.eio.ggkt.vod.mapper.SubjectMapper;
import com.eio.ggkt.vod.service.SubjectService;
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
 * @author eio
 * @since 2023-05-08
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private SubjectListener subjectListener;

    /**
     * 实现每次查询一层数据
     *
     * @param id
     * @return
     */
    @Override
    public List<Subject> selectSubjectList(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Subject> subjectList = subjectMapper.selectList(wrapper);
        //subjectList 遍历，得到每个subject对象，判断是否有下一层数据，如果有，蒋hasChildren设为true
        for (Subject subject : subjectList) {
            Long subjectId = subject.getId();
            subject.setHasChildren(this.isChild(subjectId));
        }
        return subjectList;
    }

    /**
     * 判断是否有下一层数据
     *
     * @param subjectId
     * @return
     */
    private boolean isChild(Long subjectId) {

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", subjectId);
        return subjectMapper.selectCount(wrapper) > 0;
    }

    /**
     * 实现课程分类导出
     *
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {

        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //查询课程分类表所有数据
            List<Subject> subjectList = subjectMapper.selectList(null);

            // List<Subject>  --->   List<SubjectEeVo>
            ArrayList<SubjectEeVo> subjectEeVoList = new ArrayList<>();

            for (Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                //subjectEeVo.setId(subject.getId());
                //subjectEeVo.setParentId(subject.getParentId());
                //快捷方式
                BeanUtils.copyProperties(subject, subjectEeVo);
                subjectEeVoList.add(subjectEeVo);
            }
            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEeVoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 实现课程分类导入
     *
     * @param file
     */
    @Override
    public void importData(MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), SubjectEeVo.class, subjectListener)
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            throw new GgktException(20001,"导入失败");
        }
    }
}

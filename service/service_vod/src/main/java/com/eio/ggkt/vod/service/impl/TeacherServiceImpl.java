package com.eio.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.ggkt.model.vod.Teacher;
import com.eio.ggkt.vo.vod.TeacherQueryVo;
import com.eio.ggkt.vod.mapper.TeacherMapper;
import com.eio.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author eio
 * @since 2023-04-28
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public IPage<Teacher> findQueryPage(Page<Teacher> page,TeacherQueryVo teacherQueryVo) {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        System.out.println(teacherQueryVo);

        if (!StringUtils.isEmpty(teacherQueryVo.getName())) {
            queryWrapper.like("name",teacherQueryVo.getName());
        }
        if (!StringUtils.isEmpty(teacherQueryVo.getLevel())) {
            queryWrapper.eq("level",teacherQueryVo.getLevel());
        }
        if (!StringUtils.isEmpty(teacherQueryVo.getJoinDateBegin())) {
            queryWrapper.ge("join_date",teacherQueryVo.getJoinDateBegin());
        }
        if (!StringUtils.isEmpty(teacherQueryVo.getJoinDateEnd())) {
            queryWrapper.le("join_date",teacherQueryVo.getJoinDateEnd());
        }

        return teacherMapper.selectPage(page,queryWrapper);
    }
}

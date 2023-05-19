package com.eio.ggkt.vod.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.ggkt.model.vod.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.ggkt.vo.vod.TeacherQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author eio
 * @since 2023-04-28
 */
public interface TeacherService extends IService<Teacher> {

    IPage<Teacher> findQueryPage(Page<Teacher> page,TeacherQueryVo teacherQueryVo);
}

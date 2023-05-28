package com.eio.ggkt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eio.ggkt.model.vod.Course;
import com.eio.ggkt.vo.vod.CoursePublishVo;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据课程id获取课程发布信息
     * @param id
     * @return
     */
    CoursePublishVo selectCoursePublishVoById(Long id);
}

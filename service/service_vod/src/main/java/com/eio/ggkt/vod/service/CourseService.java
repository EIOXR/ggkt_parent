package com.eio.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.ggkt.model.vod.Course;
import com.eio.ggkt.vo.vod.CourseFormVo;
import com.eio.ggkt.vo.vod.CoursePublishVo;
import com.eio.ggkt.vo.vod.CourseQueryVo;

import java.util.Map;

public interface CourseService extends IService<Course> {

    /**
     * 分页查询课程信息
     * @param page
     * @param courseQueryVo
     * @return
     */
    //IPage<Course> getAllCourseByPage(Page<Course> page, CourseQueryVo courseQueryVo);

    /**
     * 分页查询课程信息
     * @param page
     * @param courseQueryVo
     * @return
     */
    Map<String, Object> findPage(Page<Course> page, CourseQueryVo courseQueryVo);

    /**
     * 课程添加
     * @param courseFormVo
     * @return
     */
    Long saveCourseInfo(CourseFormVo courseFormVo);

    /**
     * 根据id获取课程信息
     * @param id
     * @return
     */
    CourseFormVo getCourseInfoById(Long id);

    /**
     * 修改课程信息
     * @param courseFormVo
     */
    void updateCourseId(CourseFormVo courseFormVo);

    /**
     * 根据id获取课程发布信息
     * @param id
     * @return
     */
    CoursePublishVo getCoursePublishVo(Long id);

    /**
     * 根据id发布课程
     * @param id
     * @return
     */
    boolean publishCourseById(Long id);

    /**
     * 根据课程id删除课程
     * @param id
     * @return
     */
    boolean deleteCourseById(Long id);
}

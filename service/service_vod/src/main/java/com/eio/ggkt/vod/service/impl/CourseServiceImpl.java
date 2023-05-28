package com.eio.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.ggkt.model.vod.Course;
import com.eio.ggkt.model.vod.CourseDescription;
import com.eio.ggkt.model.vod.Subject;
import com.eio.ggkt.model.vod.Teacher;
import com.eio.ggkt.vo.vod.CourseFormVo;
import com.eio.ggkt.vo.vod.CoursePublishVo;
import com.eio.ggkt.vo.vod.CourseQueryVo;
import com.eio.ggkt.vod.mapper.CourseDescriptionMapper;
import com.eio.ggkt.vod.mapper.CourseMapper;
import com.eio.ggkt.vod.mapper.SubjectMapper;
import com.eio.ggkt.vod.mapper.TeacherMapper;
import com.eio.ggkt.vod.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    /**
     * 实现分页查询课程信息
     *
     * @param page
     * @param courseQueryVo
     * @return
     */
    /*@Override
    public IPage<Course> getAllCourseByPage(Page<Course> page, CourseQueryVo courseQueryVo) {

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //获取courseQueryVo对象中的数据，即查询条件
        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();
        Long teacherId = courseQueryVo.getTeacherId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();

        if (title != null && !title.equals("")) {
            wrapper.like("title", title);
        }
        if (subjectId != null) {
            wrapper.eq("subject_id", subjectId);
        }
        if (teacherId != null) {
            wrapper.eq("teacher_id", teacherId);
        }
        if (subjectParentId != null) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }

        //返回查询结果
        return null;
    }*/

    /**
     * 实现分页查询课程信息
     *
     * @param page
     * @param courseQueryVo
     * @return
     */
    @Override
    public Map<String, Object> findPage(Page<Course> page, CourseQueryVo courseQueryVo) {

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //获取courseQueryVo对象中的数据，即查询条件
        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();
        Long teacherId = courseQueryVo.getTeacherId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();

        if (title != null && !title.equals("")) {
            wrapper.like("title", title);
        }
        if (subjectId != null) {
            wrapper.eq("subject_id", subjectId);
        }
        if (teacherId != null) {
            wrapper.eq("teacher_id", teacherId);
        }
        if (subjectParentId != null) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }

        Page<Course> coursePage = courseMapper.selectPage(page, wrapper);

        //将coursePage中属性取出，进行进一步封装
        long total = coursePage.getTotal();
        long pages = coursePage.getPages();


        /*List<Course> list = coursePage.getRecords();

        List<Course> records = new ArrayList<>();
        for (Course course : list) {
            records.add(getNameById(course));
        }*/


        //简写，使用list集合的stream流
        List<Course> records = coursePage.getRecords();
        records.stream().forEach(this::getNameById);


        //将数据封装到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("pages", pages);
        map.put("records", records);

        return map;
    }

    private Course getNameById(Course course) {

        Teacher teacher = teacherMapper.selectById(course.getTeacherId());

        if (teacher != null) {
            course.getParam().put("teacherName", teacher.getName());
        }

        //获取讲师和分类名称
        //一层分类

        Subject subjectOne = subjectMapper.selectById(course.getSubjectParentId());
        if (subjectOne != null) {
            course.getParam().put("subjectParentTitle", subjectOne.getTitle());
        }
        Subject subjectTwo = subjectMapper.selectById(course.getSubjectId());
        if (subjectTwo != null) {
            course.getParam().put("subjectTitle", subjectTwo.getTitle());
        }
        return course;
    }

    /**
     * 实现添加课程信息
     * @param courseFormVo
     * @return
     */
    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //添加课程基本信息，操作course表
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        courseMapper.insert(course);

        //添加课程描述信息，操作course_description表
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        //设置课程的id
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    /**
     * 实现根据课程id获取课程信息
     * @param id
     * @return
     */
    @Override
    public CourseFormVo getCourseInfoById(Long id) {

        //课程基本信息
        Course course = courseMapper.selectById(id);
        if (course == null) {
            return null;
        }
        //课程描述信息
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);

        if (courseDescription != null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }

        return courseFormVo;
    }

    /**
     * 实现修改课程信息
     * @param courseFormVo
     */
    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {
        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        courseMapper.updateById(course);

        //修改课程描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseFormVo.getId());
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescriptionMapper.updateById(courseDescription);
    }

    /**
     * 实现根据id获取课程发布信息
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return courseMapper.selectCoursePublishVoById(id);
    }


    @Override
    public boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setPublishTime(new Date());
        course.setStatus(1);
        return this.updateById(course);
    }
}

package com.eio.ggkt.vod.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.ggkt.model.vod.Course;
import com.eio.ggkt.result.Result;
import com.eio.ggkt.vo.vod.CourseFormVo;
import com.eio.ggkt.vo.vod.CourseQueryVo;
import com.eio.ggkt.vod.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "课程管理接口")
@RestController
@RequestMapping("/vod/course")
@CrossOrigin//跨域
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("获取分页列表")
    @GetMapping("/{pageNo}/{pageSize}")
    public Result index(
            @PathVariable("pageNo") Long pageNo,
            @PathVariable("pageSize") Long pageSize,
            CourseQueryVo courseQueryVo
    ){
        //设置Page条件
        Page<Course> page = new Page<>(pageNo, pageSize);
        //调用Service层方法，传入查询条件和分页对象page
        //IPage<Course> iPage = courseService.getAllCourseByPage(page,courseQueryVo);

        Map<String,Object> map = courseService.findPage(page, courseQueryVo);

        return Result.ok(map);
    }

    /**
     * 添加课程信息请求
     * @param courseFormVo
     * @return
     */
    @ApiOperation("添加课程基本信息")
    @PostMapping("/save")
    public Result save(@RequestBody CourseFormVo courseFormVo){

        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    /**
     * id获取课程信息请求
     * @param id
     * @return
     */
    @ApiOperation("根据id获取课程信息")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){
        CourseFormVo courseFormVo = courseService.getCourseInfoById(id);
        return Result.ok(courseFormVo);
    }


    @ApiOperation("修改课程信息")
    @PutMapping("/update")
    public Result update(@RequestBody CourseFormVo courseFormVo){
        courseService.updateCourseId(courseFormVo);
        return Result.ok(null);
    }
}



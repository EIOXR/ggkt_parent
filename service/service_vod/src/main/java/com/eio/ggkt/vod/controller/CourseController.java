package com.eio.ggkt.vod.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.ggkt.model.vod.Course;
import com.eio.ggkt.result.Result;
import com.eio.ggkt.vo.vod.CourseFormVo;
import com.eio.ggkt.vo.vod.CoursePublishVo;
import com.eio.ggkt.vo.vod.CourseQueryVo;
import com.eio.ggkt.vod.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
//        //从中获取到课程ID返回前端以实现保存下一步成功进入
//        Long courseId = courseFormVo.getId();
        courseService.updateCourseId(courseFormVo);
        return Result.ok(courseFormVo.getId());
    }


    @ApiOperation("根据课程id获取课程发布信息")
    @GetMapping("/getCoursePublishVo/{id}")
    public Result getCoursePublishVo(@PathVariable Long id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }



    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(@ApiParam(value = "课程ID", required = true)@PathVariable Long id){
        return courseService.publishCourseById(id) ? Result.ok(null) : Result.fail(null).message("发布失败，请重试！");
    }


    @ApiOperation("删除课程")
    @DeleteMapping("deleteCourseById/{id}")
    public Result deleteCourseById(@PathVariable Long id){
        return courseService.deleteCourseById(id) ? Result.ok(null) : Result.fail(null).message("删除失败，请重试！");
    }
}



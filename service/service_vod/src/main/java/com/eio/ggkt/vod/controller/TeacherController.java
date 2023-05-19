package com.eio.ggkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.ggkt.model.vod.Teacher;
import com.eio.ggkt.result.Result;
import com.eio.ggkt.vo.vod.TeacherQueryVo;
import com.eio.ggkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author eio
 * @since 2023-04-28
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/vod/teacher")
@CrossOrigin //跨域
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    /**
     * 查询所有教师信息
     *
     * @return
     */
    @ApiOperation("所有讲师列表")
    @GetMapping("/getAllTeacher")
    public Result getAllTeacher() {

        List<Teacher> list = teacherService.list();

        return Result.ok(list);
    }


    /**
     * 根据id删除教师信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id删除教师")
    @DeleteMapping("/deleteTeacherById/{deleteId}")
    public Result deleteTeacherById(@PathVariable("deleteId") Integer id) {

        boolean isSuccess = teacherService.removeById(id);

        return Result.ok(isSuccess);

    }


    /**
     * 条件查询分页
     *
     * @param pageNo         当前 页
     * @param pageSize       每页大小
     * @param teacherQueryVo 封装的实体类，用于获取查询条件
     * @return
     */
    @ApiOperation("条件查询分页")
    @PostMapping("/findQueryPage/{pageNo}/{pageSize}")
    public Result findPage(
            @PathVariable long pageNo,
            @PathVariable long pageSize,
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo
    ) {
/*        //判断teacherQueryVo是否为空
        if (teacherQueryVo == null) {
            //查询全部
            List<Teacher> list = teacherService.list();
            return Result.ok(list);
        } else {

        }*/
        //创建page对象
        Page<Teacher> page = new Page<>(pageNo, pageSize);
        if (teacherQueryVo == null) {
            IPage<Teacher> iPage = teacherService.page(page);
            return Result.ok(iPage);
        } else {
            IPage<Teacher> iPage = teacherService.findQueryPage(page, teacherQueryVo);
            return Result.ok(iPage);
        }
    }


    /**
     * 处理添加教师请求
     * @param teacher
     * @return
     */
    @ApiOperation("添加教师")
    @PostMapping("/addTeacher")
    public Result insertTeacher(@RequestBody Teacher teacher){
        return teacherService.save(teacher) ? Result.ok(null) : Result.fail(null).message("添加失败，请重试");
    }


    /**
     * 根据id查询教师
     * @param id
     * @return
     */
    @ApiOperation("根据id查询教师")
    @GetMapping("/getTeacherById/{id}")
    public Result getTeacherById(@PathVariable("id") Integer id){

        Teacher teacher = teacherService.getById(id);

        return Result.ok(teacher);
    }


    /**
     * 修改教师
     * @param teacher
     * @return
     */
    @ApiOperation("修改教师")
    @PutMapping("/updateTeacher")
    public Result updateTeacherById(@RequestBody Teacher teacher){
        return teacherService.updateById(teacher) ? Result.ok(null) : Result.fail(null).message("修改失败，请重试");
    }


    /**
     * 批量删除教师
     * @param ids
     * @return
     */
    @ApiOperation("批量删除老师")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@RequestBody List<Integer> ids){
        return teacherService.removeByIds(ids) ? Result.ok(null) : Result.fail(null).message("删除失败，请重试");
    }
}


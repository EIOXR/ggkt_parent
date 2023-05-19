package com.eio.ggkt.vod.controller;


import com.eio.ggkt.model.vod.Subject;
import com.eio.ggkt.result.Result;
import com.eio.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author eio
 * @since 2023-05-08
 */
@Api(tags = {"课程分类接口"})
@RestController
@RequestMapping("/vod/subject")
@CrossOrigin//跨域
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    /**
     * 课程分类列表
     * 每次查询一层数据
     * 懒加载
     * @param id
     * @return
     */
    @ApiOperation("课程分类列表")
    @GetMapping("/getChildSubject/{id}")
    private Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.ok(list);
    }


    /**
     * 课程分类导出请求
     * @param response
     */
    @ApiOperation("课程分类导出")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response){
        subjectService.exportData(response);
    }


    @ApiOperation("课程分类导入")
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        subjectService.importData(file);
        return Result.ok(null);
    }

}


package com.eio.ggkt.vod.controller;


import com.eio.ggkt.model.vod.Chapter;
import com.eio.ggkt.result.Result;
import com.eio.ggkt.vo.vod.ChapterVo;
import com.eio.ggkt.vod.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "章节管理接口")
@RestController
@RequestMapping("/vod/chapter")
@CrossOrigin//跨域
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //1.大纲列表（章节和小节列表）
    @ApiOperation("课程大纲")
    @GetMapping("/getNestedTreeList/{courseId}")
    public Result getTreeList(@PathVariable Long courseId) {
        List<ChapterVo> list = chapterService.getTreeList(courseId);
        return list != null ? Result.ok(list) : Result.fail(null).message("未查询到");
    }

    //2.添加章节
    @ApiOperation("添加章节")
    @PostMapping("/saveChapter")
    public Result saveChapter(@RequestBody Chapter chapter) {
         return chapterService.save(chapter) ? Result.ok(null) : Result.fail(null).message("操作失败，请重试！");
    }

    //3.修改-根据id查询
    @ApiOperation("根据id查询章节")
    @GetMapping("/getChapterById/{id}")
    public Result getChapterById(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    //4.修改-最终实现
    @ApiOperation("修改课程")
    @PutMapping("/updateChapter")
    public Result updateChapter(@RequestBody Chapter chapter){
        return chapterService.updateById(chapter) ? Result.ok(null) : Result.fail(null).message("操作失败，请重试！");
    }

    //5.删除功能
    @ApiOperation("删除章节")
    @DeleteMapping("/deleteChapterById/{id}")
    public Result deleteChapterById(@PathVariable Long id){
        return chapterService.removeById(id) ?  Result.ok(null) : Result.fail(null).message("操作失败，请重试！");
    }
}

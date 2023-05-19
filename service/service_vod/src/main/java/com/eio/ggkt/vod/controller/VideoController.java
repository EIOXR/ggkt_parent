package com.eio.ggkt.vod.controller;


import com.eio.ggkt.model.vod.Video;
import com.eio.ggkt.result.Result;
import com.eio.ggkt.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "小节管理接口")
@RestController
@RequestMapping("/vod/video")
 @CrossOrigin//跨域
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("获取小节")
    @GetMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.ok(video);
    }


    @ApiOperation("新增")
    @PostMapping("/saveVideo")
    public Result saveVideo(@RequestBody Video video) {
        return videoService.save(video) ? Result.ok(null) : Result.fail(null).message("操作失败，请重试！");
    }

    @ApiOperation("修改")
    @PutMapping("/updateVideo")
    public Result updateVideo(@RequestBody Video video) {
        return videoService.updateById(video) ? Result.ok(null) : Result.fail(null ).message("操作失败，请重试");
    }

    @ApiOperation("删除")
    @DeleteMapping("/deleteVideoById/{id}")
    public Result deleteVideoById(@PathVariable Long id) {
        return videoService.removeById(id) ? Result.ok(null) : Result.fail(null).message("操作失败，请重试！");
    }


}

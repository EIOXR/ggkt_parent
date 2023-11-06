package com.eio.ggkt.vod.controller;


import com.eio.ggkt.result.Result;
import com.eio.ggkt.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author eio
 * @since 2023-05-19
 */
@Api(tags = "videoVisitor管理")
@RestController
@RequestMapping("/vod/video-visitor")
@CrossOrigin//跨域
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService videoVisitorService;


    /**
     * 返回用户统计数据
     *
     * @param courseId
     * @param startDate
     * @param endDate
     * @return
     */
    @ApiOperation("显示统计数据")
    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result findCount(
            @PathVariable Long courseId,
            @PathVariable String startDate,
            @PathVariable String endDate
    ) {

        Map<String, Object> map =
                videoVisitorService.findCount(courseId, startDate, endDate);

        return Result.ok(map);
    }

}


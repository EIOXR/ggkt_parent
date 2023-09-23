package com.eio.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.ggkt.model.vod.Video;

public interface VideoService extends IService<Video> {

    /**
     * 根据课程ID删除视频小节
     * @param id
     */
    void deleteVideoByCourseId(Long id);
}

package com.eio.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.ggkt.model.vod.Course;
import com.eio.ggkt.model.vod.Video;
import com.eio.ggkt.vod.mapper.VideoMapper;
import com.eio.ggkt.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


    @Autowired
    private VideoMapper videoMapper;

    /**
     * 根据课程id删除课程小节（视频）
     * @param id
     */
    @Override
    public void deleteVideoByCourseId(Long id) {

        QueryWrapper<Video> wrapper = new QueryWrapper<>();

    }
}

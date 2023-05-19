package com.eio.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.ggkt.model.vod.Video;
import com.eio.ggkt.vod.mapper.VideoMapper;
import com.eio.ggkt.vod.service.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
}

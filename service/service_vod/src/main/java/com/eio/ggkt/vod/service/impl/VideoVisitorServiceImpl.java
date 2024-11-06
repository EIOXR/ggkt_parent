package com.eio.ggkt.vod.service.impl;

import com.eio.ggkt.model.vod.VideoVisitor;
import com.eio.ggkt.vo.vod.VideoVisitorCountVo;
import com.eio.ggkt.vod.mapper.VideoVisitorMapper;
import com.eio.ggkt.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author eio
 * @since 2023-05-19
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {


    @Autowired
    private VideoVisitorMapper videoVisitorMapper;


    /**
     * 实现返回当前时间段用户观看数量的集合
     * @param courseId
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {

        //调用Mapper的方法
        List<VideoVisitorCountVo> videoVisitorVoList =
                videoVisitorMapper.findCount(courseId,startDate,endDate);


        //创建map集合封装数据
        HashMap<String, Object> map = new HashMap<>();

        //创建两个list集合，一个存放日期数据，一个存放用户数量
        //从videoVisitorVoList中取出数据放入list集合中
        List<String> dateList = videoVisitorVoList.stream()
                .map(VideoVisitorCountVo::getJoinTime)
                .collect(Collectors.toList());

        List<Integer> userCountList = videoVisitorVoList.stream()
                .map(VideoVisitorCountVo::getUserCount)
                .collect(Collectors.toList());


        //将数据放入map集合中
        map.put("xData",dateList);
        map.put("yData",userCountList);

        return map;
    }
}

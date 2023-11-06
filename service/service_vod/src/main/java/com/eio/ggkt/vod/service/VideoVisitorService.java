package com.eio.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.ggkt.model.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author eio
 * @since 2023-05-19
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    /**
     * 返回当前时间段用户观看数量的集合
     * @param courseId
     * @param startDate
     * @param endDate
     * @return
     */
    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}

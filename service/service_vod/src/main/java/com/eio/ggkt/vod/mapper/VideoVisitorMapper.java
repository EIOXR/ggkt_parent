package com.eio.ggkt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eio.ggkt.model.vod.VideoVisitor;
import com.eio.ggkt.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author eio
 * @since 2023-05-19
 */
@Repository
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> findCount(@Param("courseId")Long courseId,
                                        @Param("startDate")String startDate,
                                        @Param("endDate") String endDate);
}

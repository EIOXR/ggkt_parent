package com.eio.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.ggkt.model.vod.Chapter;
import com.eio.ggkt.vo.vod.ChapterVo;

import java.util.List;

public interface ChapterService extends IService<Chapter> {

    /**
     * 获取课程大纲
     * @param courseId
     * @return
     */
    List<ChapterVo> getTreeList(Long courseId);

    /**
     * 根据课程id删除课程章节
     * @param id
     */
    void deleteChapterByCourseId(Long id);

}

package com.eio.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.ggkt.model.vod.Chapter;
import com.eio.ggkt.model.vod.Video;
import com.eio.ggkt.vo.vod.ChapterVo;
import com.eio.ggkt.vo.vod.VideoVo;
import com.eio.ggkt.vod.mapper.ChapterMapper;
import com.eio.ggkt.vod.mapper.VideoMapper;
import com.eio.ggkt.vod.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 实现获取课程章节
     *
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getTreeList(Long courseId) {

        QueryWrapper<Chapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", courseId);
        chapterWrapper.orderByAsc("sort", "id");

        //根据课程id获取章节chapters集合对象
        List<Chapter> chapters = chapterMapper.selectList(chapterWrapper);
        if (chapters.size() != 0) {
            //用于查询video的wrapper对象
            QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
            //用于将ChapterVo对象封装到list集合
            List<ChapterVo> voList = new ArrayList<>();

            //循环遍历出Chapter的id去查询video对象，分别封装到vo对象中，存入集合
            for (Chapter chapter : chapters) {

                ChapterVo chapterVo = new ChapterVo();
                BeanUtils.copyProperties(chapter, chapterVo);
                voList.add(chapterVo);
                //根据chapterId获取视频信息
                //封装到videoVo对象
                videoWrapper.eq("chapter_id", chapter.getId());
                List<Video> videos = videoMapper.selectList(videoWrapper);
                videoWrapper.clear();
                //用于将videoVo对象封装到list集合
                List<VideoVo> videoVoList = new ArrayList<>();
                if (videos.size() != 0) {
                    //通过外层取得的chapterId查询出video信息，并封装到videoVo对象中，存入videoVoList集合
                    for (Video video : videos) {
                        VideoVo videoVo = new VideoVo();
                        BeanUtils.copyProperties(video, videoVo);
                        videoVoList.add(videoVo);
                    }
                } else {
                    chapterVo.setChildren(null);
                }

                chapterVo.setChildren(videoVoList);
            }
            return voList;
        }
        //[ChapterVo(id=1, title=第七章：I/O流, sort=7, children=[]),
        // ChapterVo(id=15, title=第一章：Java入门, sort=1,
        //          children=[
        //                  VideoVo(id=33, title=第一节：Java简介, isFree=1, sort=1, videoSourceId=3b71d85d93554e7dbb59becdf823f63d),
        //                  VideoVo(id=34, title=第二节：表达式和赋值语句, isFree=1, sort=2, videoSourceId=3b71d85d93554e7dbb59becdf823f63d),
        //                  VideoVo(id=35, title=第三节：String类, isFree=0, sort=3, videoSourceId=3b71d85d93554e7dbb59becdf823f63d),
        //                  VideoVo(id=36, title=第四节：程序风格, isFree=0, sort=4, videoSourceId=3b71d85d93554e7dbb59becdf823f63d)]),
        // ChapterVo(id=16, title=第二章：控制台输入和输出, sort=2, children=[VideoVo(id=1, title=第一节, isFree=1, sort=0, videoSourceId=3b71d85d93554e7dbb59becdf823f63d), VideoVo(id=5, title=IO流基础, isFree=1, sort=0, videoSourceId=3b71d85d93554e7dbb59becdf823f63d)]),
        // ChapterVo(id=17, title=第三章：控制流, sort=3, children=[]),
        // ChapterVo(id=18, title=第四章：类的定义, sort=4, children=[]),
        // ChapterVo(id=19, title=第五章：数组, sort=5, children=[]),
        // ChapterVo(id=20, title=第六章：继承, sort=6, children=[])]

        return null;
    }


    /**
     * 根据课程id删除课程章节
     *
     * @param id
     */
    @Override
    public void deleteChapterByCourseId(Long id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        chapterMapper.delete(wrapper);
    }
}

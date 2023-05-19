package com.eio.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.ggkt.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author eio
 * @since 2023-05-08
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 每次查询一层数据
     * @param id
     * @return
     */
    List<Subject> selectSubjectList(Long id);

    /**
     * 课程分类导出
     * @param response
     */
    void exportData(HttpServletResponse response);

    /**
     * 课程分类导入
     * @param file
     */
    void importData(MultipartFile file);
}

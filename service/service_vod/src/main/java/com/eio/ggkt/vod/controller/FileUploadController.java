package com.eio.ggkt.vod.controller;


import com.eio.ggkt.result.Result;
import com.eio.ggkt.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/vod/file")
@CrossOrigin//跨域
public class FileUploadController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传功能
     * @param file
     * @return
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file){
        String url = fileService.upload(file);
        return Result.ok(url).message("文件上传成功");
    }
}

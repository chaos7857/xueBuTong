package com.cc.xuebutongbackend.file.controller;

import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.model.dto.BaseResponse;
import com.cc.xuebutongbackend.common.utils.ResultUtils;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.file.constant.FileDefault;
import com.cc.xuebutongbackend.file.model.service.FileService;
import com.cc.xuebutongbackend.file.utils.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RequestMapping("/file")
@RestController
public class FileDemoController {
    @Resource
    FileService fileService;

    // @RequireRole
    @PostMapping("/upload/demo/local")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();// "1.png"

//        String contentType = file.getContentType();// "image/png"
//        String name = file.getName();// "file"
//        long size = file.getSize();// "2018089"
//        Resource resource = file.getResource();
        /*
         * 不管是本地还是远程
         *
         * 将url（url可以加上userid来进行分文件夹）
         * fileName
         * contentType
         * fileSize
         * 写入数据库，
         * 当然，还需要传递introduction
         * 以及tags
         * 然后从request中获取userId进行记录
         * */
        // ==================================================================================
        // 将文件保存在本地位置上
        File uploads = new File(FileDefault.LOCAL_PATH);
        if (!uploads.exists()) {
            ThrowUtils.throwIf(
                    !uploads.mkdirs(), ErrorCode.OPERATION_ERROR
            );
        }
        String absolutePath = uploads.getAbsolutePath();
        originalFilename = FileUtils.fileRename(originalFilename);
        File dest_file = new File(absolutePath + File.separator + originalFilename);
        try {
            file.transferTo(dest_file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResultUtils.success(dest_file.getAbsolutePath());
    }

    @GetMapping("/download/demo/local")
    public void downloadFile(String fileName, HttpServletResponse response) throws IOException {
        /*
        * 从请求获取文件名
        * 依据名检索实际url
        * 返回url所在的文件
        * 同时可以返回介绍等信息
        * */
        // 修改返回头
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        // 发送数据
        byte[] buffer = new byte[1024];
        int len = 0;
        try (FileInputStream fileInputStream = new FileInputStream(FileDefault.LOCAL_PATH +fileName)) {
            while ((len = fileInputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

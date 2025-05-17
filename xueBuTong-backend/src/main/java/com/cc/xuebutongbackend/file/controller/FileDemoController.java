package com.cc.xuebutongbackend.file.controller;

import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.model.dto.BaseResponse;
import com.cc.xuebutongbackend.common.utils.ResultUtils;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.file.utils.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequestMapping("/file")
@RestController
public class FileDemoController {
    @PostMapping("/upload/demo/local")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();// "1.png"
//        String contentType = file.getContentType();// "image/png"
//        String name = file.getName();// "file"
//        long size = file.getSize();// "2018089"
//        Resource resource = file.getResource();
        // ==================================================================================
        File uploads = new File("uploads");
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

}

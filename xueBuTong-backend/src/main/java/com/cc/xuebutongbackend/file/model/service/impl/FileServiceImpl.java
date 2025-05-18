package com.cc.xuebutongbackend.file.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.xuebutongbackend.file.model.entity.File;
import com.cc.xuebutongbackend.file.model.service.FileService;
import com.cc.xuebutongbackend.file.model.mapper.FileMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【file】的数据库操作Service实现
* @createDate 2025-05-18 08:01:48
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File>
    implements FileService{

}





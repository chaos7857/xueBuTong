package com.cc.xuebutongbackend.file.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;

public class FileUtils {
    public static String fileRename(String filename){
        UUID uuid = UUID.randomUUID();
        String suffix = FileUtil.getSuffix(filename);
        return uuid.toString()+ '.' + suffix;

        /*
        // ==============================================================
        // deepseek的方法，剪断uuid
        // 1. 清洗文件名，防止路径遍历
        String safeName = Paths.get(filename).getFileName().toString();
        // 2. 分离文件名主干和扩展名
        String mainName = FilenameUtils.getBaseName(safeName);
        String extension = FilenameUtils.getExtension(safeName);
        // 3. 处理特殊字符（可选）
        mainName = mainName.replaceAll("[^a-zA-Z0-9]", "_");
        // 4. 生成短 UUID（8位）
        String shortUuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        // 5. 拼接新文件名
        return String.format("%s_%s.%s", mainName, shortUuid, extension);

        */
    }
}

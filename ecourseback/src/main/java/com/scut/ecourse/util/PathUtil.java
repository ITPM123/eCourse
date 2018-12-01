package com.scut.ecourse.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class PathUtil {
    /**
     * 获取运行java -jar 命令所在目录下的指定目录
     */
    public static File getSaveDir(String subDir){
        if (subDir==null || subDir.equals("")) {
            subDir = "/static/images/upload/";
        }
        //获得上传文件路径
        File path;
        try{
            File root = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!root.exists()) root = new File("");
            path = new File(root.getAbsolutePath(),subDir);
            if (!path.exists())
                path.mkdirs();
            return  path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.hystrix.notice.util;

import com.hystrix.notice.common.ErrorCode;
import com.hystrix.notice.exception.BusinessException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Util {
    private final String[] validExtensions = { "jpg", "jpeg", "png", "gif" };

    public static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == filePath.length() - 1) {
            return ""; // 文件名中没有后缀或者后缀为空
        }
        return filePath.substring(dotIndex + 1).toLowerCase();
    }

    public static boolean checkSuffix(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String suffix = getFileExtension(fileName);
        return isImageExtensionValid(suffix);
    }

    public static boolean isImageExtensionValid(String suffix) {
        String[] validExtensions = { "jpg", "jpeg", "png", "gif" };
        for (String extension : validExtensions) {
            if (extension.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> addToDirectory(MultipartFile[] file) throws IOException {
        String path="";
        List<String> strings = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            String suffix = getFileExtension(multipartFile.getOriginalFilename());
            String UUID= java.util.UUID.randomUUID().toString();
            path="C:\\Users\\zjh\\Desktop\\新建文件夹\\img\\"+"\\"+ UUID +  "." + suffix;
            FileUtils.writeByteArrayToFile(new File(path),multipartFile.getBytes());
            strings.add(path);
        }
        return strings;
    }


}

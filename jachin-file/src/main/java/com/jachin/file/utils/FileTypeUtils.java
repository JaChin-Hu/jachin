package com.jachin.file.utils;

import cn.hutool.core.io.FileTypeUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/24 17:39
 */
public class FileTypeUtils {
    private FileTypeUtils() {
    }

    private static final String IMAGE_TYPE = "image/";
    private static final String AUDIO_TYPE = "audio/";
    private static final String VIDEO_TYPE = "video/";
    private static final String APPLICATION_TYPE = "application/";
    private static final String TEXT_TYPE = "text/";
    private static final String[] IMAGE_EXT = {"JPG", "JPEG", "GIF", "PNG", "BMP", "PCX", "TGA", "PSD", "TIFF"};
    private static final String[] AUDIO_EXT = {"mp3", "OGG", "WAV", "REAL", "APE", "MODULE", "MIDI", "VQF", "CD"};
    private static final String[] VIDEO_EXT = {"mp4", "avi", "MPEG-1", "RM", "ASF", "WMV", "qlv", "3gp", "MPEG-2", "mov", "MPEG4"};
    private static final String[] APPLICATION_EXT = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "zip", "jar"};
    private static final String[] TEXT_EXT = {"txt"};


    public static String getFileType(MultipartFile multipartFile) {
        InputStream inputStream;
        String type;
        try {
            inputStream = multipartFile.getInputStream();
            type = FileTypeUtil.getType(inputStream);
            for (String ext : IMAGE_EXT) {
                if (ext.equalsIgnoreCase(type)) {
                    return IMAGE_TYPE + type;
                }
            }
            for (String ext : AUDIO_EXT) {
                if (ext.equalsIgnoreCase(type)) {
                    return AUDIO_TYPE + type;
                }
            }
            for (String ext : VIDEO_EXT) {
                if (ext.equalsIgnoreCase(type)) {
                    return VIDEO_TYPE + type;
                }
            }
            for (String ext : APPLICATION_EXT) {
                if (ext.equalsIgnoreCase(type)) {
                    return APPLICATION_TYPE + type;
                }
            }
            for (String ext : TEXT_EXT) {
                if (ext.equalsIgnoreCase(type)) {
                    return TEXT_TYPE + type;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

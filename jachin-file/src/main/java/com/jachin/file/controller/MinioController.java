package com.jachin.file.controller;

import com.jachin.file.service.MinioService;
import com.jachin.file.utils.FileTypeUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/24 17:20
 */
@RequestMapping("/minio")
@RestController
public class MinioController {
    private final MinioService minioService;

    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public String uploadFile(String bucketName, MultipartFile file) {
        String fileType = FileTypeUtils.getFileType(file);
        if (fileType != null) {
            return minioService.putObject(file, bucketName, fileType);
        }
        return "不支持的文件格式。请确认格式,重新上传！！！";
    }

    @PostMapping("/addBucket/{bucketName}")
    public String addBucket(@PathVariable String bucketName) {
        minioService.makeBucket(bucketName);
        return "创建成功！！！";
    }

    @GetMapping("/show/{bucketName}")
    public List<String> show(@PathVariable String bucketName) {
        return minioService.listObjectNames(bucketName);
    }

    @GetMapping("/showBucketName")
    public List<String> showBucketName() {
        return minioService.listBucketName();
    }

    @GetMapping("/showListObjectNameAndDownloadUrl/{bucketName}")
    public Map<String, String> showListObjectNameAndDownloadUrl(@PathVariable String bucketName) {
        Map<String, String> map = new HashMap<>();
        List<String> listObjectNames = minioService.listObjectNames(bucketName);
        String url = "localhost:8085/minio/download/" + bucketName + "/";
        listObjectNames.forEach(System.out::println);
        for (int i = 0; i <listObjectNames.size() ; i++) {
            map.put(listObjectNames.get(i),url+listObjectNames.get(i));
        }
        return map;
    }

    @DeleteMapping("/removeBucket/{bucketName}")
    public String delBucketName(@PathVariable String bucketName) {
        return minioService.removeBucket(bucketName) == true ? "删除成功" : "删除失败";
    }

    @DeleteMapping("/removeObject/{bucketName}/{objectName}")
    public String delObject(@PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {
        return minioService.removeObject(bucketName, objectName) == true ? "删除成功" : "删除失败";
    }

    @DeleteMapping("/removeListObject/{bucketName}")
    public String delListObject(@PathVariable("bucketName") String bucketName, @RequestBody List<String> objectNameList) {
        return minioService.removeListObject(bucketName, objectNameList) == true ? "删除成功" : "删除失败";
    }


    @RequestMapping("/download/{bucketName}/{objectName}")
    public void download(HttpServletResponse response, @PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {
        InputStream in = null;
        try {
            in = minioService.downloadObject(bucketName, objectName);
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(objectName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            //将字节从InputStream复制到OutputStream 。
            IOUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

package com.jachin.file.service;

import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/24 17:33
 */
public interface MinioService {
    /**
     * 判断 bucket是否存在
     *
     * @param bucketName bucketName
     * @return 是否存在
     */
    boolean bucketExists(String bucketName);

    /**
     * 创建 bucket
     *
     * @param bucketName bucketName
     */
    void makeBucket(String bucketName);

    /**
     * 列出所有存储桶名称
     *
     * @return bucketName List
     */
    List<String> listBucketName();

    /**
     * 列出所有存储桶 信息
     *
     * @return buckets
     */
    List<Bucket> listBuckets();

    /**
     * 根据桶名删除桶
     *
     * @param bucketName bucketName
     * @return 删除是否成功
     */
    boolean removeBucket(String bucketName);

    /**
     * 列出存储桶中的所有对象名称
     *
     * @param bucketName bucketName
     * @return listObjectNames
     */
    List<String> listObjectNames(String bucketName);

    /**
     * 文件上传
     *
     * @param multipartFile multipartFile
     * @param bucketName bucketName
     * @param fileType fileType
     * @return ?
     */
    String putObject(MultipartFile multipartFile, String bucketName, String fileType);

    /**
     * 文件流下载
     *
     * @param bucketName bucketName
     * @param objectName objectName
     * @return InputStream
     */
    InputStream downloadObject(String bucketName, String objectName);


    /**
     * 删除文件
     *
     * @param bucketName bucketName
     * @param objectName objectName
     * @return 是否成功删除
     */
    boolean removeObject(String bucketName, String objectName);


    /**
     * 批量删除文件
     *
     * @param bucketName bucketName
     * @param objectNameList objectNameList
     * @return 是否成功删除
     */
    boolean removeListObject(String bucketName, List<String> objectNameList);

    /**
     * 获取文件路径
     *
     * @param bucketName bucketName
     * @param objectName objectName
     * @return 文件路劲
     */
    String getObjectUrl(String bucketName, String objectName);
}

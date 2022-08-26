package com.jachin.file.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/24 17:21
 */
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.port}")
    private Integer port;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.secure}")
    private boolean secure;
    @Value("${minio.bucket-name}")
    private String bucketName;
    @Value("${minio.image-size}")
    private long imageSize;
    @Value("${minio.file-size}")
    private long fileSize;

    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder().endpoint(endpoint, port, secure).credentials(accessKey, secretKey).build();
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Integer getPort() {
        return port;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public boolean isSecure() {
        return secure;
    }

    public String getBucketName() {
        return bucketName;
    }

    public long getImageSize() {
        return imageSize;
    }

    public long getFileSize() {
        return fileSize;
    }
}

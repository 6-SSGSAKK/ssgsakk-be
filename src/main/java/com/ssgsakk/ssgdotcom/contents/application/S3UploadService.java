package com.ssgsakk.ssgdotcom.contents.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public String s3_upload(MultipartFile files){
        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(files.getContentType());
        objectMetadata.setContentLength(files.getSize());

        String storeFilename ="";
        try  {
            String originalFilename = files.getOriginalFilename();
            storeFilename = createStoreFilename(originalFilename);
            InputStream inputStream = files.getInputStream();
            amazonS3Client.putObject(new PutObjectRequest(bucket, "imageupload/"+storeFilename,
                    inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new IllegalStateException("S3 파일 업로드에 실패했습니다.");
        }
        return storeFilename;
    }
    private String createStoreFilename(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(position+1);
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+ext;
    }
}


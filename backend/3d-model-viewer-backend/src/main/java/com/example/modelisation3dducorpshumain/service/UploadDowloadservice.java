package com.example.modelisation3dducorpshumain.service;

import com.example.modelisation3dducorpshumain.utils.FileStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.example.modelisation3dducorpshumain.utils.bucket.BucketName.PROFIL_IMAGE;

@Service
public class UploadDowloadservice {
    private final FileStore fileStore;
    public UploadDowloadservice(FileStore fileStore) {
        this.fileStore = fileStore;
    }


    public String uploadFile(MultipartFile file,String fileType) {
        // 4. Grab some metadata from file any
        String path ;
        String fileName;
        Map<String,String> metadata = new HashMap<>();
        metadata.put("content-type",file.getContentType());
        metadata.put("content-length",String.valueOf(file.getSize()));
        // 5. store the image in s3 storage and update the image link of the user
        path = String.format("%s",PROFIL_IMAGE.getBucketName());
        if(fileType.equals("glb")){
            fileName = String.format("objects/%s-%s",file.getOriginalFilename(), UUID.randomUUID());
        }else{
            fileName = String.format("images/%s-%s",file.getOriginalFilename(), UUID.randomUUID());
        }
        try{
            fileStore.save(path,fileName,Optional.of(metadata),file.getInputStream());
            return fileName;
        }catch (Exception e){
            throw new IllegalStateException("somthing get wrong");
        }
    }

    public byte[] downloadFile(String imageName) throws IOException {
        String path = String.format("%s",PROFIL_IMAGE.getBucketName());

        return fileStore.download(path,imageName);
    }
}

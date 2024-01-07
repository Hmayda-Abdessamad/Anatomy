package com.example.modelisation3dducorpshumain.utils.bucket;

public enum BucketName {
    PROFIL_IMAGE("chat-app-images-123");

    private String bucketName;

    BucketName(String bucketName) {
        this.bucketName=bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}

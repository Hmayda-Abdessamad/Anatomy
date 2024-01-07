package com.example.modelisation3dducorpshumain.utils.bucket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BucketNameProperties {

    @Value("${application.credential.aws.bucket.name}")
    private static String bucketName;

    public static String getName() {
        return bucketName;
    }
}
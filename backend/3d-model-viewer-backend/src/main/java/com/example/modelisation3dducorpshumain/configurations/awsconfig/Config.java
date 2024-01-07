package com.example.modelisation3dducorpshumain.configurations.awsconfig;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${application.credential.aws.access-key}")
    private  String accessKey;
    @Value("${application.credential.aws.secret-key}")
    private  String secretKey;



    @Bean
    public AmazonS3 s3(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        return  AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                awsCredentials
                        )
                ).build();

    }
}

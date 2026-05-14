package com.awsfilestorage.awsfilestorageapp.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.BasicSessionCredentials;

@Configuration
public class AmazonConfig {

//    @Bean
//    public AmazonS3 s3(){
//
//        AWSCredentials awsCredentials = new BasicAWSCredentials(
//                "ASIAUXTADQFVNTEYUZH7",
//                "OXKGSgRy21DbQKtyKoGLlz0LhAfvjKU8JZ+bhjNv"
//
//        );
//        return AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .build();
//    }

    @Bean
    public AmazonS3 s3(){
        return AmazonS3ClientBuilder.standard().build();
    }
    @Bean
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard().build();
    }
}

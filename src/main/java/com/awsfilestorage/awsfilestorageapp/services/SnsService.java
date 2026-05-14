package com.awsfilestorage.awsfilestorageapp.services;

import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnsService {

    private final SnsTemplate snsTemplate;

    @Value("${aws.sns.topic.arn}")
    private String topicArn;

    public void sendNotification(String message) {
        snsTemplate.sendNotification(topicArn, message, "Image Uploaded");
    }
}

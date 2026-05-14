//package com.awsfilestorage.awsfilestorageapp.services;
//
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class SqsService {
//
//    private final AmazonSQS amazonSQS;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private final String QUEUE_URL =
//            "https://sqs.us-east-1.amazonaws.com/325545329002/image-processing-queue";
//
//    public void sendMessage(Object event) {
//        try {
//            String json = objectMapper.writeValueAsString(event);
//            amazonSQS.sendMessage(QUEUE_URL, json);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to send SQS message", e);
//        }
//    }
//}
package com.awsfilestorage.awsfilestorageapp.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SqsService {

    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${aws.sqs.queue.url}")
    private String queueUrl;

    public void sendMessage(Object event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            amazonSQS.sendMessage(queueUrl, json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send SQS message", e);
        }
    }
}
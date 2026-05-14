package com.awsfilestorage.awsfilestorageapp.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUploadedEvent {
    private String eventType;
    private String userId;
    private String fileName;
    private String s3Key;
    private String imageUrl;
}
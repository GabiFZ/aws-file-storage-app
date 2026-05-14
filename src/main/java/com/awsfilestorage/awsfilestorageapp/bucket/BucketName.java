package com.awsfilestorage.awsfilestorageapp.bucket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BucketName {

    PROFILE_IMAGE("image-upload-bucket-3255");

    private final String bucketName;


    
}

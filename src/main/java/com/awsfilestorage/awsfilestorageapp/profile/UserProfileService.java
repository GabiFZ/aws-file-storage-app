package com.awsfilestorage.awsfilestorageapp.profile;

import com.awsfilestorage.awsfilestorageapp.bucket.BucketName;
import com.awsfilestorage.awsfilestorageapp.filestore.FileStore;
import com.awsfilestorage.awsfilestorageapp.services.ImageUploadedEvent;
import com.awsfilestorage.awsfilestorageapp.services.SnsService;
import com.awsfilestorage.awsfilestorageapp.services.SqsService;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;
    private final SqsService sqsService;
    private final SnsService snsService;

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    };

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // 1. Check if the image is not empty
        isFileEmpty(file);


        // 2. If a file is an image

        isImage(file);
        // 3. The user exists in our database

        UserProfile user = getUserProfileOrThrow(userProfileId);


        // 4. Grap some metadata from file if any

        Map<String, String> metadata = extractMetadata(file);


        // 5. Store the image in s3 and update database with s3 image link (useProfileImageLink)
        String bucketName = BucketName.PROFILE_IMAGE.getBucketName();
        String fileName = String.format("%s-%s", file.getOriginalFilename(), user.getUserProfileId()); //UUID.randomUUID();


        String key = user.getUserProfileId() + "/" + fileName;

        try {
            fileStore.save(bucketName, key, Optional.of(metadata), file.getInputStream(), file.getSize());
            //System.out.println("TRIMIT MESAJ IN SQS");


            String url = "https://" + bucketName + ".s3.amazonaws.com/" + key;
            user.setUserProfileImageLink(key);
            userProfileDataAccessService.saveUserProfile(user);

            //sqsService.sendMessage("Image uploaded: " + key);
            ImageUploadedEvent event = new ImageUploadedEvent(
                    "USER_PROFILE_IMAGE_UPLOADED",
                    user.getUserProfileId().toString(),
                    file.getOriginalFilename(),
                    key,
                    url
            );

            sqsService.sendMessage(event);
            snsService.sendNotification(
                    "User " + user.getUsername() + " uploaded image: " + file.getOriginalFilename()
            );
            //System.out.println("MESAJ TRIMIS");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = getUserProfileOrThrow(userProfileId);
        String bucketName = BucketName.PROFILE_IMAGE.getBucketName();

        return user.getUserProfileImageLink()
                .map(key -> fileStore.download(bucketName, key))
                .orElse(new byte[0]);
    }


    private static Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        UserProfile user = userProfileDataAccessService.getUserProfiles().stream().filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId)).findFirst().orElseThrow(() -> new IllegalStateException("UserProfile with id " + userProfileId + " does not found"));
        return user;
    }

    private static void isImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image " + file.getContentType());
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() + "]");
        }
    }


}

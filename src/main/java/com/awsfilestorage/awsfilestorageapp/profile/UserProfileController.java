package com.awsfilestorage.awsfilestorageapp.profile;

import com.awsfilestorage.awsfilestorageapp.services.SqsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final SqsService sqsService;

    @GetMapping
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }
    @PostMapping(
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId, @RequestParam("file") MultipartFile file) throws IOException {

        userProfileService.uploadUserProfileImage(userProfileId, file);
    }
    @GetMapping("{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId) throws IOException {
       return  userProfileService.downloadUserProfileImage(userProfileId);

    }

//    @GetMapping("/test-sqs")
//    public String testSqs() {
//        sqsService.sendMessage("HELLO FROM TEST");
//        return "sent";
//    }
}

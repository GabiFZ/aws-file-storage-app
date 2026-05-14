//package com.awsfilestorage.awsfilestorageapp.profile;
//
//
//import com.awsfilestorage.awsfilestorageapp.datastore.FakeUserProfileDataStore;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class UserProfileDataAccessService {
//
//    private final FakeUserProfileDataStore fakeUserProfileDataStore;
//
//    List<UserProfile> getUserProfiles() {
//        return fakeUserProfileDataStore.getUserProfiles();
//    };
//}

package com.awsfilestorage.awsfilestorageapp.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserProfileDataAccessService {

    private final UserProfileRepository userProfileRepository;

    List<UserProfile> getUserProfiles() {
        return userProfileRepository.findAll();
    }

    void saveUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }
}

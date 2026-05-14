//package com.awsfilestorage.awsfilestorageapp.datastore;
//
//
//import com.awsfilestorage.awsfilestorageapp.profile.UserProfile;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//public class FakeUserProfileDataStore {
//
//    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
//
//    static {
//        USER_PROFILES.add(new UserProfile(UUID.fromString("ca5025bb-bdda-4cec-ba9b-02d9d7b652d6"), "janetjones", null));
//        USER_PROFILES.add(new UserProfile(UUID.fromString("2ba96fa7-0b5c-4b9b-b1f1-757bd6093e3f"), "antoniojunior", null));
//    }
//
//    public List<UserProfile> getUserProfiles() {
//        return USER_PROFILES;
//    }
//}

package com.awsfilestorage.awsfilestorageapp.datastore;

import com.awsfilestorage.awsfilestorageapp.profile.UserProfile;
import com.awsfilestorage.awsfilestorageapp.profile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserProfileRepository userProfileRepository;

    @Override
    public void run(String... args) {
        if (userProfileRepository.count() == 0) {
            userProfileRepository.save(new UserProfile(
                    UUID.fromString("ca5025bb-bdda-4cec-ba9b-02d9d7b652d6"),
                    "janetjones",
                    null
            ));
            userProfileRepository.save(new UserProfile(
                    UUID.fromString("2ba96fa7-0b5c-4b9b-b1f1-757bd6093e3f"),
                    "antoniojunior",
                    null
            ));

        }
        UUID marioId = UUID.fromString("2ba96fa7-0b5c-4b9b-b1f1-757bd6093e3e");
        if (!userProfileRepository.existsById(marioId)) {
            userProfileRepository.save(new UserProfile(
                    marioId,
                    "mariojunior",
                    null
            ));
        }

    }
}
//package com.awsfilestorage.awsfilestorageapp.profile;
//
//import lombok.AllArgsConstructor;
//
//import java.util.Objects;
//import java.util.Optional;
//import java.util.UUID;
//
//
//@AllArgsConstructor
//public class UserProfile {
//
//    private UUID userProfileId;
//    private String username;
//    private String userProfileImageLink; // S3 key
//
//    public UUID getUserProfileId() {
//        return this.userProfileId;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }
//
//    public Optional<String> getUserProfileImageLink() {
//        return Optional.ofNullable(userProfileImageLink);
//    }
//
//    public void setUserProfileId(UUID userProfileId) {
//        this.userProfileId = userProfileId;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setUserProfileImageLink(String userProfileImageLink) {
//        this.userProfileImageLink = userProfileImageLink;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UserProfile)) return false;
//        UserProfile that = (UserProfile) o;
//
//        return Objects.equals(userProfileId, that.userProfileId) &&
//                Objects.equals(username, that.username) &&
//                Objects.equals(userProfileImageLink, that.userProfileImageLink);
//    }
//
//    protected boolean canEqual(final Object other) {
//        return other instanceof UserProfile;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userProfileId, username, userProfileImageLink);
//    }
//
//    public String toString() {
//        return "UserProfile(userProfileId=" + this.getUserProfileId() + ", username=" + this.getUsername() + ", userProfileImageLink=" + this.getUserProfileImageLink() + ")";
//    }
//}

package com.awsfilestorage.awsfilestorageapp.profile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    private UUID userProfileId;
    private String username;
    private String userProfileImageLink;

    public UUID getUserProfileId() {
        return this.userProfileId;
    }

    public String getUsername() {
        return this.username;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    public void setUserProfileId(UUID userProfileId) {
        this.userProfileId = userProfileId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImageLink);
    }

    public String toString() {
        return "UserProfile(userProfileId=" + this.getUserProfileId() + ", username=" + this.getUsername() + ", userProfileImageLink=" + this.getUserProfileImageLink() + ")";
    }
}

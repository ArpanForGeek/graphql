package com.example.graphql.operation.query.userprofile;

import com.example.graphql.model.userprofile.UserProfile;
import com.example.graphql.services.serviceprovider.impl.userprofile.UserProfileService;
import com.example.graphql.utils.AESUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileQuery implements GraphQLQueryResolver {
    private static String SECRET_KEY = "YuTr45QEsUiOppTy";

    @Autowired
    private UserProfileService userProfileService;

    /**
     * Search User Profile by user id
     *
     * @param userId user Id
     * @return UserProfile
     */
    @Secured({"ROLE_BASIC"})
    public UserProfile getUserProfileById(Integer userId) {
        UserProfile userProfile = userProfileService.getUserById(userId);
        userProfile.setPassword(AESUtils.decrypt(userProfile.getPassword(), SECRET_KEY).trim());
        return userProfile;
    }

    /**
     * To search User profiles of those whose user name starts with "partialUserName"
     *
     * @param partialUserName
     * @return List of User Profile
     */
    @Secured({"ROLE_BASIC", "ROLE_CONTRIBUTOR"})
    public List<UserProfile> searchUserProfiles(String partialUserName) {
        return userProfileService.getUserProfileByUserNameStartsWith(partialUserName)
                .stream()
                .map(userProfile -> {
                            userProfile.setPassword(AESUtils.decrypt(userProfile.getPassword(), SECRET_KEY).trim());
                            return userProfile;
                        }
                ).collect(Collectors.toList());
    }

    /**
     * Search for all users
     *
     * @return List of userprofile
     */
    @Secured({"ROLE_ADMIN"})
    public List<UserProfile> getAllUsers() {
        return userProfileService.getAllUsers();
    }

}

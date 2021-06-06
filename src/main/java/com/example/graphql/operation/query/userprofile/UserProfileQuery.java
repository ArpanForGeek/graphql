package com.example.graphql.operation.query.userprofile;

import com.example.graphql.model.userprofile.UserProfile;
import com.example.graphql.services.serviceprovider.impl.userprofile.UserProfileService;
import com.example.graphql.utils.aesutils.AESUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserProfileQuery implements GraphQLQueryResolver {
    private static String SECRET_KEY = "YuTr45QEsUiOppTy";

    @Autowired
    private UserProfileService userProfileService;

    //@PreAuthorize("hasAuthority('ROLE_BASIC')")
    @Secured("ROLE_BASIC")
    public UserProfile getUserProfileById(Integer userId) {
        UserProfile userProfile = userProfileService.getUserById(userId);
        userProfile.setPassword(AESUtils.decrypt(userProfile.getPassword(), SECRET_KEY).trim());
        return userProfile;
    }

}

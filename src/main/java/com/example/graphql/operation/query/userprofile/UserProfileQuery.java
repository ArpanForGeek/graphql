package com.example.graphql.operation.query.userprofile;

import com.example.graphql.entity.userprofile.UserProfile;
import com.example.graphql.services.serviceprovider.impl.userprofile.UserProfileService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileQuery implements GraphQLQueryResolver {

    @Autowired
    private UserProfileService userProfileService;

    public UserProfile getUserProfileById(Integer userId) {
        return userProfileService.getUserById(userId);
    }

}

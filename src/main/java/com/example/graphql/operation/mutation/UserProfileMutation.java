//package com.example.graphql.operation.mutation;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import com.example.graphql.model.userprofile.UserProfile;
//import com.example.graphql.services.serviceprovider.impl.userprofile.UserProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserProfileMutation implements GraphQLMutationResolver {
//
//    private static String SECRET_KEY = "YuTr45QEsUiOppTy";
//    @Autowired
//    private UserProfileService userProfileService;
//
//    public boolean save(UserProfile userProfile){
//        userProfileService.save(userProfile);
//        return true;
//    }
//}

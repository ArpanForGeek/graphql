package com.example.graphql.modelmapper;

import com.example.graphql.dto.UserProfileDto;
import com.example.graphql.model.userprofile.UserProfile;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserProfileConverter implements Converter<UserProfileDto, UserProfile> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserProfile convert(UserProfileDto userProfileDto) {
        return mapper.map(userProfileDto, UserProfile.class);
    }
}

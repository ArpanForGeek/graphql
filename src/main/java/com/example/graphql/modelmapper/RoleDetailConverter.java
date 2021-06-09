package com.example.graphql.modelmapper;

import com.example.graphql.dto.RoleDetailDto;
import com.example.graphql.model.userprofile.RoleDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDetailConverter implements Converter<RoleDetailDto, RoleDetail> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public RoleDetail convert(RoleDetailDto source) {
        return mapper.map(source,RoleDetail.class);
    }
}

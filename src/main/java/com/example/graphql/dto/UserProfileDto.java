package com.example.graphql.dto;

import com.example.graphql.model.userprofile.RoleDetail;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto implements Serializable {

    private Integer userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNo;
    private String password;
    private Integer active;
    private Integer defaultValue;
    private Set<RoleDetailDto> roleDetailSet;
}

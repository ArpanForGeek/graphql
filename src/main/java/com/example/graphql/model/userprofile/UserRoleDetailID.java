package com.example.graphql.model.userprofile;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleDetailID implements Serializable {
    private Integer userId;
    private Integer roleId;
}

package com.example.graphql.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleDetailDto implements Serializable {
    private Integer roleId;
    private String roleName;
}

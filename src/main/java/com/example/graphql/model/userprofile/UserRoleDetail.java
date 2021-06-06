package com.example.graphql.model.userprofile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserRoleDetailID.class)
public class UserRoleDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    public UserRoleDetail(Integer roleId){}
}

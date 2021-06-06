package com.example.graphql.model.userprofile;

import com.example.graphql.annotations.passwordgenerator.annotation.GeneratePassword;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user_profile_tbl")
@Data
@NoArgsConstructor
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @MapsId("user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "contact_no")
    private String contactNo;

    @GeneratePassword
    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "is_default")
    private Boolean defaultValue;

    @ManyToMany(mappedBy = "", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_tbl", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleDetail> roleDetailSet;

    public UserProfile(UserProfile userProfile) {
        this.active = userProfile.getActive();
        this.contactNo = userProfile.getContactNo();
        this.emailId = userProfile.getEmailId();
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.userName = userProfile.getUserName();
        this.password = userProfile.getPassword();
        this.userId = userProfile.getUserId();
        this.roleDetailSet = userProfile.getRoleDetailSet();
        this.defaultValue = userProfile.getDefaultValue();

    }
}

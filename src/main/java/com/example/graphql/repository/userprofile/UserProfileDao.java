package com.example.graphql.repository.userprofile;

import com.example.graphql.model.userprofile.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for  CRUD operations on a repository(UserProfile) for a specific type(Integer).
 */
@Repository
public interface UserProfileDao extends CrudRepository<UserProfile, Integer> {

    /**
     * Retrieve UserProfile by username
     *
     * @param userName provided
     * @return UserProfile
     */
    UserProfile findByUserName(String userName);

    /**
     * Retrieves UserProfile object based on username & password combination
     *
     * @param userName logged in username
     * @param password provided by user
     * @return UserProfile object based on username & password combination
     */
    UserProfile findByUserNameAndPassword(String userName, String password);
}

package com.example.graphql.repository.userprofile;

import com.example.graphql.model.userprofile.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    /**
     * To search User profiles of those whose user name starts with "text"
     * @param text plain text - to search User profiles of those whose user name starts with "text"
     * @return List of UserProfile
     */
    List<UserProfile> findByUserNameStartsWith(String text);
}

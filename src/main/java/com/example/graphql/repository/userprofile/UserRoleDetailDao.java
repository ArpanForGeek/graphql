package com.example.graphql.repository.userprofile;

import com.example.graphql.model.userprofile.UserRoleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for  CRUD operations on a repository(UserRoleDetail) for a specific type(Integer).
 */
@Repository
public interface UserRoleDetailDao extends CrudRepository<UserRoleDetail, Integer> {
}

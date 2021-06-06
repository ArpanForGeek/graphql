package com.example.graphql.repository.userprofile;

import com.example.graphql.model.userprofile.RoleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for  CRUD operations on a repository(RoleDetail) for a specific type(Integer).
 */
@Repository
public interface RoleDetailDao extends CrudRepository<RoleDetail, Integer> {
}

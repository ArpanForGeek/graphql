package com.example.graphql.services.serviceprovider.impl.userprofile;

import com.example.graphql.model.userprofile.RoleDetail;
import com.example.graphql.repository.userprofile.RoleDetailDao;
import com.example.graphql.services.serviceprovider.PersistOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * This class contains methods related data access operation support for RoleDetail
 */
@Service
public class RoleDetailService implements PersistOperation<RoleDetail> {

    protected static final Logger logger = LogManager.getLogger("knowledge-repository");

    @Autowired
    private RoleDetailDao roleDetailDao;

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    /**
     * Retrieve role detail by role id
     *
     * @param roleId provided
     * @return RoleDetail
     */
    public RoleDetail getRoleById(Integer roleId) {
        logger.info("Started retrieving RoleDetail operation by role id :" + roleId);
        try {
            RoleDetail roleDetail = roleDetailDao.findById(roleId)
                    .orElseGet(() ->
                    {
                        logger.error("No RoleDetail found by role id :" + roleId + " . Hence ResourceNotFoundException is thrown");
                        throw new RuntimeException("");
                    });
            logger.debug("Retrieved RoleDetail by role id " + roleId + " :" + roleDetail);
            logger.info("Finished retrieving RoleDetail operation by role id :" + roleId);
            return roleDetail;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public boolean save(RoleDetail roleDetail) {
        logger.info("Started saving RoleDetail operation with :" + roleDetail);
        try {
            entityManager.clear();
            roleDetailDao.save(roleDetail);
            logger.debug("Saved RoleDetail :" + roleDetail);
            logger.info("Finished saving RoleDetail operation with :" + roleDetail);
            return true;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}

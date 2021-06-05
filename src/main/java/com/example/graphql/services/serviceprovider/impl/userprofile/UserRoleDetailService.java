package com.example.graphql.services.serviceprovider.impl.userprofile;

import com.example.graphql.entity.userprofile.UserRoleDetail;
import com.example.graphql.repository.userprofile.UserRoleDetailDao;
import com.example.graphql.services.serviceprovider.PersistOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

/**
 * This class contains methods related data access operation support for UserRoleDetail
 */
@Service
public class UserRoleDetailService implements PersistOperation<UserRoleDetail> {
    protected static final Logger logger = LogManager.getLogger("knowledge-repository");

    @Autowired
    private UserRoleDetailDao userRoleDetailDao;
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;


    /**
     * Saves list of UserRoleDetail entity
     *
     * @param userRoleDetails provided entiry
     * @return true
     */
    @Override
    public boolean saveAll(List<UserRoleDetail> userRoleDetails) {
        logger.info("Started saving list of UserRoleDetail operation :" + userRoleDetails);
        try {
            entityManager.clear();
            userRoleDetailDao.saveAll(userRoleDetails);
            logger.debug("Saved list of UserRoleDetail :" + userRoleDetails);
            logger.info("Finished saving list of UserRoleDetail operation");
            return true;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Save UserRoleDetail entity
     *
     * @param userRoleDetail provided entity
     * @return true
     */
    @Override
    public boolean save(UserRoleDetail userRoleDetail) {
        logger.info("Started saving UserRoleDetail operation with :" + userRoleDetail);
        try {
            entityManager.clear();
            userRoleDetailDao.save(userRoleDetail);
            logger.debug("Saved UserRoleDetail :" + userRoleDetail);
            logger.info("Finished saving UserRoleDetail operation with :" + userRoleDetail);
            return true;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}

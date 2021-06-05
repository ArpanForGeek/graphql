package com.example.graphql.services.serviceprovider.impl.userprofile;

import com.example.graphql.entity.userprofile.UserProfile;
import com.example.graphql.exception.exceptions.ResourceNotFoundException;
import com.example.graphql.repository.userprofile.UserProfileDao;
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
 * This class contains methods related data access operation support for UserProfile
 */
@Service
public class UserProfileService implements PersistOperation<UserProfile> {

    protected static final Logger logger = LogManager.getLogger("knowledge-repository");
    @Autowired
    private UserProfileDao userProfileDao;
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    /**
     * Retrieve UserProfile based on user id
     *
     * @param userId provided
     * @return UserProfile .If any issue occurred throw ResourceNotFoundException
     */
    public UserProfile getUserById(Integer userId) {
        logger.info("Started Retrieving User profile operation by userId :" + userId);
        try {
            UserProfile userProfile = userProfileDao.findById(userId)
                    .orElseThrow(() -> {
                        logger.error("UserProfile not found with userid :" + userId);
                        throw new ResourceNotFoundException("User not found","userId");
                    });
            logger.debug("Retrieved User profile " + userProfile + " by userId :" + userId);
            logger.info("Finished Retrieving User profile operation by userId :" + userId);
            return userProfile;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

//    /**
//     * Retrieve UserProfile object based on username & password combination
//     *
//     * @param password provided by user
//     * @return UserProfile object based on username & password combination
//     */
//    public UserProfile getUserByUserNameAndPassword(String password) {
//        logger.info("Started Retrieving User Profile operation by username " + getUserNameFromAuthenticationHeader());
//        try {
//            UserProfile userProfile = userProfileDao.findByUserNameAndPassword(getUserNameFromAuthenticationHeader(), password);
//            logger.debug("Retrieved User Profile : " + userProfile);
//            logger.info("Finished Retrieving User Profile operation by username " + getUserNameFromAuthenticationHeader());
//            return userProfile;
//        } catch (DataAccessException exception) {
//            throw new RuntimeException(exception.getMessage());
//        }
//    }

    /**
     * Save UserProfile entity
     *
     * @param userProfile provided entity
     * @return true
     */
    @Override
    public boolean save(UserProfile userProfile) {
        logger.info("Started Saving User Profile operation");
        try {
            entityManager.clear();
            userProfileDao.save(userProfile);
            logger.debug("Saved User Profile with details :" + userProfile);
            logger.info("Finished Saving User Profile operation");
            return true;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Retrieve UserProfile by username
     *
     * @param userName provided
     * @return UserProfile
     */
    public UserProfile getUserByUserName(String userName) {
        logger.info("Started Retrieving User Profile operation by username :" + userName);
        try {
            UserProfile userProfile = userProfileDao.findByUserName(userName);
            logger.debug("Retrieved User Profile by username " + userName + ":" + userProfile);
            logger.info("Finished Retrieving User Profile operation by username :" + userName);
            return userProfile;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Delete UserProfile by user id
     *
     * @param userId provided
     * @return true
     */
    @Override
    public boolean delete(Integer userId) {
        logger.info("Started deleting User Profile operation by userId " + userId);
        try {
            entityManager.clear();
            userProfileDao.deleteById(userId);
            logger.debug("Deleted User Profile by userId " + userId);
            logger.info("Finished deleting User Profile operation by userId " + userId);
            return true;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Retrieve username by user id
     *
     * @param userId provided
     * @return String
     */
    public String getUserNameFromUserId(Integer userId) {
        logger.info("Started Retrieving username operation by useId :" + userId);
        try {
            String username = userProfileDao.findById(userId).get().getUserName();
            logger.debug("Retrieved username from userId " + userId + " :" + username);
            logger.info("Finished Retrieving username operation by useId :" + userId);
            return username;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Retrieve full name of user based on user  id
     *
     * @param userId provided
     * @return String
     */
    public String getFullNameOfUserByUserId(Integer userId) {
        logger.info("Started Retrieving full name of user operation from useId :" + userId);
        try {
            UserProfile userDetails = getUserById(userId);
            logger.debug("Retrieved user details from useId :" + userId);
            String fullName = userDetails.getFirstName() + " " + userDetails.getLastName();
            logger.debug("Constructed fullName from userDetails :" + fullName);
            logger.info("Finished Retrieving full name of user operation from useId :" + userId);
            return fullName;
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

//    /**
//     * Retrieve CustomUserProfile by username while log in
//     *
//     * @param username provided
//     * @return CustomUserProfile
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public CustomUserProfile loadUserByUsername(String username) throws UsernameNotFoundException {
//        logger.info("Started constructing CustomUserProfile operation by username :" + username);
//        logger.info("Started Retrieving userProfile operation by username :" + username);
//        try {
//            UserProfile userProfile = userProfileDao.findByUserName(username);
//            logger.debug("Retrieved userProfile :" + userProfile);
//            logger.info("Finished Retrieving userProfile by username :" + username);
//            if (userProfile == null) {
//                logger.error("userProfile not found with username " + username + " . Hence InternalAuthenticationServiceException is thrown");
//                throw new InternalAuthenticationServiceException("Invalid Credentials");
//            }
//            logger.info("CustomUserProfile construction is completed");
//            return new CustomUserProfile(userProfile);
//        } catch (DataAccessException exception) {
//            throw new RuntimeException(exception.getMessage());
//        }
//    }
//
//    /**
//     * Retrieve username from authentication header
//     *
//     * @return String
//     */
//    public String getUserNameFromAuthenticationHeader() {
//        logger.info("Started Retrieving username from AuthenticationHeader operation");
//        try {
//            String username = "";
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            logger.debug("principal from SecurityContextHolder : " + principal);
//            if (principal instanceof CustomUserProfile) {
//                username = ((CustomUserProfile) principal).getUsername();
//            } else {
//                username = principal.toString();
//            }
//            logger.debug("Retrieved username : " + username + " from AuthenticationHeader ");
//            logger.info("Finished Retrieving username from AuthenticationHeader operation");
//            return username;
//        } catch (DataAccessException exception) {
//            throw new RuntimeException(exception.getMessage());
//        }
//    }
//

//    /**
//     * Retrieve user id from authentication header
//     *
//     * @return Integer
//     */
//    public Integer getUserIdFromAuthenticationHeader() {
//        logger.info("Started Retrieving userId from AuthenticationHeader operation");
//        try {
//            Integer userId = null;
//            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            logger.debug("Authentication from SecurityContextHolder : " + authentication);
//            if (authentication instanceof OAuth2Authentication) {
//                final UserProfile principal = (UserProfile) authentication.getPrincipal();
//                userId = principal.getUserId();
//            }
//            logger.debug("Retrieved userId : " + userId + " from AuthenticationHeader");
//            logger.info("Finished Retrieving userId from AuthenticationHeader operation");
//            return userId;
//        } catch (DataAccessException exception) {
//            throw new RuntimeException(exception.getMessage());
//        }
//    }

//    /**
//     * Retrieve user id by username
//     *
//     * @param userName provided
//     * @return Integer in case if any issue throws NullPointerException
//     */
//    public Integer getUserIdByUserName(String userName) {
//        logger.info("Started Retrieving userId operation by username :" + userName);
//        try {
//            UserProfile userProfile = userProfileDao.findByUserName(userName);
//            logger.info("Finished Retrieving userId operation by username :" + userName);
//            return userProfile.getUserId();
//        } catch (DataAccessException exception) {
//            throw new RuntimeException(exception.getMessage());
//        } catch (AuthenticationServiceException | NullPointerException exception) {
//            throw new InternalAuthenticationServiceException("invalid cred");
//        }
//    }
}



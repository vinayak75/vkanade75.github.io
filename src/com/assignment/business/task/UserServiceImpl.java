/**
 * 
 */
package com.assignment.business.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.enterprise.task.UserDaoIF;
import com.assignment.model.task.User;
import com.assignment.util.AppLoggerUtil;

/**
 * @author Vinayak Kanade
 *
 */
@Service
public class UserServiceImpl implements UserServiceIF {
    private static final String NA = "NA";
    @Autowired
    UserDaoIF                   userDaoIF;

    /* This method is used to save/update user details */
    @Override
    public void saveUser(UserDTO userDTO) {

        try {
            User user;
            String userId = userDTO.getUserId();
            /* update existing user details */
            if (!NA.equals(userId)) {
                user = userDaoIF.getUserById(Integer.valueOf(userId));
                user.setUserName(userDTO.getUserName());
                user.setPassword(userDTO.getPassword());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setPhoneNumber(userDTO.getPhoneNumber());
                user.setLocation(userDTO.getLocation());
            }
            /* save new user details */
            else {
                user = new User();
                user.setUserName(userDTO.getUserName());
                user.setPassword(userDTO.getPassword());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setPhoneNumber(userDTO.getPhoneNumber());
                user.setLocation(userDTO.getLocation());
            }
            userDaoIF.saveUser(user);
        } catch (Exception e) {
            AppLoggerUtil.logError(this.getClass(),
                    "Problem in saving/updating user details : Root cause" + e.getMessage(), e);
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> userDtoList = new ArrayList<>();
        try {
            List<User> userDetails = userDaoIF.getAllUser();
            if (!userDetails.isEmpty()) {
                AppLoggerUtil.logInfo(this.getClass(), "Successfully found all user details");
                for (User userObj : userDetails) {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDTO, userObj);
                    userDtoList.add(userDTO);
                }
            } else {
                AppLoggerUtil.logInfo(this.getClass(), "No any user details found");
            }

        } catch (Exception e) {
            AppLoggerUtil.logError(this.getClass(),
                    "Problem in getting all user details : Root cause" + e.getMessage(), e);
        }
        return userDtoList;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        UserDTO userDTO = new UserDTO();
        try {
            User user = userDaoIF.getUserById(userId);
            if (user != null) {
                AppLoggerUtil.logInfo(this.getClass(), " user details found");
                BeanUtils.copyProperties(userDTO, user);
            } else {
                AppLoggerUtil.logInfo(this.getClass(), " user details not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        userDaoIF.deleteUser(user);

    }
}

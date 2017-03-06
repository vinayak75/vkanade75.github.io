/**
 * 
 */
package com.assignment.controller.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.business.common.JsonHelper;
import com.assignment.business.task.UserDTO;
import com.assignment.business.task.UserServiceIF;
import com.assignment.util.AppLoggerUtil;

/**
 * @author Vinayak Kanade
 *
 */
@Controller
public class UserController {
    private static final String ERROR        = "error";
    private static final String USER_DETAILS = "userDetails";
    @Autowired
    UserServiceIF               serServiceIF;

    /**
     * This method is used to save/update user details
     * 
     * @param userForm
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/saveUser.json", method = RequestMethod.POST)
    @ResponseBody
    public void saveUser(@RequestBody UserDTO userForm, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            serServiceIF.saveUser(userForm);
            List<UserDTO> userDto = serServiceIF.getAllUser();
            responseMap.put(USER_DETAILS, userDto);
            responseMap.put(ERROR, "false");
        } catch (Exception e) {
            responseMap.put(ERROR, true);
            AppLoggerUtil.logError(this.getClass(),
                    "Problem in saving/updating user details : Root cause" + e.getMessage(), e);
        } finally {
            JsonHelper.writeResponseData(responseMap, response);
        }

    }

    /**
     * This method is used to get existing user by user id
     * 
     * @param userId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getUserById.json", method = RequestMethod.POST)
    @ResponseBody
    public void editUser(@RequestParam("userId") Integer userId, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            UserDTO userDto = serServiceIF.getUserById(userId);
            responseMap.put(USER_DETAILS, userDto);
            responseMap.put(ERROR, false);
        } catch (Exception e) {
            responseMap.put(ERROR, true);
            AppLoggerUtil.logError(this.getClass(), "Problem in getting user details : Root cause" + e.getMessage(), e);
        } finally {
            JsonHelper.writeResponseData(responseMap, response);
        }

    }

    /**
     * This method is used delete user details by user id
     * 
     * @param userId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/deleteUser.json", method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestParam("userId") Integer userId, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            serServiceIF.deleteUser(userId);
            List<UserDTO> userDto = serServiceIF.getAllUser();
            responseMap.put(USER_DETAILS, userDto);
            responseMap.put(ERROR, "false");
        } catch (Exception e) {
            responseMap.put(ERROR, true);
            AppLoggerUtil
                    .logError(this.getClass(), "Problem in deleting user details : Root cause" + e.getMessage(), e);
        } finally {
            JsonHelper.writeResponseData(responseMap, response);
        }

    }

    /**
     * This method is used to get all user details.
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getAllUser.json", method = RequestMethod.POST)
    @ResponseBody
    public void getAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            List<UserDTO> userDto = serServiceIF.getAllUser();
            responseMap.put(USER_DETAILS, userDto);
            responseMap.put(ERROR, "false");
        } catch (Exception e) {
            responseMap.put(ERROR, true);
            AppLoggerUtil.logError(this.getClass(),
                    "Problem in getting all user details : Root cause" + e.getMessage(), e);
        } finally {
            JsonHelper.writeResponseData(responseMap, response);
        }

    }
}

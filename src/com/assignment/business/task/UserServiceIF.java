package com.assignment.business.task;

import java.util.List;

public interface UserServiceIF {
    public void saveUser(UserDTO userDTO);

    public void deleteUser(Integer userId);

    public List<UserDTO> getAllUser();

    public UserDTO getUserById(Integer userId);
}

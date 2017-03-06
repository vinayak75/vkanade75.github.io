package com.assignment.enterprise.task;

import java.util.List;

import com.assignment.model.task.User;

public interface UserDaoIF {
    public void saveUser(User user);

    public List<User> getAllUser();

    public User getUserById(Integer userId);

    public void deleteUser(User user);
}

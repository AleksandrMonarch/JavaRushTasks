package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model {

    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());
    }

    @Override
    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(userService.getAllDeletedUsers());
    }

    @Override
    public void loadUserById(long userId) {
        modelData.setActiveUser(userService.getUsersById(userId));
    }

    @Override
    public void deleteUserById(long id) {
        userService.deleteUser(id);
        loadUsers();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        loadUsers();
    }

    private List<User> getAllUsers() {
        return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));
    }
}

package com.bsuir.archive.server.service;

import com.bsuir.archive.server.domain.User;
import com.bsuir.archive.server.service.exception.ServiceException;

public interface UserService {

    boolean addUser(User user) throws ServiceException;
    boolean delUser(String login) throws ServiceException;
    boolean changeUser(User user, User newUser) throws ServiceException;
    void clear() throws ServiceException;
    User findUser(String login, String password) throws ServiceException;
}

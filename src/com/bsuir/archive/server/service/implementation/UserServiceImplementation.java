package com.bsuir.archive.server.service.implementation;

import com.bsuir.archive.server.dao.DAOFactory;
import com.bsuir.archive.server.dao.UserDAO;
import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.User;
import com.bsuir.archive.server.service.UserService;
import com.bsuir.archive.server.service.exception.ServiceException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImplementation implements UserService {

    public static UserServiceImplementation getInstance() {
        return instance;
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        Boolean result = true;
        if ((user != null) && (findUser(user) == null)) {
            user.setHash(getHash(user.getHash()));
            List<User> list;
            try {
                list = userDAO.getList();
                list.add(user);
                userDAO.setList(list);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            result = false;
        }
        return result;
    }

    public User findUser(User user) throws ServiceException {

        List<User> list;
        try {
            list = userDAO.getList();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

        User detectedUser = null;
        for (User item : list) {
            if (item.equals(user)) {
                detectedUser = item;
            }
        }
        return detectedUser;
    }

    public User findUser(String login) throws ServiceException {

        List<User> list;
        try {
            list = userDAO.getList();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

        User detectedUser = null;
        for (User item : list) {
            if (item.getLogin().equals(login)) {
                detectedUser = item;
            }
        }
        return detectedUser;
    }

    @Override
    public boolean delUser(String login) throws ServiceException {
        boolean result = false;
        List<User> list;
        try {
            list = userDAO.getList();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        User forDel = findUser(login);
        if (forDel != null) {
            result = true;
            list.remove(forDel);
            try {
                userDAO.setList(list);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        }
        return result;
    }

    @Override
    public boolean changeUser(User user, User newUser) throws ServiceException {
        boolean result = false;
        List<User> list;
        try {
            list = userDAO.getList();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        User forChange = findUser(user);
        if (forChange != null) {
            result = true;
            list.remove(forChange);
            list.add(newUser);
            try {
                userDAO.setList(list);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        }
        return result;
    }

    @Override
    public void clear() throws ServiceException {
        List<User> list = new ArrayList<User>();
        try {
            userDAO.setList(list);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public User findUser(String login, String password) throws ServiceException {
        List<User> list;
        try {
            list = userDAO.getList();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

        User detectedUser = null;
        for (User item : list) {
            if ((item.getLogin().equals(login)) && (item.getHash().equals(getHash(password)))) {
                detectedUser = item;
            }
        }
        return detectedUser;
    }

    private UserServiceImplementation() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDAO();
    }

    private String getHash(String str) throws ServiceException {
        MessageDigest md = null;
        String hash = "";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            hash = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
        return hash;
    }

    UserDAO userDAO;
    private static UserServiceImplementation instance = new UserServiceImplementation();
}

package com.bsuir.archive.server.service.implementation;

import com.bsuir.archive.server.dao.DAOFactory;
import com.bsuir.archive.server.dao.UserDAO;
import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.User;
import com.bsuir.archive.server.service.UserService;
import com.bsuir.archive.server.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplementation implements UserService {

    public static UserServiceImplementation getInstance(){
        return instance;
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        Boolean result = true;
        if ( (user!=null) && (findUser(user)==null)) {
            List<User> list;
            try {
                list = userDAO.getList();
                list.add(user);
                userDAO.setList(list);
            }catch (DAOException ex){
                throw  new ServiceException(ex);
            }
        }
        else{
            result = false;
        }
        return result;
    }

    public  User findUser(User user) throws ServiceException {

        List<User> list;
        try {
            list = userDAO.getList();
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }

        User detectedUser = null;
        for (User item: list) {
            if (item.equals(user)){
                detectedUser = item;
            }
        }
        return detectedUser;
    }

    @Override
    public boolean delUser(User user) throws ServiceException {
        boolean result = false;
        List<User> list;
        try {
            list = userDAO.getList();
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }

        User forDel = findUser(user);
        if (forDel!=null){
            result = true;
            list.remove(forDel);
            try {
                userDAO.setList(list);
            }catch (DAOException ex){
                throw  new ServiceException(ex);
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
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
        User forChange = findUser(user);
        if (forChange!=null){
            result = true;
            list.remove(forChange);
            list.add(newUser);
            try {
                userDAO.setList(list);
            }catch (DAOException ex){
                throw  new ServiceException(ex);
            }
        }
        return  result;
    }

    @Override
    public void clear() throws ServiceException {
                List<User> list = new ArrayList<User>();
        try {
            userDAO.setList(list);
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
    }

    private UserServiceImplementation(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDAO();
    }
    UserDAO userDAO;
    private static UserServiceImplementation instance = new UserServiceImplementation();
}

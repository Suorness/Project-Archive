package com.bsuir.archive.server.dao;

import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.User;

import java.util.List;

public interface UserDAO {
    List<User> getList() throws DAOException;

    void setList(List<User> list) throws DAOException;

}

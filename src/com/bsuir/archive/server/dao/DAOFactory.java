package com.bsuir.archive.server.dao;

public class DAOFactory {
    private static DAOFactory ourInstance = new DAOFactory();

    public static DAOFactory getInstance() {
        return ourInstance;
    }

    private DAOFactory() {
    }
}

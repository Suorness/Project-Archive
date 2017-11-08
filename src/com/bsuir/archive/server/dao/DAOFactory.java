package com.bsuir.archive.server.dao;

import com.bsuir.archive.server.dao.implamentation.XMLDossierDAO;
import com.bsuir.archive.server.dao.implamentation.XMLUserDAO;

public class DAOFactory {
    private static DAOFactory ourInstance = new DAOFactory();
    private  DossierDAO dossierDAO = XMLDossierDAO.getInstance();
    private  UserDAO userDAO = XMLUserDAO.getInstance();

    public DossierDAO getDossierDAO(){
        return dossierDAO;
    }
    public UserDAO getUserDAO(){
        return userDAO;
    }
    public static DAOFactory getInstance() {
        return ourInstance;
    }

    private DAOFactory() {
    }
}

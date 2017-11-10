package com.bsuir.archive.server.service;

import com.bsuir.archive.server.service.implementation.DossierServiceImplementation;
import com.bsuir.archive.server.service.implementation.UserServiceImplementation;

public class ServiceFactory {
    private static ServiceFactory ourInstance = new ServiceFactory();
    private DossierService dossierService = DossierServiceImplementation.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();


    public DossierService getDossierService() {
        return dossierService;
    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
    }
}

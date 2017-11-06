package com.bsuir.archive.server.service;

public class ServiceFactory {
    private static ServiceFactory ourInstance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
    }
}

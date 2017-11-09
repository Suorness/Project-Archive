package com.bsuir.archive.server.service;

import com.bsuir.archive.server.service.exception.ServiceException;

public interface DataTransferService {
    String getRequest() throws  ServiceException;
    void sendResponse(String response) throws  ServiceException;

    void initializing() throws ServiceException;
    void  run() throws  ServiceException;
}

package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.UserService;
import com.bsuir.archive.server.service.exception.ServiceException;

public class DeleteUserCommand implements Command {

    UserService userService;

    public DeleteUserCommand() {
        ServiceFactory factory = ServiceFactory.getInstance();
        userService = factory.getUserService();
    }

    @Override
    public String execute(String[] param) {
        String response = "User deleted";
        Boolean result = true;
        try {
            result = userService.delUser(param[1]);
        } catch (ServiceException ex) {
            response = "Error";
            return response;
        }
        if (!result) {
            response = "User was not deleted";
        }
        return response;
    }

    @Override
    public Boolean isAccessSee() {
        return accessSee;
    }

    @Override
    public Boolean isAccessWrite() {
        return accessWrite;
    }

    @Override
    public Boolean isAccessChange() {
        return accessChange;
    }

    @Override
    public String getDescriptionCommand() {
        return description;
    }

    @Override
    public Integer getCountParam() {
        return countParam;
    }

    @Override
    public Boolean isAccessAdmin() {
        return accessAdmin;
    }


    private static final int countParam = 2;
    private String description = "Delete a user: deleteuser|login";
    Boolean accessSee = false;
    Boolean accessWrite = true;
    Boolean accessChange = true;
    Boolean accessAdmin = true;
}

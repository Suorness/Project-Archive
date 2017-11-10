package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.auxiliary.manager.UserManager;
import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.domain.User;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.UserService;
import com.bsuir.archive.server.service.exception.ServiceException;

public class AuthorizationCommand implements Command {

    UserManager manager;
    UserService userService;

    public AuthorizationCommand(UserManager userManager) {
        manager = userManager;
        ServiceFactory factory = ServiceFactory.getInstance();
        userService = factory.getUserService();
    }

    @Override
    public String execute(String[] param) {
        String response = "";
        User user = null;
        try {
            user = userService.findUser(param[1], param[2]);
        } catch (ServiceException ex) {
            response = "Error";
            return response;
        }
        if (user != null) {
            manager.setUser(user);
            response = "Authorized";
        } else {
            response = "User will not find";
        }
        return response;
    }

    private int parseToInt(String str) {
        int result;
        try {
            result = Integer.parseInt(str);
        } catch (Exception ex) {
            result = -1;
        }
        return result;
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

    private String description = "Authorization: signin|login|password";
    private static final int countParam = 3;
    Boolean accessSee = false;
    Boolean accessWrite = false;
    Boolean accessChange = false;
    Boolean accessAdmin = false;
}

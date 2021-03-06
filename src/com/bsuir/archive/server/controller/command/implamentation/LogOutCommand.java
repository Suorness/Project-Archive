package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.auxiliary.manager.UserManager;
import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.UserService;

public class LogOutCommand implements Command {


    UserManager manager;
    UserService userService;

    public LogOutCommand(UserManager userManager) {
        manager = userManager;
        ServiceFactory factory = ServiceFactory.getInstance();
        userService = factory.getUserService();
    }

    @Override
    public String execute(String[] param) {
        String response = "";

        manager.logOut();
        response = "Operation was successfully completed";

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


    private String description = "Change user: logout";
    private static final int countParam = 1;
    Boolean accessSee = true;
    Boolean accessWrite = false;
    Boolean accessChange = false;
    Boolean accessAdmin = false;
}

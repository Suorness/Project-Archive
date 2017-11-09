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
        response = "Операция прошла успешно";

        return response;
    }

    @Override
    public Boolean isAccessSee() {
        return AccessSee;
    }

    @Override
    public Boolean isAccessWrite() {
        return AccessWrite;
    }

    @Override
    public Boolean isAccessChange() {
        return AccessChange;
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
        return AccessAdmin;
    }


    private String description = "Change user: logout";
    private static final int countParam = 1;
    Boolean AccessSee = true;
    Boolean AccessWrite = false;
    Boolean AccessChange = false;
    Boolean AccessAdmin = false;
}

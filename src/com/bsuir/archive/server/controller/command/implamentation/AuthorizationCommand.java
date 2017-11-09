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
        int id = parseToInt(param[1]);
        User user = null;
        if (id == -1) {
            response = "Неверные аргументы комманды. Повторите ввод.";
            return response;
        }
        try {
            user = userService.findUser(id);
        } catch (ServiceException ex) {
            //TODO правка
            System.out.println(ex.getMessage());
        }
        if (user != null) {
            manager.setUser(user);
            response = "Авторизован";
        } else {
            response = "Пользователь не найдет";
        }
        if (id == -1) {
            response = "Неверные аргументы комманды. Повторите ввод.";
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

    private String description = "Authorization: signin|id";
    private static final int countParam = 2;
    Boolean AccessSee = false;
    Boolean AccessWrite = false;
    Boolean AccessChange = false;
    Boolean AccessAdmin = false;
}

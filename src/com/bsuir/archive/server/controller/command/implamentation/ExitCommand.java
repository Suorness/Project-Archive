package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;

public class ExitCommand implements Command {
    @Override
    public String execute(String[] param) {
        return "Exit";
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


    private static final int countParam = 1;
    private String description = "Exiting the program: exit";
    Boolean AccessSee = false;
    Boolean AccessWrite = false;
    Boolean AccessChange = false;
    Boolean AccessAdmin = false;
}

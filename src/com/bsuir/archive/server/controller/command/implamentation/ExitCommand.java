package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;

public class ExitCommand implements Command {
    @Override
    public String execute(String[] param) {
        return "Exit";
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


    private static final int countParam = 1;
    private String description = "Exiting the program: exit";
    Boolean accessSee = false;
    Boolean accessWrite = false;
    Boolean accessChange = false;
    Boolean accessAdmin = false;
}

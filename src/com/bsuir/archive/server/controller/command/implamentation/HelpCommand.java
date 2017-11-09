package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.CommandProvider;
import com.bsuir.archive.server.controller.command.Command;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command {

    public HelpCommand(){
        provider = new CommandProvider();
    }
    private CommandProvider provider;
    @Override
    public String execute() {
        List<Command> list = new ArrayList<Command>(provider.getCommands().values());
        StringBuffer buffer = new StringBuffer();
        for (Command command:list) {
            buffer.append(command.getDescriptionCommand() + "\r\n");
        }
        return buffer.toString();
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

    private String description ="List of available commands";
    Boolean AccessSee = false;
    Boolean AccessWrite = false;
    Boolean AccessChange = false;
}

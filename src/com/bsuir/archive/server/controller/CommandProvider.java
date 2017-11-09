package com.bsuir.archive.server.controller;

import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.controller.command.implamentation.AddDossierCommand;
import com.bsuir.archive.server.controller.command.implamentation.AuthorizationCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("addDossierCommand",new AddDossierCommand());
        commands.put("AuthorizationCommand", new AuthorizationCommand());
    }


    Command getCommand(String commandName) {
        Command command;
        command = commands.get(commandName);
        return command;

    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}

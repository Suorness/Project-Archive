package com.bsuir.archive.server.controller;

import com.bsuir.archive.server.auxiliary.manager.UserManager;
import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.domain.User;
import com.bsuir.archive.server.service.DataTransferService;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.UserService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Runnable {


    private final CommandProvider provider = new CommandProvider();

    private UserService userService;
    private DataTransferService dataTransferService;
    private boolean working;
    private Socket socket;
    private UserManager userManager;

    private InputStream sIn;
    private OutputStream sOut;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Controller(Socket socket) {
        ServiceFactory factory = ServiceFactory.getInstance();
        userService = factory.getUserService();
        dataTransferService = factory.getDataTransferService();
        this.socket = socket;
        userManager = new UserManager();
    }

    @Override
    public void run() {
        try {
            sIn = socket.getInputStream();
            sOut = socket.getOutputStream();
            inputStream = new DataInputStream(sIn);
            outputStream = new DataOutputStream(sOut);
            String line = null;
            String request = null;

            while (socket.isConnected()) {
                String response = null;
                line = formationCommandList();
                outputStream.writeUTF(line);
                request = inputStream.readUTF();
                response = doAction(request);
                if (response==null){
                    response = "";
                }
                outputStream.writeUTF(response);
                outputStream.flush();
            }
        } catch (IOException ex) {
            //throw new ServiceException(ex);
        }
    }

    void Start() {
        working = true;

    }

    String doAction(String request) {
        String response = null;
        String commandName;
        commandName = request.split(" ")[0];
        Command command = provider.getCommand(commandName);
        if (command != null) {
            response =command.execute();
        }
        return response;
    }

    Boolean availabilityCheck(User user, Command command) {
        Boolean result = true;
        if (command.isAccessChange()) {
            if (!user.isAccessChange()) {
                result = false;
            }
        }
        if (command.isAccessSee()) {
            if (!user.isAccessSee()) {
                result = false;
            }
        }
        if (command.isAccessWrite()) {
            if (!user.isAccessWrite()) {
                result = false;
            }
        }
        return result;
    }

    String formationCommandList(){
        List<Command> list = new ArrayList<Command>(provider.getCommands().values());
        StringBuffer buffer = new StringBuffer();
        for (Command command:list) {
            if (availabilityCheck(userManager.getUser(),command)) {
                buffer.append(command.getDescriptionCommand() + "\r\n");
            }
        }
        return buffer.toString();
    }

}

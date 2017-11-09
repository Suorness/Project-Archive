package com.bsuir.archive.server.service.implementation;

import com.bsuir.archive.server.service.DataTransferService;
import com.bsuir.archive.server.service.exception.ServiceException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DataTransferServiceImplementation implements DataTransferService{

    private static DataTransferServiceImplementation dataTransferService = new DataTransferServiceImplementation();
    public static DataTransferServiceImplementation getDataTransferService(){
        return dataTransferService;
    }
    private int port = 1212;
    private ServerSocket serverSocket;
    Socket socket;
    private InputStream sIn;
    private OutputStream sOut;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    @Override
    public String getRequest() throws  ServiceException {
        return null;
    }

    @Override
    public void sendResponse(String response) throws  ServiceException {

    }
    private Boolean work = true;
    @Override
    public void run () throws  ServiceException{
        initializing();
        String line = null;
        while (work){
            try {
                System.out.println("Получение");
                line = inputStream.readUTF();
                System.out.println(line);
                outputStream.writeUTF(line);
                outputStream.flush();
            }catch(IOException ex){
                throw  new ServiceException(ex);
            }

        }
    }

    @Override
    public void initializing() throws  ServiceException {
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Ожидание подключения");
            socket = serverSocket.accept();
            sIn = socket.getInputStream();
            sOut = socket.getOutputStream();
            inputStream = new DataInputStream(sIn);
            outputStream = new DataOutputStream(sOut);
            System.out.println("Все создано");
        }catch (IOException ex){
            throw new ServiceException(ex);
        }

    }
}

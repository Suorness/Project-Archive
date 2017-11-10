package com.bsuir.archive.server.dao.implamentation;

import com.bsuir.archive.server.auxiliary.parser.XMLParsers;
import com.bsuir.archive.server.auxiliary.parser.exception.ParserException;
import com.bsuir.archive.server.auxiliary.parser.implamentation.XMLParser;
import com.bsuir.archive.server.auxiliary.writer.WriterFile;
import com.bsuir.archive.server.auxiliary.writer.exception.FileWriterException;
import com.bsuir.archive.server.auxiliary.writer.implamentation.FileWriters;
import com.bsuir.archive.server.dao.UserDAO;
import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.User;

import java.util.ArrayList;
import java.util.List;

public class XMLUserDAO implements UserDAO {
    private static final String PATH = System.getProperty("user.dir") + "/user.xml";
    private static XMLUserDAO instance = new XMLUserDAO();
    private final static Object lock = new Object();
    private WriterFile writer;
    private XMLParsers parser;

    public static XMLUserDAO getInstance() {
        return instance;
    }

    private XMLUserDAO() {
        writer = FileWriters.getInstance();
        parser = XMLParser.getInstance();
    }

    @Override
    public List<User> getList() throws DAOException {
        synchronized (lock) {
            List<User> list;
            String savedData;
            try {
                savedData = writer.readData(PATH);
            } catch (FileWriterException ex) {
                throw new DAOException("Error reading file", ex);
            }
            list = listFormation(savedData);
            return list;
        }
    }

    @Override
    public void setList(List<User> list) throws DAOException {
        synchronized (lock) {
            String dataToSave;
            if (list != null) {
                dataToSave = stringFormation(list);
                try {
                    writer.writeData(PATH, dataToSave);
                } catch (FileWriterException ex) {
                    throw new DAOException("Error writting in file", ex);
                }
            }
        }
    }

    private String stringFormation(List<User> list) throws DAOException {
        String dataToSave;
        User classEx = new User();
        try {
            dataToSave = parser.dataToXML(classEx.getClass(), list);
        } catch (ParserException ex) {
            throw new DAOException("Error parsing at file", ex);
        }
        return dataToSave;
    }

    private List<User> listFormation(String savedData) throws DAOException {
        List<User> list;
        User classEx = new User();
        if ((savedData.isEmpty()) || (savedData == null)) {
            list = new ArrayList<User>();
        } else {
            try {
                list = parser.XMLToData(savedData, classEx);
            } catch (ParserException ex) {
                throw new DAOException("Error parsing of file ()", ex);
            }
        }
        return list;
    }
}

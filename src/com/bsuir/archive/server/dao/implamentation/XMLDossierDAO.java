package com.bsuir.archive.server.dao.implamentation;

import com.bsuir.archive.server.auxiliary.parser.XMLParsers;
import com.bsuir.archive.server.auxiliary.parser.exception.ParserException;
import com.bsuir.archive.server.auxiliary.parser.implamentation.XMLParser;
import com.bsuir.archive.server.auxiliary.writer.WriterFile;
import com.bsuir.archive.server.auxiliary.writer.exception.FileWriterException;
import com.bsuir.archive.server.auxiliary.writer.implamentation.FileWriters;
import com.bsuir.archive.server.dao.DossierDAO;
import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.Dossier;

import java.util.ArrayList;
import java.util.List;

public class XMLDossierDAO implements DossierDAO{

    private static XMLDossierDAO instance = new XMLDossierDAO();
    private WriterFile writer;
    private XMLParsers parser;
    private static final String PATH = System.getProperty("user.dir")+ "/dossier.xml";
    private final static Object lock = new Object();

    public static XMLDossierDAO getInstance() {
        return instance;
    }
    private XMLDossierDAO(){
        writer = FileWriters.getInstance();
        parser = XMLParser.getInstance();
    }
    @Override
    public List<Dossier> getList() throws DAOException {
        synchronized (lock) {
            List<Dossier> list;
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
    public void setList(List<Dossier> list) throws DAOException {
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

    private String stringFormation (List<Dossier> list) throws DAOException{
        String dataToSave;
        Dossier classEx = new Dossier();
        try{
            dataToSave = parser.dataToXML(classEx.getClass(),list);
        }catch ( ParserException ex){
            throw new DAOException("Error parsing at file",ex);
        }
        return  dataToSave;
    }

    private  List<Dossier> listFormation (String savedData) throws DAOException{
        String dataToSave;
        List<Dossier> list;
        Dossier classEx = new Dossier();
        if ((savedData.isEmpty()) || (savedData == null)){
            list = new ArrayList<Dossier>();
        }
        else {
            try {
                list = parser.XMLToData(savedData, classEx);
            } catch (ParserException ex) {
                throw new DAOException("Error parsing of file ()", ex);
            }
        }
        return list;
    }


}

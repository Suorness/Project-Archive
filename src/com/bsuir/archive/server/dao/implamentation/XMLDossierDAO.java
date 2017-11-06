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

    private static XMLDossierDAO dossierDAO = new XMLDossierDAO();
    private WriterFile writer;
    private XMLParsers parser;
    public static XMLDossierDAO getInstance() {
        return dossierDAO;
    }
    private XMLDossierDAO(){
        writer = FileWriters.getInstance();
        parser = XMLParser.getInstance();
    }
    private static final String PATH = System.getProperty("user.dir")+ "/dossier.xml";

    @Override
    public List<Dossier> getAllDossier() throws DAOException {
        return null;
    }

    @Override
    public boolean addDossier(Dossier dossier) throws DAOException {
        Boolean result = true;
        String savedData;
        String dataToSave;

        try {
            savedData = writer.readData(PATH);
        }
        catch (FileWriterException ex){
            throw new DAOException("Error reading file",ex);
        }
        if (dossier!=null){
            dataToSave = stringFormation(dossier,savedData);
            try {
                writer.writeData(PATH,dataToSave);
            }
            catch (FileWriterException ex){
                throw new DAOException("Error writting in file",ex);
            }
        }
        return result;
    }

    private String stringFormation (Dossier dossier, String savedData) throws DAOException{
        String dataToSave;
        List<Dossier> list;
        if ((savedData.isEmpty()) || (savedData == null)){
            list = new ArrayList<Dossier>();
        }
        else {
            try {
                list = parser.XMLToData(savedData, dossier);
            } catch (ParserException ex) {
                throw new DAOException("Error parsing of file ()", ex);
            }
        }
        list.add(dossier);
        try{
            dataToSave = parser.dataToXML(dossier.getClass(),list);
        }catch ( ParserException ex){
            throw new DAOException("Error parsing at file",ex);
        }
        return  dataToSave;
    }

    @Override
    public boolean delDossier(Dossier dossier) throws DAOException {
        return false;
    }

    @Override
    public boolean changeDossier(Dossier dossier) throws DAOException {
        return false;
    }

    @Override
    public boolean clear() throws DAOException {
        return false;
    }
}

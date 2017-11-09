package com.bsuir.archive.server.service.implementation;

import com.bsuir.archive.server.dao.DAOFactory;
import com.bsuir.archive.server.dao.DossierDAO;
import com.bsuir.archive.server.dao.UserDAO;
import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.Dossier;
import com.bsuir.archive.server.service.DossierService;
import com.bsuir.archive.server.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class DossierServiceImplementation implements DossierService {
    public static DossierServiceImplementation getInstance(){
        return instance;
    }
    @Override
    public boolean addDossier(Dossier dossier) throws ServiceException {
        Boolean result = true;
        if (dossier!=null){
            List<Dossier> list;
            try {
                list = dossierDAO.getList();
                list.add(dossier);
                dossierDAO.setList(list);
            }catch (DAOException ex){
                throw new ServiceException(ex);
            }
        }
        else{
            result = false;
        }
        return result;
    }

    @Override
    public boolean delDossier(Dossier dossier) throws ServiceException {
        boolean result = false;
        List<Dossier> list;
        try {
            list = dossierDAO.getList();
        }catch (DAOException ex){
            throw new ServiceException(ex);
        }
        Dossier forDel = null;
        for (Dossier item: list) {
            if (item.equals(dossier)){
                forDel = item;
            }
        }
        if (forDel!=null){
            result = true;
            list.remove(forDel);
            try {
                dossierDAO.setList(list);
            }catch (DAOException ex){
                throw new ServiceException(ex);
            }
        }
        return result;
    }

    @Override
    public boolean changeDossier(Dossier dossier, Dossier newDossier) throws ServiceException {
        boolean result = false;
        List<Dossier> list;
        try {
            list = dossierDAO.getList();
        }catch (DAOException ex){
            throw new ServiceException(ex);
        }
        Dossier forChange = null;
        for (Dossier item: list) {
            if (item.equals(dossier)){
                forChange = item;
            }
        }
        if (forChange!=null){
            result = true;
            list.remove(forChange);
            list.add(newDossier);
            try {
                dossierDAO.setList(list);
            }catch (DAOException ex){
                throw new ServiceException(ex);
            }
        }
        return  result;
    }

    @Override
    public void clear() throws ServiceException {
        List<Dossier> list = new ArrayList<Dossier>();
        try {
            dossierDAO.setList(list);
        }catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Dossier> getList() throws ServiceException {
        List<Dossier> list = null;
        try {
            list = dossierDAO.getList();
        }catch (DAOException ex){
            throw new ServiceException(ex);
        }
        return list;
    }

    private DossierServiceImplementation(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        dossierDAO = daoFactory.getDossierDAO();
    }
    DossierDAO dossierDAO;
    private static DossierServiceImplementation instance = new DossierServiceImplementation();

}

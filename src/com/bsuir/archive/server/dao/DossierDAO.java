package com.bsuir.archive.server.dao;

import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.Dossier;

import java.util.List;

public interface DossierDAO {
    List<Dossier> getAllDossier() throws DAOException;
    boolean addDossier(Dossier dossier) throws DAOException;
    boolean delDossier(Dossier dossier) throws DAOException;
    boolean changeDossier(Dossier dossier)throws DAOException;
    boolean clear() throws DAOException;
}

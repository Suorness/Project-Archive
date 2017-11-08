package com.bsuir.archive.server.dao;

import com.bsuir.archive.server.dao.exception.DAOException;
import com.bsuir.archive.server.domain.Dossier;
import java.util.List;

public interface DossierDAO {
    List<Dossier> getList() throws DAOException;
    void setList(List<Dossier> list) throws DAOException;
}

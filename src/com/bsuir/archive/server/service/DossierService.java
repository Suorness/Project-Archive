package com.bsuir.archive.server.service;

import com.bsuir.archive.server.domain.Dossier;
import com.bsuir.archive.server.service.exception.ServiceException;

public interface DossierService {
    boolean addDossier(Dossier dossier) throws ServiceException;
    boolean delDossier(Dossier dossier) throws ServiceException;
    boolean changeDossier(Dossier dossier,Dossier newDossier) throws ServiceException;
    void clear() throws ServiceException;
}

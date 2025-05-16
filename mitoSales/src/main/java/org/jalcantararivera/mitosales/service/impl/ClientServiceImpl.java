package org.jalcantararivera.mitosales.service.impl;

import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.model.Client;
import org.jalcantararivera.mitosales.repo.IClientRepo;
import org.jalcantararivera.mitosales.repo.IGenericRepo;
import org.jalcantararivera.mitosales.service.IClientService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client,Integer> implements IClientService {

    private final IClientRepo repo;
    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }


}

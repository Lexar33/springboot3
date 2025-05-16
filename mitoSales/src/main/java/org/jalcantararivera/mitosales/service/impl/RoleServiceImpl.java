package org.jalcantararivera.mitosales.service.impl;

import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.model.Role;
import org.jalcantararivera.mitosales.repo.IGenericRepo;
import org.jalcantararivera.mitosales.repo.IRoleRepo;
import org.jalcantararivera.mitosales.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role,Integer> implements IRoleService {

    private final IRoleRepo repo;
    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }


}


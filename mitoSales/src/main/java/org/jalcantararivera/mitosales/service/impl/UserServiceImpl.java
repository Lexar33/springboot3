package org.jalcantararivera.mitosales.service.impl;

import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.model.User;
import org.jalcantararivera.mitosales.repo.IGenericRepo;
import org.jalcantararivera.mitosales.repo.IUserRepo;
import org.jalcantararivera.mitosales.service.IUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User,Integer> implements IUserService {

    private final IUserRepo repo;
    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }

}

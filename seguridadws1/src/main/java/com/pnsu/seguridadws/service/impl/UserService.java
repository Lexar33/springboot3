package com.pnsu.seguridadws.service.impl;

import com.pnsu.seguridadws.model.User;
import com.pnsu.seguridadws.repo.IGenericRepo;
import com.pnsu.seguridadws.repo.IUserRepo;
import com.pnsu.seguridadws.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends GenericService<User,Integer> implements IUserService {

    private final IUserRepo userRepo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return userRepo;
    }
}

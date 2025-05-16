package com.pnsu.seguridadws.repo;

import com.pnsu.seguridadws.model.User;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepo extends IGenericRepo<User,Integer> {
    //@Query("FROM User u WHERE u.username = :username")
    User findOneByUsername(String username);

}
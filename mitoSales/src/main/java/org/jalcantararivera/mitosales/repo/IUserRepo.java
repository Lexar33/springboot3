package org.jalcantararivera.mitosales.repo;

import org.jalcantararivera.mitosales.model.User;

public interface IUserRepo extends IGenericRepo<User,Integer>{
    //@Query("FROM User u WHERE u.username =:username");
    User findOneByUsername(String username);

}

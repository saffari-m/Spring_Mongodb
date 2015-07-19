package com.springmongo.Service;


import com.springmongo.Base.BaseService;
import com.springmongo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Mohammad on 7/17/2015.
 */
@Service
public class UserService extends BaseService<User> {
    //public User findByName(String name);
    //public String findAndModify(String id);
}

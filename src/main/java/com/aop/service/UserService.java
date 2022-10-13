package com.aop.service;

import com.aop.dao.UserDao;
import com.aop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct");
        userDao.createUser( new User().builder().id(1L).name("Kadir").surname("İrpik").age(26).build());
        userDao.createUser( new User().builder().id(2L).name("Bayram").surname("İrpik").age(22).build());
        userDao.createUser( new User().builder().id(3L).name("Ünal").surname("İrpik").age(25).build());
    }

    public List<User> userGetAll(){
        System.out.println("userGetAll");
        return userDao.userGetAll();
    }

    public String createUser(User user){
        System.err.println("createUser");
        return userDao.createUser(user);
    }

    public String deleteUser(User user){
        System.out.println("deleteUser");
        return userDao.deleteUser(user);
    }

    public String updateUser(User user){
        System.out.println("updateUser");
        return userDao.updateUser(user);
    }

}

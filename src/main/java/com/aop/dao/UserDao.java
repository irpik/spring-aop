package com.aop.dao;

import com.aop.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    public List<User> userList = new ArrayList<>();

    public List<User> userGetAll(){
        return userList;
    }

    public String createUser(User user){
        userList.add(user);
        return "Kullanıcı kaydedildi.";
    }

    public String deleteUser(User user){
        return "Silme " + (userList.remove(user) ? "başarılı.":"başarısız!");
    }

    public String updateUser(User user){
        userList.forEach((u)->{
            if (u.getId() == user.getId()){
                u.setName(user.getName());
                u.setSurname(user.getSurname());
                u.setAge(user.getAge());
            }
        });
        return "Güncellendi.";
    }

}

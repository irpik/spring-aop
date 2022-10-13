package com.aop.controller;

import com.aop.model.User;
import com.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/demo")
    @ResponseBody
    public String allMethods(){
        userService.createUser(new User().builder().id(4L).name("Demo").surname("Demo").age(18).build());
        StringBuilder message = new StringBuilder();
        /*message.append(userService.userGetAll());
        message.append(userService.createUser(new User().builder().id(4L).name("Demo").surname("Demo").age(18).build()));
        message.append(userService.deleteUser(new User().builder().id(1L).name("Kadir").surname("İrpik").age(26).build()));
        message.append(userService.userGetAll());
        message.append(userService.updateUser(new User().builder().id(4L).name("Demo").surname("Update").age(18).build()));
        message.append(userService.userGetAll());*/
        return message.toString();
    }

}

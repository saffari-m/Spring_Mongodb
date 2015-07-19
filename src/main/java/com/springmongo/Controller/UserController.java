package com.springmongo.Controller;

import com.springmongo.Model.User;
import com.springmongo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad on 7/17/2015.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/", method = RequestMethod.GET)
    public String helloWorld() {
        /*if(userService == null)
            userService = new UserService();*/
        List users = userService.getAll();
        return "user";
    }

    @RequestMapping(value = "user/get", method = RequestMethod.GET)
    public @ResponseBody List<User> getUser() {
        List users = userService.getAll();
        return users;
    }
    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    public @ResponseBody String saveUser(User user) {
        try {
            //userService.findAndModify(user.getId());

            //if(userService.findByName(user.getName()) == null){
            user.id = null;
            userService.save(user);
            //}

            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @RequestMapping(value = "user/search", method = RequestMethod.POST)
    public @ResponseBody List<User> searchUser(User user) {

        List<User> users = new ArrayList<User>();
        if(user != null){
            String[] properties = {"name","family"};
            Object[] values = {user.getName(),user.getFamily()};
            users  = userService.find(properties , values);
        }
        return users;
    }
}

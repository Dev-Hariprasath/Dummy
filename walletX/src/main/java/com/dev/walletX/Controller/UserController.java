package com.dev.walletX.Controller;


import com.dev.walletX.Model.Users;
import com.dev.walletX.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("walletX")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Welcome to Telusko "+request.getSession().getId();
    }


    @GetMapping("/Users")
    public List<Users> getUsers(){
        return service.getUsers();
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }

    @DeleteMapping("/Users/{id}")
    public String delete(@PathVariable Long id){
        return service.deleteUserById(id);
    }

    @GetMapping("/Users/{id}")
    public Users getUser(@PathVariable Long id){
        return service.getUserById(id);
    }

    @GetMapping("/Users/{name}")
    public Users getUserByName(@PathVariable String name){
        return service.getUserByUsername(name);
    }
}


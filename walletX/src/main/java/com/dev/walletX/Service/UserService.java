package com.dev.walletX.Service;

import com.dev.walletX.Model.Users;
import com.dev.walletX.Repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserDao repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }

    public List<Users> getUsers() {
        return repo.findAll();
    }

    public String deleteUserById(long id) {
         repo.deleteById(id);
        return "Deleted Successfully";
    }

    public Users getUserById(Long id) {
        return repo.findById(id).orElse(new Users());
    }

    public Users getUserByUsername(String name) {
        return repo.findByUsername(name);
    }
}

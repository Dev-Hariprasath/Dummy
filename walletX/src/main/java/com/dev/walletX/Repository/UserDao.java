package com.dev.walletX.Repository;

import com.dev.walletX.Model.Account;
import com.dev.walletX.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDao extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}

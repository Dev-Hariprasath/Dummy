package com.dev.walletX.Controller;

import com.dev.walletX.Model.Account;
import com.dev.walletX.Model.Transactions;
import com.dev.walletX.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        return ResponseEntity.ok(accountService.updateAccount(id, accountDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(
            @RequestParam Long senderUserId,
            @RequestParam Long receiverUserId,
            @RequestParam double amount,
            @RequestParam String description) {

        return ResponseEntity.ok(accountService.transferMoney(senderUserId, receiverUserId, amount, description));
    }


    @GetMapping("/{userId}/transactions")
    public List<Transactions> getTransactions(@PathVariable Long userId) {
        return accountService.getTransactions(userId);
    }
}


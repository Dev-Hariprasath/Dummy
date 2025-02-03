package com.dev.walletX.Service;


import com.dev.walletX.Model.Account;
import com.dev.walletX.Model.Transactions;
import com.dev.walletX.Model.Users;
import com.dev.walletX.Repository.AccountDao;
import com.dev.walletX.Repository.TransactionsDao;
import com.dev.walletX.Repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountRepository;

    @Autowired
    private UserDao UserRepository;

    @Autowired
    private TransactionsDao transactionRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account createAccount(Account account) {
        Long id = account.getUser().getId();
        Users user = UserRepository.findById(id).orElse(new Users());
        account.setUser(user);
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account accountDetails) {
        Account account = getAccountById(id);
        account.setBalance(accountDetails.getBalance());
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }

    @Transactional
    public String transferMoney(Long senderId, Long receiverId, double amount, String description) {
        if (senderId.equals(receiverId)) {
            throw new IllegalArgumentException("Cannot send money to self");
        }

        Account senderAccount = getAccountById(senderId);
        Account receiverAccount = getAccountById(receiverId);

        if (senderAccount.getBalance() < amount || amount <= 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transactions transaction = new Transactions(senderAccount, receiverAccount, amount, LocalDateTime.now(), description);
        transactionRepository.save(transaction);

        return "Transfer successful";
    }


    public List<Transactions> getTransactions(Long userId) {
        Account account = getAccountById(userId);
        return transactionRepository.findBySenderAccountOrReceiverAccount(account, account);
    }
}


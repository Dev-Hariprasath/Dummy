package com.dev.walletX.Repository;

import com.dev.walletX.Model.Account;
import com.dev.walletX.Model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionsDao extends JpaRepository<Transactions, Long> {
    List<Transactions> findBySenderAccountOrReceiverAccount(Account senderAccount, Account receiverAccount);
}

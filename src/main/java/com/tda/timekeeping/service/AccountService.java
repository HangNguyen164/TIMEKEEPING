package com.tda.timekeeping.service;

import com.tda.timekeeping.entity.Account;
import com.tda.timekeeping.repository.AccountRepository;
import com.tda.timekeeping.service.impl.AccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements AccountImpl {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account login(String username, String password) {
        return accountRepository.login(username,password);
    }

    @Override
    public void addNewAccount(List<Account> listAccount) {
        for (Account account : listAccount) {
            Account getAccount = accountRepository.getOneAccountByUsername(account.getUsername());
            if (getAccount == null) {
                accountRepository.save(account);
            }
        }
    }

}

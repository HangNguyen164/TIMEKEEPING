package com.tda.timekeeping.service;

import com.tda.timekeeping.entity.Account;
import com.tda.timekeeping.repository.AccountRepository;
import com.tda.timekeeping.service.impl.AccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements AccountImpl {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getOne(String username) {
        return accountRepository.getOne(username);
    }
}

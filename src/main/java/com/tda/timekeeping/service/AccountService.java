package com.tda.timekeeping.service;

import com.tda.timekeeping.entity.Account;
import com.tda.timekeeping.repository.AccountRepository;
import com.tda.timekeeping.service.impl.AccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements AccountImpl {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getOne(String username) {
        return accountRepository.getOne(username);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void add(Account account) {
        accountRepository.save(account);
    }
//    https://www.baeldung.com/spring-transactional-propagation-isolation
//    https://www.slideshare.net/DucNguyenQuang3/transaction-isolation-level-vietnamese
}

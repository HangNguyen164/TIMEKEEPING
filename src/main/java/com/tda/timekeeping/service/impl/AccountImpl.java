package com.tda.timekeeping.service.impl;

import com.tda.timekeeping.entity.Account;

import java.util.List;

public interface AccountImpl {
    boolean addNewAccount(List<Account> listAccount);

    List<Account> getAll();
}

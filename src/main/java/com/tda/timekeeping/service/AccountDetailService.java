package com.tda.timekeeping.service;

import com.tda.timekeeping.repository.AccountDetailRepository;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDetailService {
    @Autowired
    private AccountDetailRepository accountDetailRepository;

    public List<AccountDetailVo> getAllByUsername(String username) {
        return accountDetailRepository.getAllByUsername(username);
    }
}

package com.tda.timekeeping.service;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.repository.AccountDetailRepository;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;

@Service
public class AccountDetailService {
    @Autowired
    private AccountDetailRepository accountDetailRepository;

    public List<AccountDetailVo> getAllByUsername(String username) {
        return accountDetailRepository.getAllByUsername(username);
    }

    @Transactional
    public List<AccountDetailVo> getAll(int month) {
        return accountDetailRepository.getAll(month);
    }

    public AccountDetail getOne(int id) {
        return accountDetailRepository.getOne(id);
    }

    @Transactional
    public void update(Time startTime, Time endTime, String note, int sendMail, int id) {
        accountDetailRepository.update(startTime, endTime, note, sendMail, id);
    }
}

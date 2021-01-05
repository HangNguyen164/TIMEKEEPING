package com.tda.timekeeping.service;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.repository.AccountDetailRepository;
import com.tda.timekeeping.service.impl.AccountDetailImpl;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;

import static com.tda.timekeeping.util.Helper.checkMonthChoose;

@Service
public class AccountDetailService implements AccountDetailImpl {
    @Autowired
    private AccountDetailRepository accountDetailRepository;

    @Override
    public List<AccountDetailVo> getAccountDetailVosByUsername(String username) {
        return accountDetailRepository.getAccountDetailVosByUsername(username);
    }

    @Override
    public List<AccountDetailVo> getAccountDetailVosInMonth(String monthChoose) {
        return accountDetailRepository.getAccountDetailVosInMonth(checkMonthChoose(monthChoose));
    }

    @Override
    public List<AccountDetailVo> getAccountDetailVosByUsernameInMonth(String username, String monthChoose) {
        return accountDetailRepository.getAccountDetailVosByUsernameInMonth(username, checkMonthChoose(monthChoose));
    }

    @Override
    public AccountDetail getOne(int id) {
        return accountDetailRepository.getOne(id);
    }

    @Override
    @Transactional
    public void update(Time startTime, Time endTime, String note, int sendMail, int id) {
        accountDetailRepository.update(startTime, endTime, note, sendMail, id);
    }
}

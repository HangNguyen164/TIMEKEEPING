package com.tda.timekeeping.service.impl;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.vo.AccountDetailVo;

import java.sql.Time;
import java.util.List;

public interface AccountDetailImpl {
    List<AccountDetailVo> getAccountDetailVosByUsername(String username);

    List<AccountDetailVo> getAccountDetailVosInMonth(String monthChoose);

    List<AccountDetailVo> getAccountDetailVosByUsernameInMonth(String username, String monthChoose);

    void update(Time startTime, Time endTime, String note, int sendMail, int id);

    AccountDetail getOne(int id);
}

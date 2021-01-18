package com.tda.timekeeping.service.impl;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.vo.AccountDetailVo;

import java.sql.Time;
import java.util.List;

public interface AccountDetailImpl {

    List<AccountDetailVo> getAccountDetailVosInMonthYear(String monthChoose, String yearChoose);

    List<AccountDetailVo> getAccountDetailVosByUsernameInMonthInYear(String username, String monthChoose, String yearChoose);

    void update(Time startTime, Time endTime, String note, int sendMail, int id);

    boolean addNewAccountDetail(List<AccountDetail> lists);

    void updateFullInfoAccountDetail(AccountDetail accountDetailOld, AccountDetail accountDetailNew);
}

package com.tda.timekeeping.service.impl;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.vo.AccountDetailVo;

import java.sql.Time;
import java.util.List;

public interface AccountDetailImpl {

    List<AccountDetailVo> getAccountDetailVosInMonth(String monthChoose,String yearChoose);

    List<AccountDetailVo> getAccountDetailVosByUsernameInMonthInYear(String username, String monthChoose,String yearChoose);

    void update(Time startTime, Time endTime, String note, int sendMail, int id);

    void addNewAccountDetail(List<AccountDetail> lists);

    void updateFullInfoAccountDetail(AccountDetail accountDetail);
}

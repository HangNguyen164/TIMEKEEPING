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
    @Transactional
    public void update(Time startTime, Time endTime, String note, int sendMail, int id) {
        accountDetailRepository.update(startTime, endTime, note, sendMail, id);
    }

    @Override
    public void updateFullInfoAccountDetail(AccountDetail accountDetail) {
        AccountDetail ad = accountDetailRepository.getOneByUsernameAndDate(accountDetail.getUsername(), accountDetail.getWorkDate());
        ad.setName(accountDetail.getName());
        ad.setPosition(accountDetail.getPosition());
        ad.setDepartment(accountDetail.getDepartment());
        ad.setStartTime(accountDetail.getStartTime());
        ad.setEndTime(accountDetail.getEndTime());
        ad.setNote(accountDetail.getNote());
        ad.setCheckEmail(accountDetail.getCheckEmail());
        accountDetailRepository.save(ad);

    }

    @Override
    public void addNewAccountDetail(List<AccountDetail> lists) {
        for (AccountDetail ad : lists) {
            AccountDetail accountDetail = accountDetailRepository.getOneByUsernameAndDate(ad.getUsername(), ad.getWorkDate());
            if (accountDetail == null) {
                accountDetailRepository.save(ad);
            } else {
                // update
                updateFullInfoAccountDetail(accountDetail);
            }
        }
    }
}

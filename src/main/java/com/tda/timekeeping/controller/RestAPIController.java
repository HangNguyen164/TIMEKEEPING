package com.tda.timekeeping.controller;

import com.tda.timekeeping.body.RequestBodyContent;
import com.tda.timekeeping.body.RequestBodySearch;
import com.tda.timekeeping.service.impl.AccountDetailImpl;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.tda.timekeeping.util.Helper.*;

@RestController
@RequestMapping(path = "/user")
public class RestAPIController {

    @Autowired
    private AccountDetailImpl accountDetailImpl;

    @PostMapping("/list")
    RequestBodyContent hello(@RequestBody RequestBodySearch requestBodySearch, HttpSession session) {
        UserDetails account = (UserDetails) session.getAttribute("account");

        List<AccountDetailVo> accountDetailVoListByUser = accountDetailImpl.getAccountDetailVosByUsernameInMonthInYear
                (account.getUsername(), requestBodySearch.getMonth(), requestBodySearch.getYear());
        int totalNotWorkInOffice = totalNotWorkInOffice(accountDetailVoListByUser);
        String totalWorkInMonth = totalWorkInMonth(accountDetailVoListByUser);
        String listDayWorkNotFull = listDayWorkNotFull(accountDetailVoListByUser);
        RequestBodyContent a = new RequestBodyContent(accountDetailVoListByUser, totalNotWorkInOffice, totalWorkInMonth, listDayWorkNotFull);
        return a;

    }
}

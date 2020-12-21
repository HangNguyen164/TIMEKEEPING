package com.controller;

import com.service.AccountDetailService;
import com.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.util.Helper.*;

@Controller
public class HomeUserController {
    @Autowired
    private AccountDetailService accountDetailService;

    @RequestMapping(value = "/home-user")
    public String getAllInfo(Model model) {
        // current month
        int currentMonth = getTypeOfDate(new Date(), Calendar.MONTH);

        List<AccountDetailVo> accountDetailVoList = accountDetailService.getAllByUsername("TDAV0036");
        int totalNotWorkInOffice = totalNotWorkInOffice(accountDetailVoList, currentMonth);
        String totalWorkInMonth = totalWorkInMonth(accountDetailVoList, currentMonth);
        String listDayWorkNotFull = listDayWorkNotFull(accountDetailVoList, currentMonth);

        model.addAttribute("listAccountShow", accountDetailVoList);
        model.addAttribute("totalNotWorkInOffice", totalNotWorkInOffice);
        model.addAttribute("totalWorkInMonth", totalWorkInMonth);
        model.addAttribute("listDayWorkNotFull", listDayWorkNotFull);
        return "home";
    }
}

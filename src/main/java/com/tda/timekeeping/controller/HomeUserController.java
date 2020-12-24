package com.tda.timekeeping.controller;

import com.tda.timekeeping.service.AccountDetailService;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.tda.timekeeping.util.Helper.getTypeOfDate;
import static com.tda.timekeeping.util.Helper.totalNotWorkInOffice;
import static com.tda.timekeeping.util.Helper.totalWorkInMonth;
import static com.tda.timekeeping.util.Helper.listDayWorkNotFull;

@Controller
public class HomeUserController {
    @Autowired
    private AccountDetailService accountDetailService;

    @GetMapping(value = "/home-user")
    public String getAllInfo(Model model) {
        // current month
        int currentMonth = getTypeOfDate(new Date(), Calendar.MONTH);

        List<AccountDetailVo> accountDetailVoList = accountDetailService.getAllByUsername("TDAV0037");
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

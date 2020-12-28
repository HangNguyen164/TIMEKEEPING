package com.tda.timekeeping.controller;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.service.AccountDetailService;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.tda.timekeeping.util.Helper.*;

@Controller
public class HomeController {
    @Autowired
    private AccountDetailService accountDetailService;

    @GetMapping(value = "/home-user")
    public String getAllInfo(Model model) {
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

    @GetMapping(value = "/home-admin")
    public String getAll(Model model) {
        int currentMonth = getTypeOfDate(new Date(), Calendar.MONTH);
        List<AccountDetailVo> accountDetailVoList = accountDetailService.getAll();
        int totalNotWorkInOffice = totalNotWorkInOffice(accountDetailVoList, currentMonth);
        String totalWorkInMonth = totalWorkInMonth(accountDetailVoList, currentMonth);
        String listDayWorkNotFull = listDayWorkNotFull(accountDetailVoList, currentMonth);
        model.addAttribute("listAccountShow", accountDetailVoList);
        model.addAttribute("totalNotWorkInOffice", totalNotWorkInOffice);
        model.addAttribute("totalWorkInMonth", totalWorkInMonth);
        model.addAttribute("listDayWorkNotFull", listDayWorkNotFull);
        return "homeAdmin";
    }

    @PostMapping("/update/{id}")
    public String save(@PathVariable("id") int id, @RequestParam("startTime") String startTimeStr, @RequestParam("endTime") String endTimeStr, @RequestParam("note") String note, RedirectAttributes redirect, Model model) {
        AccountDetail accountDetail = accountDetailService.getOne(id);
        if (startTimeStr.length() > 0 && endTimeStr.length() > 0) {
            try {
                Time startTime = convert(startTimeStr);
                Time endTime = convert(endTimeStr);
                accountDetail.setStartTime(startTime);
                accountDetail.setEndTime(endTime);
                accountDetailService.update(startTime, endTime, note, 0, id);
                return "redirect:/home-admin";
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (startTimeStr.length() == 0 && endTimeStr.length() == 0) {
            System.out.println("Nghi");
        } else {
            System.out.println("Must full 2 time");
        }
        return "redirect:/home-admin";
    }
}

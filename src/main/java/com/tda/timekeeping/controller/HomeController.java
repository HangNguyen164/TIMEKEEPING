package com.tda.timekeeping.controller;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.service.AccountDetailService;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.tda.timekeeping.util.Helper.*;

@Controller
public class HomeController {
    @Autowired
    private AccountDetailService accountDetailService;
    int currentMonth = getTypeOfDate(new Date(), Calendar.MONTH) + 1;

    @GetMapping(value = "/home-user")
    public String getAllInfo(Model model) {

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

    @RequestMapping(value = "/home-admin")
    public String getAll(@RequestParam(value = "month", required = false) String monthChoose, Model model) {
        List<String> getAllMonth = getAllMonth();
        List<AccountDetailVo> accountDetailVoList;
        int totalNotWorkInOffice;
        String totalWorkInMonth, listDayWorkNotFull;
        int month;
        if (monthChoose == null) {
            month = currentMonth;
        } else {
            month = Integer.valueOf(monthChoose);
        }
        accountDetailVoList = accountDetailService.getAll(month);
        totalNotWorkInOffice = totalNotWorkInOffice(accountDetailVoList, month);
        totalWorkInMonth = totalWorkInMonth(accountDetailVoList, month);
        listDayWorkNotFull = listDayWorkNotFull(accountDetailVoList, month);
        model.addAttribute("listAccountShow", accountDetailVoList);
        model.addAttribute("totalNotWorkInOffice", totalNotWorkInOffice);
        model.addAttribute("totalWorkInMonth", totalWorkInMonth);
        model.addAttribute("listDayWorkNotFull", listDayWorkNotFull);
        model.addAttribute("getAllMonth", getAllMonth);
        return "homeAdmin";
    }

    @PostMapping("/update/{id}")
    public String save(@PathVariable("id") int id, @RequestParam("startTime") String startTimeStr, @RequestParam("endTime") String endTimeStr,
                       @RequestParam("sendMail") String sendMail,
                       @RequestParam("note") String note) {
        AccountDetail accountDetail = accountDetailService.getOne(id);
        if (startTimeStr.length() > 0 && endTimeStr.length() > 0) {
            try {
                Time startTime = convert(startTimeStr);
                Time endTime = convert(endTimeStr);
                accountDetail.setStartTime(startTime);
                accountDetail.setEndTime(endTime);
                accountDetail.setCheckEmail(Integer.valueOf(sendMail));
                accountDetailService.update(startTime, endTime, note, accountDetail.getCheckEmail(), id);
                return "redirect:/home-admin";
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        } else if (startTimeStr.length() == 0 && endTimeStr.length() == 0) {
            System.out.println("Nghi");
        } else {
            System.out.println("Must full 2 time");
        }
        return "redirect:/home-admin";
    }
}

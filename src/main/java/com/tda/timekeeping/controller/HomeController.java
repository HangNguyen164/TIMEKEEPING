package com.tda.timekeeping.controller;

import com.tda.timekeeping.entity.Account;
import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.service.impl.AccountDetailImpl;
import com.tda.timekeeping.util.Helper;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.tda.timekeeping.util.Helper.*;

@Controller
public class HomeController {
    @Autowired
    private AccountDetailImpl accountDetailImpl;
    private Helper helper;

    @GetMapping(value = "/home-user")
    public String getAllInfo(Model model) {
        int currentMonth = getTypeOfDate(new Date(), Calendar.MONTH) + 1;

        List<AccountDetailVo> accountDetailVoList = accountDetailImpl.getAccountDetailVosByUsername("TDAV0037");
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
        List<AccountDetailVo> accountDetailVoList = accountDetailImpl.getAccountDetailVosInMonth(monthChoose);
        model.addAttribute("listAccountShow", accountDetailVoList);
        model.addAttribute("getAllMonth", getAllMonth);
        model.addAttribute("month", checkMonthChoose(monthChoose));
        return "homeAdmin";
    }

    @PostMapping("/update/{id}")
    public String updateAccountDetail(@PathVariable("id") int id, @RequestParam("startTime") String startTimeStr, @RequestParam("endTime") String endTimeStr,
                                      @RequestParam("sendMail") String sendMail,
                                      @RequestParam("note") String note) {
        AccountDetail newAccountDetail = getAccountDetailWithNewInfo(startTimeStr, endTimeStr, sendMail, note);
        if (newAccountDetail != null) {
            accountDetailImpl.update(newAccountDetail.getStartTime(), newAccountDetail.getEndTime(), note, newAccountDetail.getCheckEmail(), id);
        }
        return "redirect:/home-admin";
    }

    @GetMapping("/home-admin/username/{username}/total/{monthChoose}")
    public String totalInfoAccountDetailInMonth(@PathVariable("username") String username, @PathVariable("monthChoose") String monthStr, Model model) {
        List<AccountDetailVo> accountDetailVoListByUser = accountDetailImpl.getAccountDetailVosByUsernameInMonth(username, monthStr);
        List<String> getAllMonth = getAllMonth();
        List<AccountDetailVo> accountDetailVoList;
        accountDetailVoList = accountDetailImpl.getAccountDetailVosInMonth(monthStr);
        int month = Integer.valueOf(monthStr);
        int totalNotWorkInOffice = totalNotWorkInOffice(accountDetailVoListByUser, month);
        String totalWorkInMonth = totalWorkInMonth(accountDetailVoListByUser, month);
        String listDayWorkNotFull = listDayWorkNotFull(accountDetailVoListByUser, month);

        model.addAttribute("listAccountShow", accountDetailVoListByUser);
        model.addAttribute("totalNotWorkInOffice", totalNotWorkInOffice);
        model.addAttribute("totalWorkInMonth", totalWorkInMonth);
        model.addAttribute("listDayWorkNotFull", listDayWorkNotFull);
        model.addAttribute("listAccountShow", accountDetailVoList);
        model.addAttribute("getAllMonth", getAllMonth);
        model.addAttribute("month", checkMonthChoose(monthStr));
        return "homeAdmin";
    }

    @RequestMapping(value = "/home-admin/add")
    public String addDataFromExcel(Model model, @RequestParam("fileinput") MultipartFile fileName) {
        // add data from excel
        String pathFile = fileName.getOriginalFilename();
        Account account = getAccountFromExcel(pathFile);
        AccountDetail accountDetail = getAccountDetailFromExcel(pathFile);

        // add Account


        // add AccountDetail
        return "redirect:/home-admin";
    }
}

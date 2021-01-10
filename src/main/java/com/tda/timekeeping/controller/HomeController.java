package com.tda.timekeeping.controller;

import com.tda.timekeeping.entity.Account;
import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.service.impl.AccountDetailImpl;
import com.tda.timekeeping.service.impl.AccountImpl;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.tda.timekeeping.io.ImportDataFromExcel.getAccountDetailFromExcel;
import static com.tda.timekeeping.io.ImportDataFromExcel.getAccountFromExcel;
import static com.tda.timekeeping.util.Helper.*;

@Controller
public class HomeController {
    @Autowired
    private AccountDetailImpl accountDetailImpl;

    @Autowired
    private AccountImpl accountImpl;

    @RequestMapping(value = "/home-user")
    public String getAllInfo(@RequestParam(value = "month", required = false) String monthChoose, @RequestParam(value = "year", required = false) String yearChoose,Model model) {
        List<String> getAllMonth = getAllMonth();
        List<String> getAllYear = getAllYear();
        List<AccountDetailVo> accountDetailVoListByUser = accountDetailImpl.getAccountDetailVosByUsernameInMonthInYear("TDAV0006",monthChoose, yearChoose);

        int month = checkMonthChoose(monthChoose);
        int year = checkMonthChoose(yearChoose);
        int totalNotWorkInOffice = totalNotWorkInOffice(accountDetailVoListByUser, month, year);
        String totalWorkInMonth = totalWorkInMonth(accountDetailVoListByUser, month, year);
        String listDayWorkNotFull = listDayWorkNotFull(accountDetailVoListByUser, month, year);

        model.addAttribute("listAccountShow", accountDetailVoListByUser);
        model.addAttribute("totalNotWorkInOffice", totalNotWorkInOffice);
        model.addAttribute("totalWorkInMonth", totalWorkInMonth);
        model.addAttribute("listDayWorkNotFull", listDayWorkNotFull);
        model.addAttribute("getAllMonth", getAllMonth);
        model.addAttribute("getAllYear", getAllYear);
        model.addAttribute("listAccountShow", accountDetailVoListByUser);

        return "home";
    }

    @RequestMapping(value = "/home-admin")
    public String getAll(@RequestParam(value = "month", required = false) String monthChoose, @RequestParam(value = "year", required = false) String yearChoose, Model model) {
        List<String> getAllMonth = getAllMonth();
        List<String> getAllYear = getAllYear();
        List<AccountDetailVo> accountDetailVoList = accountDetailImpl.getAccountDetailVosInMonth(monthChoose, yearChoose);
        model.addAttribute("listAccountShow", accountDetailVoList);
        model.addAttribute("getAllMonth", getAllMonth);
        model.addAttribute("getAllYear", getAllYear);
        model.addAttribute("month", checkMonthChoose(monthChoose));
        model.addAttribute("year", checkYearChoose(yearChoose));
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

    @GetMapping("/home-admin/username/{username}/total/{monthChoose}/{yearChoose}")
    public String totalInfoAccountDetailInMonth(@PathVariable("username") String username, @PathVariable("monthChoose") String monthStr,
                                                @PathVariable("yearChoose") String yearChoose, Model model) {
        List<AccountDetailVo> accountDetailVoListByUser = accountDetailImpl.getAccountDetailVosByUsernameInMonthInYear(username, monthStr, yearChoose);
        List<String> getAllMonth = getAllMonth();
        List<String> getAllYear = getAllYear();
        List<AccountDetailVo> accountDetailVoList;
        accountDetailVoList = accountDetailImpl.getAccountDetailVosInMonth(monthStr, yearChoose);
        int month = Integer.valueOf(monthStr);
        int year = Integer.valueOf(yearChoose);
        int totalNotWorkInOffice = totalNotWorkInOffice(accountDetailVoListByUser, month, year);
        String totalWorkInMonth = totalWorkInMonth(accountDetailVoListByUser, month, year);
        String listDayWorkNotFull = listDayWorkNotFull(accountDetailVoListByUser, month, year);

        model.addAttribute("listAccountShow", accountDetailVoListByUser);
        model.addAttribute("totalNotWorkInOffice", totalNotWorkInOffice);
        model.addAttribute("totalWorkInMonth", totalWorkInMonth);
        model.addAttribute("listDayWorkNotFull", listDayWorkNotFull);
        model.addAttribute("listAccountShow", accountDetailVoList);
        model.addAttribute("getAllMonth", getAllMonth);
        model.addAttribute("getAllYear", getAllYear);
        model.addAttribute("month", checkMonthChoose(monthStr));
        model.addAttribute("year", checkYearChoose(yearChoose));
//        return "redirect:/home-admin?month="+monthStr+"&year="+yearChoose;
        return "homeAdmin";
    }

    @PostMapping(value = "/home-admin/add")
    public String addDataFromExcel(@RequestParam("fileinput") MultipartFile fileName) throws IOException {
        List<Account> listAccount = new ArrayList<>();
        List<AccountDetail> listAccountDetail = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(fileName.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 3; i < worksheet.getPhysicalNumberOfRows(); i++) {
            listAccountDetail.add(getAccountDetailFromExcel(worksheet, i));
            listAccount.add(getAccountFromExcel(worksheet, i));
        }
        accountImpl.addNewAccount(listAccount);
        accountDetailImpl.addNewAccountDetail(listAccountDetail);

        return "redirect:/home-admin";
    }
}

package com.tda.timekeeping.io;

import com.tda.timekeeping.entity.Account;
import com.tda.timekeeping.entity.AccountDetail;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.sql.Time;
import java.util.Date;

public class ImportDataFromExcel {
    public static Account getAccountFromExcel(XSSFSheet worksheet, int index) {
        Account account = new Account();
        XSSFRow row = worksheet.getRow(index);
        account.setUsername(row.getCell(0).getStringCellValue());
        account.setRole("USER");
        return account;
    }

    public static AccountDetail getAccountDetailFromExcel(XSSFSheet worksheet, int index) {
        AccountDetail accountDetail = new AccountDetail();
        XSSFRow row = worksheet.getRow(index);

        accountDetail.setUsername(row.getCell(0).getStringCellValue());
        accountDetail.setName(row.getCell(1).getStringCellValue());
        accountDetail.setDepartment(row.getCell(2).getStringCellValue());
        accountDetail.setPosition(row.getCell(3).getStringCellValue());
        accountDetail.setWorkDate(new java.sql.Date(row.getCell(4).getDateCellValue().getTime()));
        accountDetail.setStartTime(covertDateToTime(row.getCell(6).getDateCellValue()));
        accountDetail.setEndTime(covertDateToTime(row.getCell(7).getDateCellValue()));
        accountDetail.setNote(row.getCell(9).getStringCellValue());
        accountDetail.setCheckEmail(checkMailNumber(row.getCell(10).getStringCellValue()));

        return accountDetail;
    }

    private static int checkMailNumber(String email) {
        return email == "" ? 0 : email.equalsIgnoreCase("Chưa mail") ? 1 : 2;
    }

    private static Time covertDateToTime(Date date) {
        return date != null ? new Time(date.getTime()) : null;
    }
}

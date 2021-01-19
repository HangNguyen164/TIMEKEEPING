package com.tda.timekeeping.body;

import com.tda.timekeeping.vo.AccountDetailVo;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestBodyContent {
    private List<AccountDetailVo> accountDetailVoListByUser;
    private int totalNotWorkInOffice;
    private String totalWorkInMonth;
    private String listDayWorkNotFull;
}

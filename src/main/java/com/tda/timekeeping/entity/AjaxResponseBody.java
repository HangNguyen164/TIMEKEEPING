package com.tda.timekeeping.entity;

import com.tda.timekeeping.vo.AccountDetailVo;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AjaxResponseBody {
    private String msg;
    private List<AccountDetailVo> result;
}

package com.tda.timekeeping.repository;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {
    String query = "SELECT new com.tda.timekeeping.vo.AccountDetailVo(ad.id,ad.username,ad.name,ad.department,ad.position," +
            "ad.workDate,ad.startTime,ad.endTime ,ad.note,ad.checkEmail) FROM AccountDetail ad";

    @Query(value = query + " Where  ad.username=:username ORDER BY ad.id")
    List<AccountDetailVo> getAccountDetailVosByUsername(@Param("username") String username);

    @Query(value = query + " Where  extract(month from ad.workDate)=:monthChoose AND ad.username=:username ORDER BY ad.id")
    List<AccountDetailVo> getAccountDetailVosByUsernameInMonth(@Param("username") String username,@Param("monthChoose")int monthChoose);

    @Modifying
    @Query(value = query + " WHERE extract(month from ad.workDate)=:monthChoose ORDER BY ad.id")
    List<AccountDetailVo> getAccountDetailVosInMonth(@Param("monthChoose") int monthChoose);

    @Modifying
    @Query(value = "UPDATE  AccountDetail  a set a.startTime=:start_time, a.endTime=:end_time, a.note=:note, a.checkEmail=:send_mail WHERE a.id=:id")
    void update(@Param("start_time") Time startTime, @Param("end_time") Time endTime, @Param("note") String note, @Param("send_mail") int sendMail, @Param("id") int id);

}

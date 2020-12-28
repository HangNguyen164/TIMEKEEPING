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
    String query = "select new com.tda.timekeeping.vo.AccountDetailVo(ad.id,ad.username,ad.name,ad.department,ad.position,ad.workDate,ad.startTime,ad.endTime ,ad.note,ad.checkEmail) FROM AccountDetail ad";

    @Query(value = query + " Where  ad.username=:username")
    List<AccountDetailVo> getAllByUsername(@Param("username") String username);

    @Query(value = query)
    List<AccountDetailVo> getAll();

    @Modifying
    @Query(value = "update  AccountDetail  a set a.startTime=:start_time, a.endTime=:end_time, a.note=:note, a.checkEmail=:send_mail WHERE a.id=:id")
    void update(@Param("start_time") Time startTime, @Param("end_time") Time endTime, @Param("note") String note, @Param("send_mail") int sendMail, @Param("id") int id);

}

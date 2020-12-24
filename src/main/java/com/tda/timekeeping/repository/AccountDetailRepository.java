package com.tda.timekeeping.repository;

import com.tda.timekeeping.entity.AccountDetail;
import com.tda.timekeeping.vo.AccountDetailVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {
    String query = "select new com.tda.timekeeping.vo.AccountDetailVo(ad.username,ad.name,ad.department,ad.position,ad.workDate,ad.startTime,ad.endTime ,ad.note,ad.checkEmail) FROM AccountDetail ad";

    @Query(value = query + " Where  ad.username=:username")
    List<AccountDetailVo> getAllByUsername(@Param("username") String username);
}

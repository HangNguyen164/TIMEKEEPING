package com.repository;

import com.entity.AccountDetail;
import com.vo.AccountDetailVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {
    @Query(value = "select new com.vo.AccountDetailVo(ad.id,ad.username,ad.name,ad.department,ad.position,ad.workDate,ad.startTime,ad.endTime ,ad.note,ad.checkEmail) " +
            "FROM AccountDetail  ad Where  ad.username=:username")
    List<AccountDetailVo> getAllByUsername(@Param("username") String username);

    @Query(value = "select new com.vo.AccountDetailVo(ad.id,ad.username,ad.name,ad.department,ad.position,ad.workDate,ad.startTime,ad.endTime ,ad.note,ad.checkEmail) " +
            "FROM AccountDetail  ad")
    List<AccountDetailVo> getAll();

}

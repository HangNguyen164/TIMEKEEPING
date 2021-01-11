package com.tda.timekeeping.repository;

import com.tda.timekeeping.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT new com.tda.timekeeping.entity.Account(a.username,a.password) FROM Account  a WHERE a.username=:username")
    Account getOneAccountByUsername(@Param("username") String username);

}

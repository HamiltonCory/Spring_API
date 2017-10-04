package com.cory.Spring_API.Repositories;

import com.cory.Spring_API.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

    Account findByAccount_Id(int account_id);
}

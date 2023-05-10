package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByEmail(String email);

    public Account findByEmailAndPwd(String email, String password);

    @Query(value = "select acc " +
            "from Account acc " +
            "where acc.email = :email and acc.role.id = :roleID")
    public Account findByEmailAndRoleID(@Param("email") String email, @Param("roleID") Long roleID);
}

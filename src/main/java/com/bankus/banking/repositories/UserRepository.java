package com.bankus.banking.repositories;

import com.bankus.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{

    // select * from User where firstname =  "..."
    List<User> findAllByFirstname(String firstname);

    // select * from User where firstname like  "%...%"
    List<User> findAllByFirstnameContaining(String firstname);

    // select * from User where firstname like  "%...%" - // no matter the case
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    // select * from User u inner join account a on u.id = a.id_user and a.iban = 'DE233253532'
    List<User> findAllByAccountIban(String iban);

    // select * from User where firstname like '%...%' and email = '....@....com'
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    // Writing for JPQL request
    @Query("from User where firstname = :fn")
    List<User> searchByFirstname(@Param("fn") String firstname);

    @Query("from User where firstname = '%:firstname%'")
    List<User> searchByFirstnameContaining(String firstname);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(String iban);

    // Native Query
    @Query(value = "select * from _user u inner join account a on u.id = a.id_user and a.iban = :iban", nativeQuery = true)
    List<User> searchByIbanNative(String iban);



}

package com.sherpaerp.library.borrowersmicroservice.entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowersRepository extends JpaRepository<Borrowers, Long> {
	List<Borrowers> findById(int id);
	List<Borrowers> findByFirstname(String firstname);
	List<Borrowers> findByLastname(String lastname);
	List<Borrowers> findByFirstnameAndLastname(String firstname, String lastname);
}
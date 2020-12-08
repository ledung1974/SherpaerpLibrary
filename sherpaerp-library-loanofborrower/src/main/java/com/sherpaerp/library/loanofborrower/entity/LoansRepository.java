package com.sherpaerp.library.loanofborrower.entity;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
	List<Loans> findByBookId (int bookId);
	List<Loans> findByBorrowerId (int borrowerId);
	List<Loans> findByBookIdAndBorrowerIdAndBorrowingDate (int bookId, int borrowerId, Date borrowingDate);
}
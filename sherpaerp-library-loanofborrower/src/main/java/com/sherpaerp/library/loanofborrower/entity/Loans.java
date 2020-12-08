package com.sherpaerp.library.loanofborrower.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbloans")
public class Loans {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name = "borrower_id", nullable = false)
	private int borrowerId;
	
	@Column(name = "book_id", nullable = false)
	private int bookId;
	
	@Column(name = "borrowing_date", nullable = false)
	private Date borrowingDate;
	
	@Column(name = "returning_date")
	private Date returningDate;
	

	public Loans() {
	}

	public Loans(int id, int borrowerId, int bookId, Date borrowingDate, Date returningDate ) {
		this.borrowerId = borrowerId;
		this.bookId = bookId;
		this.borrowingDate = borrowingDate;
		this.returningDate = returningDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBorrowerId() {
		return borrowerId;
	}
	
	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}
	
	public Date getReturningDate() {
		return returningDate;
	}

	public void setReturningDate(Date returningDate) {
		this.returningDate = returningDate;
	}
	
}

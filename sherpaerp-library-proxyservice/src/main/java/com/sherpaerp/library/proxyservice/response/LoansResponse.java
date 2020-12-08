package com.sherpaerp.library.proxyservice.response;

import java.sql.Date;

public class LoansResponse {

	private int id;
	private int borrowerId;
	private int bookId;
	private Date borrowingDate;
	private Date returningDate;

	public LoansResponse() {

	}

	public LoansResponse(int id, int borrowerId, int bookId, Date borrowingDate, Date returningDate) {
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

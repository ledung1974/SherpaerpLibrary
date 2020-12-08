package com.sherpaerp.library.proxyservice.response;

import java.sql.Date;

public class BorrowersResponse {

		private int id;
		private String firstname;
		private String lastname;
		private int borrowingLimit;
		private Date borrowingDate;
		private Date returningDate;

		public BorrowersResponse() {
		}

		public BorrowersResponse(int id, String firstname, String lastname, int borrowingLimit, Date borrowingDate, Date returningDate) {
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.borrowingLimit = borrowingLimit;
			this.borrowingDate = borrowingDate;
			this.returningDate = returningDate;
		}

		public int getId() {
			return id;
		}

		public String getFirstname() {
			return firstname;
		}
		
		public String getLastname() {
			return lastname;
		}

		public Number getBorrowingLimit() {
			return borrowingLimit;
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

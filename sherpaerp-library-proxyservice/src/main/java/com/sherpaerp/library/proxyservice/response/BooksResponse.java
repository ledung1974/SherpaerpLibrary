package com.sherpaerp.library.proxyservice.response;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class BooksResponse {

	private int id;
	private String title;
	private int pageCount;
	
	private Genres[] genres;
	
	private Authors author;
	
	private Date borrowingDate;
	
	private Date returningDate;

	@JsonCreator
	public BooksResponse (@JsonProperty("id") int id, @JsonProperty("title") String title, @JsonProperty("pageCount") int pageCount,@JsonProperty("author") Authors author, @JsonProperty("genres") Genres[] genres, Date borrowingDate, Date returningDate){
		this.id = id;
		this.title = title;
		this.pageCount = pageCount;
		this.author = author;
		this.genres = genres;
		this.borrowingDate = borrowingDate;
		this.returningDate = returningDate;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public int getPageCount () {
		return pageCount;
	}
	
	public Authors getAuthor () {
		return author;
	}
	
	public Genres[] getGenres () {
		return genres;
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

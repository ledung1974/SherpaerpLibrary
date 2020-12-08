package com.sherpaerp.library.booksmicroservice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "tbbooks")
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	//In fact, a book can be written by more than one author, then MannyToMany better
	@ManyToOne // OnetoMany from Authors in this trial 
	@JoinColumn(name = "author_id")
    private Authors author;
	
	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@EqualsAndHashCode.Exclude
    //@Exclude
    private List<Genres> genres = new ArrayList<>();
	
	
	@Column(name = "page_count", nullable = false)
	private int pageCount;

	public Books () {
	
	}
	
	@JsonCreator
	public Books (int id,String title, Authors author, int pageCount) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.pageCount = pageCount;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getTitle () {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public Authors getAuthor () {
		return author;
	}
	
	public void setAuthor (Authors author) {
		this.author = author;
	}
	
	public int getPageCount () {
		return pageCount;
	}
	
	public void setPageCount (int pageCount) {
		this.pageCount = pageCount;
	}
	
	public List<Genres> getGenres () {
		return genres;
	}
	
	public void addGenre (Genres genre) {
		this.genres.add(genre);
	}
}
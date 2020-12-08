package com.sherpaerp.library.booksmicroservice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Column;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;


@Entity
@Table(name = "tbgenres")
public class Genres {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id", updatable = false, nullable = false)
	private int id;

	@Column(name = "genre_name", nullable = false)
	private String genreName;
		
	public Genres () {
	}
	
	@JsonCreator
	public Genres (int id, String genreName) {
		this.genreName = genreName;
	}

	public String getName () {
		return genreName;
	}
	
	public void setName (String genreName) {
		this.genreName = genreName;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tbgenresbooks",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id") 
    )
    private Set<Books> books = new HashSet<Books>();
	// With Set, Hibernate will generate primary keys for the join table.
	
	public void addBooks (Books book) {
		this.books.add(book);	
	}

}
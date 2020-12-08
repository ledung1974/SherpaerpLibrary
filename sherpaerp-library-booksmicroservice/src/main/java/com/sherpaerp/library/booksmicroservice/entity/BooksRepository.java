package com.sherpaerp.library.booksmicroservice.entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
	
	@Query(value="SELECT * FROM tbbooks JOIN tbauthors ON tbbooks.author_id = tbauthors.author_id ORDER BY tbbooks.book_id", nativeQuery = true)
	List<Books> findAll();
	
	List<Books> findById(int id);
	
	List<Books> findByTitle(String title);
	
	@Query(value="SELECT * FROM tbbooks JOIN tbauthors WHERE tbbooks.title = ?1 AND tbauthors.firstname = ?2 AND tbauthors.lastname = ?3", nativeQuery = true)
	List<Books> findByTitleAndAuthor(String title, String firstname, String lastname);
	
}
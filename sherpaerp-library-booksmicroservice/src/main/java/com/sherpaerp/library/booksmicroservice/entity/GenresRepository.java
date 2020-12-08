package com.sherpaerp.library.booksmicroservice.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Long> {
	List<Genres> findByGenreName(String genreName);
}
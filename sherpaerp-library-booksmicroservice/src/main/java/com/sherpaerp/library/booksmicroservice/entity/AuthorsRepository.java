package com.sherpaerp.library.booksmicroservice.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Long> {
	List<Authors> findByFirstnameAndLastname(String firstname, String lastname);
}
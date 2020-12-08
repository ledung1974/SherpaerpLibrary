package com.sherpaerp.library.booksmicroservice.controller;

import com.sherpaerp.library.booksmicroservice.entity.Books;
import com.sherpaerp.library.booksmicroservice.entity.BooksRepository;
import com.sherpaerp.library.booksmicroservice.entity.Genres;
import com.sherpaerp.library.booksmicroservice.entity.GenresRepository;
import com.sherpaerp.library.booksmicroservice.entity.Authors;
import com.sherpaerp.library.booksmicroservice.entity.AuthorsRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "/books")
public class BooksController {

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private AuthorsRepository authorsRepository;

	@Autowired
	private GenresRepository genresRepository;

	//-----------------------------------------------------
	@GetMapping(path = "/list")
	@ResponseBody
	public List<Books> getAllBooks() {
		List<Books> response = booksRepository.findAll();
		return response;
	}
	
	//-----------------------------------------------------
	@GetMapping(path = "/find")
	@ResponseBody
	public List<Books> getABook(@RequestParam(required = false) String id,
			@RequestParam(required = false) String title, @RequestParam(required = false) List<String> author) {
		List<Books> response = new ArrayList<>();
		if (id != null) {
			try {
				int intId = Integer.parseInt(id);
				response = booksRepository.findById(intId);
			} catch (NumberFormatException e) {
				System.out.println("Exception thrown of the parameter id :" + e);
			}
		} else {
			if (title != null) {
				if (author == null) {
					response = booksRepository.findByTitle(title);
				}else {
					if (author.size()==2) {
						String firstname = author.get(0);
						String lastname = author.get(1);
						response = booksRepository.findByTitleAndAuthor(title, firstname, lastname);
					}
				}
			}
		}
		return response;
	}

	//-----------------------------------------------------
	//@PostMapping(path = "/post")
	@GetMapping(path = "/add")
	public @ResponseBody String addNewBook(@RequestParam String title, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam List<String> genres, @RequestParam int pageCount) {
		String result = "";
		// Check book's title and book's author have already been in Database
		List<Books> response = booksRepository.findByTitleAndAuthor(title, firstname, lastname);
		if (response.isEmpty()) {
			Books newBook = new Books();
			newBook.setTitle(title);
			newBook.setPageCount(pageCount);

			// Add Book's genres
			for (String genreName : genres) {
				List<Genres> ge = genresRepository.findByGenreName(genreName);
				Genres genre = new Genres();
				if (ge.isEmpty()) {
					genre.setName(genreName);
				} else {
					genre = ge.get(0);
				}
				newBook.addGenre(genre);
				genre.addBooks(newBook);
			}

			Authors author = new Authors();
			List<Authors> au = authorsRepository.findByFirstnameAndLastname(firstname, lastname);
			// Check the author have already been in Database
			if (au.isEmpty()) {
				author.setFirstname(firstname);
				author.setLastname(lastname);
				response.add(newBook);
				author.setBooks(response);
				newBook.setAuthor(author);
				authorsRepository.saveAndFlush(author);
				result = "A new book by a new author has just been added to the database!";
			} else {
				author = au.get(0);
				newBook.setAuthor(author);
				booksRepository.saveAndFlush(newBook);
				result = "A new book by the author [" + firstname + " " + lastname
						+ "] who has already been in the database has just been added to the database!";
			}

		} else {
			result = "The book with title: [" + title + "] by author [" + firstname + " " + lastname
					+ "] has already been in the database. So, the addition is canceled.";
		}
		return result;
	}

}
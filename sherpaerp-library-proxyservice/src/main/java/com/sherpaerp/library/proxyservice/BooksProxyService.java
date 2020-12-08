package com.sherpaerp.library.proxyservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="sherpaerp-library-booksmicroservice", url="localhost:8200")
public interface BooksProxyService {
	@GetMapping("/books/find?id={bookId}")
	public String retrieveFromBooksServiceById(@PathVariable int bookId); 
	
	@GetMapping("/books/find?title={title}")
	public String retrieveFromBooksServiceByTitle(@PathVariable String title);
	
	@GetMapping("/books/find?title={title}&author={author}")
	public String retrieveFromBooksServiceByTitleAndAuthor(@PathVariable String title,@PathVariable List<String> author);
	
	@GetMapping("/books/list")
	public String retrieveAllBooksFromBooksService();
	
	@GetMapping("/books/add?title={title}&firstname={firstname}&lastname={lastname}&genres={genres}&pageCount={pageCount}")
	public String addABookToBooksService(@PathVariable String title, @PathVariable String firstname,
			@PathVariable String lastname, @PathVariable List<String> genres, @PathVariable int pageCount);

}

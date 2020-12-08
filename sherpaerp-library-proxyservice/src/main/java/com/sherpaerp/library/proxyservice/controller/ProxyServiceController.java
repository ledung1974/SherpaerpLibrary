package com.sherpaerp.library.proxyservice.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sherpaerp.library.proxyservice.BooksProxyService;
import com.sherpaerp.library.proxyservice.BorrowersProxyService;
import com.sherpaerp.library.proxyservice.LoansProxyService;
import com.sherpaerp.library.proxyservice.response.BooksResponse;
import com.sherpaerp.library.proxyservice.response.BorrowersResponse;
import com.sherpaerp.library.proxyservice.response.LoansResponse;

@RestController
public class ProxyServiceController {
	@Autowired
	private BooksProxyService booksProxy;

	@Autowired
	private LoansProxyService loansProxy;

	@Autowired
	private BorrowersProxyService borrowersProxy;
    ///----------------------------------------------------------------------------------------//
	@GetMapping("/list/borrowers/bookid/{bookId}")
	public @ResponseBody List<BorrowersResponse> listBorrowersOfABook(@PathVariable String bookId) {
		List<BorrowersResponse> result = new ArrayList<>();
		String response = "";
		if (bookId != null) {
			response = this.loansProxy.retrieveFromLoansServiceByBookId(bookId);
			if (response != null) {
				final ObjectMapper objectMapper1 = new ObjectMapper();
				try {
					List<LoansResponse> loans = objectMapper1.readValue(response,
							new TypeReference<List<LoansResponse>>() {
							});
					for (LoansResponse loan : loans) {
						int borrowerId = loan.getBorrowerId();
						Date borrowingDate = loan.getBorrowingDate();
						Date returningDate = loan.getReturningDate();
						response = this.borrowersProxy.retrieveFromBorrowersServiceById(borrowerId);
						final ObjectMapper objectMapper2 = new ObjectMapper();
						List<BorrowersResponse> borrower = objectMapper2.readValue(response,
								new TypeReference<List<BorrowersResponse>>() {
								});
						borrower.get(0).setBorrowingDate(borrowingDate);
						borrower.get(0).setReturningDate(returningDate);
						result.add(borrower.get(0));
					}

				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	///----------------------------------------------------------------------------------------//
	@GetMapping("/list/books/borrowerid/{borrowerId}")
	public @ResponseBody List<BooksResponse> listBooksOfABorrower(@PathVariable String borrowerId) {
		List<BooksResponse> result = new ArrayList<>();
		String response = "";
		if (borrowerId != null) {
			response = this.loansProxy.retrieveFromLoansServiceByBorrowerId(borrowerId);
			if (response != null) {
				try {
					final ObjectMapper objectMapper1 = new ObjectMapper();
					List<LoansResponse> loans = objectMapper1.readValue(response,
							new TypeReference<List<LoansResponse>>() {
							});
					for (LoansResponse loan : loans) {
						int bookId = loan.getBookId();
						Date borrowingDate = loan.getBorrowingDate();
						Date returningDate = loan.getReturningDate();
						response = this.booksProxy.retrieveFromBooksServiceById(bookId);
						Gson gson2 = new Gson();
						BooksResponse[] book = gson2.fromJson(response, BooksResponse[].class);
						book[0].setBorrowingDate(borrowingDate);
						book[0].setReturningDate(returningDate);
						result.add(book[0]);
					}
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

			}
		}
		return result;
	}
	///----------------------------------------------------------------------------------------//
	@GetMapping("/books/list")
	public String listAllBooks() {
		return this.booksProxy.retrieveAllBooksFromBooksService();
	}
	///----------------------------------------------------------------------------------------//
	@GetMapping("/books/getid/{bookId}")
	public String GetABookById(@PathVariable int bookId) {
		String result = "";
		if (bookId>0) {
			result = this.booksProxy.retrieveFromBooksServiceById(bookId);
		}
		return result;
	}
	///----------------------------------------------------------------------------------------//
	@GetMapping("/books/find")
	public String FindBookByTitleAndAuthor(@RequestParam (required = false) String title, 
											@RequestParam (required = false) List<String> author) {
		String result = "";
		if (title != null) {
			if (author != null) {
				result = this.booksProxy.retrieveFromBooksServiceByTitleAndAuthor(title, author);
			}else {
				result = this.booksProxy.retrieveFromBooksServiceByTitle(title);
			}
		}
		return result;
	}
	///----------------------------------------------------------------------------------------//
	@GetMapping("/books/add")
	public String AddANewBook(	@RequestParam (required = false) String title, 
								@RequestParam (required = false) String firstname, 
								@RequestParam (required = false) String lastname,
								@RequestParam (required = false) List<String> genres, 
								@RequestParam (required = false) int pageCount) {
		String result = "";
		if (title != null && firstname != null && lastname != null && genres != null && pageCount !=0) {
			result = this.booksProxy.addABookToBooksService(title, firstname, lastname, genres, pageCount);
		}
		return result;
	}
	
	///----------------------------------------------------------------------------------------//
	@GetMapping("/borrowers/list")
	public String listAllBorrowers() {
			return this.borrowersProxy.retrieveAllBorrowersFromBorrowersService();
	}
		
	///----------------------------------------------------------------------------------------//
	@GetMapping("/borrowers/getid/{borrowerId}")
	public String GetABorrowerById(@PathVariable int borrowerId) {
			String result = "";
			if (borrowerId>0) {
				result = this.borrowersProxy.retrieveFromBorrowersServiceById(borrowerId);
			}
			return result;
	}
	
	///----------------------------------------------------------------------------------------//
	@GetMapping("/borrowers/find")
	public String FindBorrowerByFullName(	@RequestParam (required = false) String firstname, 
											@RequestParam (required = false) String lastname) {
			String result = "";
			if (firstname != null && lastname != null) { 	
				result = this.borrowersProxy.retrieveBorrowerFromBorrowersServiceByFullname(firstname, lastname);
			}
			return result;
	}
	
	///----------------------------------------------------------------------------------------//
	@GetMapping("/borrowers/add")
	public String AddNewBorrower(	@RequestParam (required = false) String firstname, 
										@RequestParam (required = false) String lastname,
										@RequestParam (required = false) int borrowingLimit) {
			String result = "";
			if (firstname != null && lastname != null && borrowingLimit > 0) {
				 result = this.borrowersProxy.addABorrowerToBorrowersService(firstname, lastname, borrowingLimit);
			}
			return result;
	}
	
	///----------------------------------------------------------------------------------------//
	@GetMapping("/loans/list")
	public String listAllLoans() {
		return this.loansProxy.retrieveAllLoansFromLoansService();
	}
	
	///----------------------------------------------------------------------------------------//
	@GetMapping("/loans/list/bookid/{bookId}")
	public String listAllLoansByBookId(@PathVariable String bookId) {
		String result = "";
		if (bookId != null) {
			 result = this.loansProxy.retrieveFromLoansServiceByBookId(bookId);
		}
		return result;
	}
	///----------------------------------------------------------------------------------------//
		@GetMapping("/loans/list/borrowerid/{borrowerId}")
		public String listAllLoansByBorrowerId(@PathVariable String borrowerId) {
			String result = "";
			if (borrowerId != null) {
				 result = this.loansProxy.retrieveFromLoansServiceByBorrowerId(borrowerId);
			}
			return result;
		}
	
	///----------------------------------------------------------------------------------------//
	@GetMapping("/loans/add")
			public String AdđdNewBorrower(	@RequestParam int borrowerId, 
											@RequestParam int bookId,
											@RequestParam String borrowingDate, 
											@RequestParam String returningDate) {
				/// loans/add?borrowerId=1&bookId=1&borrowingDate=2020-10-20&returningDate=2020-11-24 
				String result = "";
				String findBorrower = this.borrowersProxy.retrieveFromBorrowersServiceById(borrowerId);
				String findBook = this.booksProxy.retrieveFromBooksServiceById(bookId);
				if (findBorrower.length()>2 && findBook.length()>2) {
					result = this.loansProxy.addANewLoanToLoansService(borrowerId, bookId, borrowingDate, returningDate);
				}else {
					result = "BorrowerId or BookId doesn't exist in the database.";
				}
				return result;
			}

	
}

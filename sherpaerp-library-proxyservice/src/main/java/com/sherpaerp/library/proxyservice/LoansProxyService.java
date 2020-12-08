package com.sherpaerp.library.proxyservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="sherpaerp-library-loanofborrower", url="localhost:8201")
public interface LoansProxyService {
	@GetMapping("/loans/find?bookId={bookId}")
	public String retrieveFromLoansServiceByBookId(@PathVariable String bookId);
	
	@GetMapping("/loans/find?borrowerId={borrowerId}")
	public String retrieveFromLoansServiceByBorrowerId(@PathVariable String borrowerId);
	
	@GetMapping("/loans/list")
	public String retrieveAllLoansFromLoansService(); 
	
	@GetMapping("/loans/add?borrowerId={borrowerId}&bookId={bookId}&borrowingDate={borrowingDate}&returningDate={returningDate}")
	public String addANewLoanToLoansService(@PathVariable int borrowerId, @PathVariable int bookId, @PathVariable String borrowingDate,	@PathVariable String returningDate);
}

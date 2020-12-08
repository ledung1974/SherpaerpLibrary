package com.sherpaerp.library.loanofborrower.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sherpaerp.library.loanofborrower.entity.Loans;
import com.sherpaerp.library.loanofborrower.entity.LoansRepository;

@RestController
@RequestMapping(path = "/loans")
public class LoansController {
	@Autowired
	private LoansRepository loansRepository;

	// @PostMapping(path = "/post")
	@GetMapping(path = "/add")
	public @ResponseBody String addNewLoan(@RequestParam int borrowerId, @RequestParam int bookId,
			@RequestParam String borrowingDate, @RequestParam String returningDate) throws Exception {
		/// loans/add?borrowerId=1&bookId=1&borrowingDate=2020-10-20&returningDate=2020-11-24

		Loans newLoan = new Loans();
		newLoan.setBorrowerId(borrowerId);
		newLoan.setBookId(bookId);
		Date d = Date.valueOf(borrowingDate);
		List<Loans> response = loansRepository.findByBookIdAndBorrowerIdAndBorrowingDate(bookId, borrowerId, d);
		if (response.isEmpty()) {
			newLoan.setBorrowingDate(d);
			d = Date.valueOf(returningDate);
			newLoan.setReturningDate(d);
			loansRepository.saveAndFlush(newLoan);
			return "New loan has just been added to the database.";
		} else {
			return "The loan has already been in the database. So, the addition is canceled.";
		}
	}
	//-------------------------------------------------------------------
	@GetMapping(path = "/list")
	public @ResponseBody List<Loans> getAllLoans() {
		return loansRepository.findAll();
	}
	
	//-------------------------------------------------------------------
	@GetMapping(path = "/find")
	public @ResponseBody List<Loans> getALoan(@RequestParam(required = false) String bookId,
			@RequestParam(required = false) String borrowerId) {
		List<Loans> response = new ArrayList<>();
		if (bookId != null && borrowerId == null) {
			try {
				int intId = Integer.parseInt(bookId);
				response = loansRepository.findByBookId(intId);
			} catch (NumberFormatException e) {
				System.out.println("Exception thrown of the parameter id :" + e);
			}
		} else {
			if (bookId == null && borrowerId != null) {
				try {
					int intId = Integer.parseInt(borrowerId);
					response = loansRepository.findByBorrowerId(intId);
				} catch (NumberFormatException e) {
					System.out.println("Exception thrown of the parameter id :" + e);
				}
			}
		}
		return response;
	}

}
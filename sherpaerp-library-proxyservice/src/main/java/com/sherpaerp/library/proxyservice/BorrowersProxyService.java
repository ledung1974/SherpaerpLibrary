package com.sherpaerp.library.proxyservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="sherpaerp-library-borrowersmicroservice", url="localhost:8202")
public interface BorrowersProxyService {
	@GetMapping("/borrowers/find?id={borrowerId}")
	public String retrieveFromBorrowersServiceById(@PathVariable int borrowerId);
	
	@GetMapping("/borrowers/list")
	public String retrieveAllBorrowersFromBorrowersService();
	
	@GetMapping("/borrowers/find?firstname={firstname}&lastname={lastname}")
	public String retrieveBorrowerFromBorrowersServiceByFullname(@PathVariable String firstname,
			@PathVariable String lastname);
	
	@GetMapping("/borrowers/add?firstname={firstname}&lastname={lastname}&borrowingLimit={borrowingLimit}")
	public String addABorrowerToBorrowersService(@PathVariable String firstname,@PathVariable String lastname, @PathVariable int borrowingLimit);
	
}

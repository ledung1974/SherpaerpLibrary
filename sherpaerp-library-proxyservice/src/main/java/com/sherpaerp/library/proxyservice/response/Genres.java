package com.sherpaerp.library.proxyservice.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Genres {

	private String name;
		
	@JsonCreator
	public Genres (@JsonProperty("name") String name) {
			this.name = name;
	}
	 
	public String getName () {
			return name;
	}
}
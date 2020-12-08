package com.sherpaerp.library.proxyservice.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Authors {

		private String firstname;
		private String lastname;
		
		@JsonCreator
		public Authors (@JsonProperty("firstname") String firstname, @JsonProperty("lastname") String lastname) {
			this.firstname = firstname;
			this.lastname = lastname;
		}
		
		public String getFirstname () {
			return firstname;
		}
		
		public String getLastname () {
			return lastname;
		}
	}

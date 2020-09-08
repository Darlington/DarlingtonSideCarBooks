package com.sidecardarbook.security;

public enum SideCarBookSecurity {
	
	SECRET("SecretKeyToGenJWTs"),
    EXPIRATION_TIME(864_000_000), // 10 days
    TOKEN_PREFIX("Bearer:"),
    HEADER_STRING("Authorization"),
    API_URL("/api/**"),
    HOME_URL("/"),
    ADD_URL("/addBook"),
    UPDATE_URL("/updateBook"),
    DELETE_URL("/deleteBook"),
    SIGN_UP_URL("/users/sign-up");
	
	private String stringValue;
	private long longvalue;
	
	private SideCarBookSecurity(String stringValue) {
		this.stringValue = stringValue;
	}
	
	private SideCarBookSecurity(long longvalue) {
		this.longvalue = longvalue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public long getLongvalue() {
		return longvalue;
	}
	
	
	
	

}

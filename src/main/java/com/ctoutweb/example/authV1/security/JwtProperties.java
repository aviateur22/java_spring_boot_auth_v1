package com.ctoutweb.example.authV1.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix =  "jwt")
public class JwtProperties {
	
	/**
	 * Secret key
	 */
	private String secretKey;
	
	/**
	 * Issuer
	 */
	private String issuer;

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
}

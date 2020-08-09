package br.com.sura.model;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {

	ROLE_ADMIN, ROLE_USER;

	public String getAuthority() {
		return name();
	}

}
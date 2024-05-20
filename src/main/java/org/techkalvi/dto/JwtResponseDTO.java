package org.techkalvi.dto;

public class JwtResponseDTO {
    private String accessToken;

	public JwtResponseDTO(String accessToken) {
		// TODO Auto-generated constructor stub
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
  
}

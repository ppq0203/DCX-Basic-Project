package project.shop.dto;

import lombok.Data;

@Data
public class UserDto {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userAddress;
	private String Access_rights;
}

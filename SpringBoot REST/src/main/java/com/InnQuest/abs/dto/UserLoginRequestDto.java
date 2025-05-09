package com.intellect.abs.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserLoginRequestDto {
	private String password;
	private String email;	

}

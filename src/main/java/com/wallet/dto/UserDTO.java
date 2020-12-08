package com.wallet.dto;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserDTO {
  @Email(message = "email invalido")
  private String email;
  private String name;
  private String password;
}

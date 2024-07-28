package com.springboot.dto.request;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO extends AbstractRequestDTO<UserRequestDTO> {

    @Size(min = 4, message = "USERNAME_INVALID")
    private String fullName;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    private String userName;
    private int status;
    //    private Long role_id;
    private List<String> roles = new ArrayList<>();
}

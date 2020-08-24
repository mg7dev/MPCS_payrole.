package com.mpcs.Common;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
}

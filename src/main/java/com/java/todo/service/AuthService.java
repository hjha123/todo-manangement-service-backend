package com.java.todo.service;

import com.java.todo.dto.LoginDto;
import com.java.todo.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}

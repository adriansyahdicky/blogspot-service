package com.blogspot.blogspotservices.controller;

import com.blogspot.blogspotservices.dto.RequestCreateUser;
import com.blogspot.blogspotservices.dto.RequestLogin;
import com.blogspot.blogspotservices.service.UsersServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UsersServices userCrudService;

    @PostMapping(value = "/api/auth/create")
    public ResponseEntity<Object> create(@RequestBody RequestCreateUser requestCreateUser){
        return ResponseEntity.ok(userCrudService.save(requestCreateUser));
    }

    @PostMapping(value = "/api/auth/login")
    public ResponseEntity<Object> login(@RequestBody RequestLogin requestLogin) throws Exception {
        return ResponseEntity.ok(userCrudService.login(requestLogin));
    }

}

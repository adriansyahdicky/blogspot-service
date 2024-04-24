package com.blogspot.blogspotservices.service;

import com.blogspot.blogspotservices.dto.RequestCreateUser;
import com.blogspot.blogspotservices.dto.RequestLogin;
import com.blogspot.blogspotservices.dto.ResponseApplication;
import com.blogspot.blogspotservices.dto.ResponseLogin;
import com.blogspot.blogspotservices.model.Users;
import com.blogspot.blogspotservices.repository.UsersRepository;
import com.blogspot.blogspotservices.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServices implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> dataUser = usersRepository.findByUsername(username);

        if(dataUser.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User
                (dataUser.get().getUsername(), dataUser.get().getPassword(),
                        new ArrayList<>());
    }

    public ResponseApplication<Users> save(RequestCreateUser requestCreateUser){
        try {
            return ResponseApplication.result(usersRepository.save(Users
                    .builder()
                    .username(requestCreateUser.getUsername())
                    .password(passwordEncoder.encode(requestCreateUser.getPassword()))
                    .build()));
        }catch (Exception ex){
            log.error("Error save {} ", ex.getMessage());
            throw ex;
        }
    }

    public ResponseApplication<ResponseLogin> login(RequestLogin requestLogin) throws Exception {
        try {
            final UserDetails userDetails = loadUserByUsername(requestLogin.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getUsername(),
                            requestLogin.getPassword()
                    )
            );
            return ResponseApplication.result(ResponseLogin.builder()
                    .token(jwtTokenUtil.generateToken(userDetails))
                    .build());
        }catch (UsernameNotFoundException e){
            log.error("User Not Found {} ", e.getMessage());
            throw e;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

}

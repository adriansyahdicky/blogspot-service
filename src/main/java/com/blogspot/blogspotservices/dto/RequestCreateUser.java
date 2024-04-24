package com.blogspot.blogspotservices.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestCreateUser {
    private String username;
    private String password;
}

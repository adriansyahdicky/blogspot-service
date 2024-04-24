package com.blogspot.blogspotservices.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBlogs {
    private String id;
    private String title;
    private String body;
    private String userAuthor;
}

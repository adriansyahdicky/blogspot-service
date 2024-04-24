package com.blogspot.blogspotservices.controller;

import com.blogspot.blogspotservices.dto.RequestCreateUpdateBlog;
import com.blogspot.blogspotservices.service.BlogsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogsServices blogsServices;

    @GetMapping("/api/blogs")
    public ResponseEntity<Object> getAll( @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(value = "sort_field", required = false) String sortField,
                                          @RequestParam(value = "sort_direction", defaultValue = "asc") String sortDirection){
        return ResponseEntity.ok(blogsServices.getBlogs(page,
                size,
                sortField,
                sortDirection));
    }

    @GetMapping("/api/blogs/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(blogsServices.getBlogsById(id));
    }

    @PostMapping(value = "/api/blogs")
    public ResponseEntity<Object> create(@RequestBody RequestCreateUpdateBlog requestCreateUpdateBlog){
        return ResponseEntity.ok(blogsServices.save(requestCreateUpdateBlog));
    }

    @DeleteMapping(value = "/api/blogs")
    public ResponseEntity<Object> delete(@RequestBody Map<String, String> request){
        return ResponseEntity.ok(blogsServices.delete(request));
    }

    @PutMapping(value = "/api/blogs/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") String id,
            @RequestBody RequestCreateUpdateBlog requestCreateUpdateBlog){
        return ResponseEntity.ok(blogsServices.update(id, requestCreateUpdateBlog));
    }
}

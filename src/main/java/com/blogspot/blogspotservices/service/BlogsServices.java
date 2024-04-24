package com.blogspot.blogspotservices.service;

import com.blogspot.blogspotservices.dto.PagingSortingResp;
import com.blogspot.blogspotservices.dto.RequestCreateUpdateBlog;
import com.blogspot.blogspotservices.dto.ResponseApplication;
import com.blogspot.blogspotservices.dto.ResponseBlogs;
import com.blogspot.blogspotservices.exception.BusinessException;
import com.blogspot.blogspotservices.model.Blog;
import com.blogspot.blogspotservices.repository.BlogRepository;
import com.blogspot.blogspotservices.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import static com.blogspot.blogspotservices.constant.BlogConstant.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogsServices {

    private final BlogRepository blogRepository;

    public PagingSortingResp getBlogs(int page, int size, String sort, String sortDescription){
        Sort.Direction direction = sortDescription.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        if (sort == null)
            sort = TITLE;

        String field = null;
        if(sort.equalsIgnoreCase(TITLE)){
            field = TITLE;
        }else if (sort.equalsIgnoreCase(BODY)){
            field = BODY;
        }

        assert field != null;
        PageRequest pageable = PageRequest.of(page, size,
                Sort.by(new Sort.Order(direction, field).ignoreCase()));

        Page<Blog> pageResult=blogRepository.findAll(pageable);
        return PagingSortingResp.responseBuilder()
                .records(pageResult.getContent())
                .currentPage(pageResult.getNumber())
                .currentSize(pageResult.getSize())
                .totalPage(pageResult.getTotalPages())
                .totalSize(pageResult.getTotalElements())
                .build();
    }

    public ResponseApplication<ResponseBlogs> getBlogsById(String id){
        ResponseBlogs responseBlogs = blogRepository.findById(id)
               .map(this::convertResponse)
                .orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));
        return ResponseApplication.result(responseBlogs);
    }

    public ResponseApplication<ResponseBlogs> save(RequestCreateUpdateBlog createBlog){
        return ResponseApplication.result(
            convertResponse(blogRepository.save(Blog.builder()
                            .title(createBlog.getTitle())
                            .body(createBlog.getBody())
                            .userAuthor(UserUtil.getUsername())
                    .build()))
        );
    }

    public ResponseApplication<ResponseBlogs> update(String id, RequestCreateUpdateBlog createBlog){
        return blogRepository.findById(id)
                .map(blog -> {
                    blog.setBody(createBlog.getBody());
                    blog.setTitle(createBlog.getTitle());
                    blog.setUserAuthor(UserUtil.getUsername());
                    blogRepository.save(blog);
                    return ResponseApplication.result(convertResponse(blog));
                }).orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));
    }

    public ResponseApplication<Boolean> delete(Map<String, String> request){
        String id = request.get("id");
        return blogRepository.findById(id)
                .map(blog -> {
                    blogRepository.delete(blog);
                    return ResponseApplication.result(true);
                }).orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));
    }

    private ResponseBlogs convertResponse(Blog blog){
        return ResponseBlogs.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .body(blog.getBody())
                .userAuthor(blog.getUserAuthor())
                .build();
    }

}

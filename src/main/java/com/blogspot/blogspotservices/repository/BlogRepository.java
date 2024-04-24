package com.blogspot.blogspotservices.repository;

import com.blogspot.blogspotservices.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

}

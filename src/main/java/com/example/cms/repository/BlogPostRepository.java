package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.Entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{

}

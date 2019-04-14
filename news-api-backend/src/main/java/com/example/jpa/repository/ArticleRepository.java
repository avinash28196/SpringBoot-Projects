package com.example.jpa.repository;

import com.example.jpa.model.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	Page<Article> findByCategory(String category,Pageable pageable);

}
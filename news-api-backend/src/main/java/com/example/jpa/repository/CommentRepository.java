package com.example.jpa.repository;

import com.example.jpa.model.Article;
import com.example.jpa.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
//@Repository
//public interface CommentRepository extends JpaRepository<Comment, Long> {
//    Page<Comment> findByPostId(Long postId, Pageable pageable);
//    Optional<Comment> findByIdAndPostId(Long id, Long postId);
//}

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findById(Long articleId, Pageable pageable);
    Optional<Comment> findByIdAndArticle_Id(Long id, Long articleId);
//    Optional<Comment> findByIdArticle_Id(Long id, Long articleId);
	Page<Comment> findByArticle_Id(Long articleId, Pageable pageable);
    
}

package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Article;
import com.example.jpa.model.Comment;
import com.example.jpa.repository.ArticleRepository;
import com.example.jpa.repository.CommentRepository;
import com.example.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @GetMapping("/api/comment/{commentsId}")
    public Optional<Comment> getCommentsByCommentId(@PathVariable (value = "commentId") Long commentId){
    	return commentRepository.findById(commentId);
    }
    
    @GetMapping("/api/article/{articleId}/comments")
    public Page<Comment> getAllCommentsByArticleId(@PathVariable (value = "articleId") Long articleId,Pageable pageable) {
      return commentRepository.findByArticle_Id(articleId,pageable);
    }

	  @PostMapping("/article/{articleId}/comments")
	  public Comment createComment(@PathVariable (value = "articleId") Long articleId, @Valid @RequestBody Comment comment) {
	      return articleRepository.findById(articleId).map(article -> {
	          comment.setArticle(article); 
	          return commentRepository.save(comment);
	      }).orElseThrow(() -> new ResourceNotFoundException("ArticleId " + articleId + " not found"));
	  }

//
//    @PutMapping("/posts/{postId}/comments/{commentId}")
//    public Comment updateComment(@PathVariable (value = "postId") Long postId,
//                                 @PathVariable (value = "commentId") Long commentId,
//                                 @Valid @RequestBody Comment commentRequest) {
//        if(!postRepository.existsById(postId)) {
//            throw new ResourceNotFoundException("PostId " + postId + " not found");
//        }
//
//        return commentRepository.findById(commentId).map(comment -> {
//            comment.setText(commentRequest.getText());
//            return commentRepository.save(comment);
//        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
//    }

	
	    @PutMapping("/article/{articleId}/comments/{commentId}")
	    public Comment updateComment(@PathVariable (value = "articleId") Long articleId,
	                                 @PathVariable (value = "commentId") Long commentId,
	                                 @Valid @RequestBody Comment commentRequest) {
	        if(!articleRepository.existsById(articleId)) {
	            throw new ResourceNotFoundException("articleId " + articleId + " not found");
	        }
	
	        return commentRepository.findById(commentId).map(comment -> {
	            comment.setText(commentRequest.getText());
	            return commentRepository.save(comment);
	        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
	    }

	    @DeleteMapping("/article/{articleId}/comments/{commentId}")
	    public ResponseEntity<?> deleteComment(@PathVariable (value = "articleId") Long articleId,
	                              @PathVariable (value = "commentId") Long commentId) {
	        return commentRepository.findByIdAndArticle_Id(commentId, articleId).map(comment -> {
	            commentRepository.delete(comment);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and articleId " + articleId));
	    }
	  
}

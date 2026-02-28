package com.jin.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addComment(String comment, Long id, String username) {
        Comment comment1 = new Comment();
        comment1.setUsername(username);
        comment1.setContent(comment);
        comment1.setParentId(id);
        commentRepository.save(comment1);
    }

    public Page<Comment> findByParentId(Integer id, Pageable pageable) {
        return commentRepository.findByParentId(id, pageable);
    }

}

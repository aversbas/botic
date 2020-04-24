package com.orto.botic.service;

import com.orto.botic.entities.Comment;
import com.orto.botic.entities.Product;
import com.orto.botic.entities.User;
import com.orto.botic.repositories.CommentRepository;
import com.orto.botic.repositories.ProductRepository;
import com.orto.botic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

//    @Autowired
//    public CommentService(CommentRepository commentRepository) {
//        this.commentRepository = commentRepository;
//    }

    public Comment saveComment(Comment comment) {
//        if (comment == null) return;
       return commentRepository.save(comment);
    }
}

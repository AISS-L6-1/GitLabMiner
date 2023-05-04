package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Test
    @DisplayName("Test de getCommentsFromId")
    void getCommentsFromId() {
        List<Comment> commentList=commentService.getCommentsFromId(20699,7717,5,50);
        assertFalse(commentList.isEmpty(), "The list of comments is empty");
        System.out.println(commentList);
    }

}
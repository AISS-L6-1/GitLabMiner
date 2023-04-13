package aiss.GitLabMiner.repository;

import aiss.GitLabMiner.model.Comment;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.User;
import aiss.GitLabMiner.transformer.IssueFinal;
import aiss.GitLabMiner.transformer.ProjectFinal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class IssueRepository {
    List<IssueFinal> issueList = new ArrayList<>();

    public IssueRepository() {
        User user1 = new User(1, "Pablo", "Caballero", "url", "url", null);
        User user2 = new User(2, "Alfonso", "Alonso", "url", "url", null);
        Comment comment1 = new Comment("1", "comentario", null, null, user1);
        Comment comment2 = new Comment("2", "comentario", null, null, user2);
        List<String> labelList = List.of("label 1", "label 2");
        IssueFinal issue1 = new IssueFinal("1", "1", "titulo", "patata", "estado",
                null, null, null, labelList, 6, 9,
                user1, user2,
                List.of(comment1, comment2));
        IssueFinal issue2 = new IssueFinal("1", "2", "titulo", "patata", "estado",
                null, null, null, labelList, 6, 9,
                user1, user2,
                List.of(comment1, comment2));
        IssueFinal issue3 = new IssueFinal("2", "1", "titulo", "patata", "estado",
                null, null, null, labelList, 6, 9,
                user1, user2,
                List.of(comment1, comment2));
        IssueFinal issue4 = new IssueFinal("2", "2", "titulo", "patata", "estado",
                null, null, null, labelList, 6, 9,
                user1, user2,
                List.of(comment1, comment2));
        issueList.add(issue1);
        issueList.add(issue2);
        issueList.add(issue3);
        issueList.add(issue4);

    }

    public List<IssueFinal> findAll() {
        return issueList;
    }

    public IssueFinal findOne(String id) {
        return issueList.stream().filter(issue -> issue.getId().equals(id)).findFirst().orElse(null);
    }

    public IssueFinal create(IssueFinal issue){
        IssueFinal newIssue = new IssueFinal(
                issue.getId().toString(),
                UUID.randomUUID().toString(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getState(),
                issue.getCreatedAt(),
                issue.getUpdatedAt(),
                issue.getClosedAt(),
                issue.getLabels(),
                issue.getUpvotes(),
                issue.getDownvotes(),
                issue.getAuthor(),
                issue.getAsignee(),
                issue.getCommentList()
        );
        issueList.add(newIssue);
        return newIssue;
    }

    public void update(IssueFinal updatedIssue, String id){
        IssueFinal existing = findOne(id);
        int i = issueList.indexOf(existing);
        updatedIssue.setId(existing.getId());
        issueList.set(i, updatedIssue);
    }

    public void delete(String id){issueList.removeIf(commit -> commit.getId().equals(id));}
}

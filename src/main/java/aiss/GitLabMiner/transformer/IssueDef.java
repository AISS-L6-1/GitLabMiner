package aiss.GitLabMiner.transformer;

import aiss.GitLabMiner.model.Comment;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.User;
import aiss.GitLabMiner.service.CommentService;

import java.util.List;

public class IssueDef {

    private Integer id;
    private Integer ref_id;
    private String title;
    private String description;
    private String state;
    private String createdAt;
    private String updatedAt;
    private Object closedAt;
    private List<String> labels;
    private Integer upvotes;
    private Integer downvotes;
    private User author;
    private User asignee;

    private List<Comment> listComments;


    //metodo que usaremos para unir a nuestro Issue la lista de Comments que obtenemos con getCommentsFromId. Luego se hará una lista de IssueDef que será una propiedad de ProjectDef
    public static IssueDef ofRaw(Issue issueRaw, CommentService commentService, Integer sinceDays, Integer maxPages){
        return new IssueDef(issueRaw.getId(), issueRaw.getRef_id(), issueRaw.getTitle(), issueRaw.getDescription(),
                issueRaw.getState(), issueRaw.getCreatedAt(), issueRaw.getUpdatedAt(), issueRaw.getClosedAt(), issueRaw.getLabels(),
                issueRaw.getUpvotes(), issueRaw.getDownvotes(), issueRaw.getAuthor(), issueRaw.getAsignee(), commentService.getCommentsFromId(issueRaw.getId(), issueRaw.getRef_id(), sinceDays, maxPages));
    }

    public IssueDef(Integer id, Integer ref_id, String title, String description, String state, String createdAt, String updatedAt, Object closedAt, List<String> labels, Integer upvotes, Integer downvotes, User author, User asignee, List<Comment> listComments) {
        this.id = id;
        this.ref_id = ref_id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.labels = labels;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.author = author;
        this.asignee = asignee;
        this.listComments = listComments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRef_id() {
        return ref_id;
    }

    public void setRef_id(Integer ref_id) {
        this.ref_id = ref_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAsignee() {
        return asignee;
    }

    public void setAsignee(User asignee) {
        this.asignee = asignee;
    }

    public List<Comment> getListComments() {
        return listComments;
    }

    public void setListComments(List<Comment> listComments) {
        this.listComments = listComments;
    }
}

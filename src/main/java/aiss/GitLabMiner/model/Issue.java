
package aiss.GitLabMiner.model;

import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "iid",
        "project_id",
        "title",
        "description",
        "state",
        "created_at",
        "updated_at",
        "closed_at",
        "closed_by",
        "labels",
        "milestone",
        "assignees",
        "author",
        "type",
        "assignee",
        "user_notes_count",
        "merge_requests_count",
        "upvotes",
        "downvotes",
        "due_date",
        "confidential",
        "discussion_locked",
        "issue_type",
        "web_url",
        "time_stats",
        "task_completion_status",
        "weight",
        "blocking_issues_count",
        "has_tasks",
        "task_status",
        "_links",
        "references",
        "severity",
        "moved_to_id",
        "service_desk_reply_to",
        "epic_iid",
        "epic",
        "iteration",
        "health_status"
})
@Generated("jsonschema2pojo")
public class Issue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("iid")
    private String ref_id;
    @JsonProperty("project_id")
    private String project_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("state")
    private String state;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private Object closedAt;
    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("upvotes")
    private Integer upvotes;
    @JsonProperty("downvotes")
    private Integer downvotes;

    @JsonProperty("author")
    private User author;

    @JsonProperty("asignee")
    private User assignee;


    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("iid")
    public String getRef_id() {
        return ref_id;
    }

    @JsonProperty("iid")
    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public Object getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonProperty("upvotes")
    public Integer getUpvotes() {
        return upvotes;
    }

    @JsonProperty("upvotes")
    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    @JsonProperty("downvotes")
    public Integer getDownvotes() {
        return downvotes;
    }

    @JsonProperty("downvotes")
    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    @JsonProperty("author")
    public User getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(User author) {
        this.author = author;
    }

    @JsonProperty("assignee")

    public User getAssignee() {
        return assignee;
    }

    @JsonProperty("asignee")
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", iid=" + ref_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt=" + closedAt +
                ", labels=" + labels +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", author=" + author +
                ", asignee=" + assignee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id) && Objects.equals(ref_id, issue.ref_id) && Objects.equals(title, issue.title) && Objects.equals(description, issue.description) && Objects.equals(state, issue.state) && Objects.equals(createdAt, issue.createdAt) && Objects.equals(updatedAt, issue.updatedAt) && Objects.equals(closedAt, issue.closedAt) && Objects.equals(labels, issue.labels) && Objects.equals(upvotes, issue.upvotes) && Objects.equals(downvotes, issue.downvotes) && Objects.equals(author, issue.author) && Objects.equals(assignee, issue.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ref_id, title, description, state, createdAt, updatedAt, closedAt, labels, upvotes, downvotes, author, assignee);
    }
}

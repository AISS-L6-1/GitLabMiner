
package aiss.GitLabMiner.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "message",
    "author_name",
    "author_email",
    "authored_date",
    "committer_name",
    "committer_email",
    "committed_date",
    "web_url"
})
@Generated("jsonschema2pojo")
public class Commit {
    /*
    Propiedades que quiero -> [id, tittle, message, authorName, authorEmail, authoredDate,
    committerName, committerEmail, committedDate, webUrl]
     */
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("message")
    private String message;
    @JsonProperty("author_name")
    private String author_name;
    @JsonProperty("author_email")
    private String author_email;
    @JsonProperty("authored_date")
    private String authored_date;
    @JsonProperty("committer_name")
    private String committer_name;
    @JsonProperty("committer_email")
    private String committer_email;
    @JsonProperty("committed_date")
    private String committed_date;

    @JsonProperty("web_url")
    private String web_url;

    public Commit(){

    }

    public Commit(String id, String title, String message, String authorName, String authorEmail, String authoredDate, String committerName, String committerEmail, String committedDate, String webUrl) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.author_name = authorName;
        this.author_email = authorEmail;
        this.authored_date = authoredDate;
        this.committer_name = committerName;
        this.committer_email = committerEmail;
        this.committed_date = committedDate;
        this.web_url = webUrl;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("author_name")
    public String getAuthor_name() {
        return author_name;
    }

    @JsonProperty("author_name")
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    @JsonProperty("author_email")
    public String getAuthor_email() {
        return author_email;
    }

    @JsonProperty("author_email")
    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    @JsonProperty("authored_date")
    public String getAuthored_date() {
        return authored_date;
    }

    @JsonProperty("authored_date")
    public void setAuthored_date(String authored_date) {
        this.authored_date = authored_date;
    }

    @JsonProperty("committer_name")
    public String getCommitter_name() {
        return committer_name;
    }

    @JsonProperty("committer_name")
    public void setCommitter_name(String committer_name) {
        this.committer_name = committer_name;
    }

    @JsonProperty("committer_email")
    public String getCommitter_email() {
        return committer_email;
    }

    @JsonProperty("committer_email")
    public void setCommitter_email(String committer_email) {
        this.committer_email = committer_email;
    }

    @JsonProperty("committed_date")
    public String getCommitted_date() {
        return committed_date;
    }

    @JsonProperty("committed_date")
    public void setCommitted_date(String committed_date) {
        this.committed_date = committed_date;
    }

    @JsonProperty("web_url")
    public String getWeb_url() {
        return web_url;
    }

    @JsonProperty("web_url")
    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("authorName");
        sb.append('=');
        sb.append(((this.author_name == null)?"<null>":this.author_name));
        sb.append(',');
        sb.append("authorEmail");
        sb.append('=');
        sb.append(((this.author_email == null)?"<null>":this.author_email));
        sb.append(',');
        sb.append("authoredDate");
        sb.append('=');
        sb.append(((this.authored_date == null)?"<null>":this.authored_date));
        sb.append(',');
        sb.append("committerName");
        sb.append('=');
        sb.append(((this.committer_name == null)?"<null>":this.committer_name));
        sb.append(',');
        sb.append("committerEmail");
        sb.append('=');
        sb.append(((this.committer_email == null)?"<null>":this.committer_email));
        sb.append(',');
        sb.append("committedDate");
        sb.append('=');
        sb.append(((this.committed_date == null)?"<null>":this.committed_date));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.web_url == null)?"<null>":this.web_url));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}


package aiss.GitLabMiner.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "username",
    "name",
    "state",
    "avatar_url",
    "web_url"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("name")
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.avatarUrl == null)? 0 :this.avatarUrl.hashCode()));
        result = ((result* 31)+((this.webUrl == null)? 0 :this.webUrl.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.username == null)? 0 :this.username.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return (((((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl)))&&((this.webUrl == rhs.webUrl)||((this.webUrl!= null)&&this.webUrl.equals(rhs.webUrl))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id)))&&((this.username == rhs.username)||((this.username!= null)&&this.username.equals(rhs.username))));
    }

    public User(Integer id, String username, String name, String avatarUrl, String webUrl, Map<String, Object> additionalProperties) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.webUrl = webUrl;
    }
}

package org.petrov.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostDto {

    @JsonProperty("post_id")
    private long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String content;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty
    private List<TagDto> tags;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }
}

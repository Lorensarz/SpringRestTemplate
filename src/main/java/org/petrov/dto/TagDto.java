package org.petrov.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TagDto {
    @JsonProperty("tag_id")
    private long id;
    @JsonProperty("tag_name")
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

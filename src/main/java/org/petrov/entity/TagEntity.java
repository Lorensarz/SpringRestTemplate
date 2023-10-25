package org.petrov.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private long id;

    @Column(name = "tag_name")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<PostEntity> posts;

    public TagEntity() {
    }

    public TagEntity(long id, String name, List<PostEntity> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

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

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return id == tagEntity.id && Objects.equals(name, tagEntity.name) && Objects.equals(posts, tagEntity.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, posts);
    }

    @Override
    public String toString() {
        return "TagEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }
}

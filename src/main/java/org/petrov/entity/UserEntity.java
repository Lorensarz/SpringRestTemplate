package org.petrov.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany
    private List<PostEntity> posts;

    public UserEntity() {
    }

    public UserEntity(long id, String name, String email, List<PostEntity> posts) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(posts, that.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, posts);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", posts=" + posts +
                '}';
    }
}

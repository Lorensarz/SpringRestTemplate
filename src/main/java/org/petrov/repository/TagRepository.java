package org.petrov.repository;

import org.petrov.entity.PostEntity;
import org.petrov.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository <TagEntity, Long> {

    void addTagToPost(PostEntity postEntity);

    void updateTagsForPost(PostEntity postEntity);

    void removeTagFromPost(PostEntity postEntity);

    @Query("SELECT t FROM TagEntity t WHERE t.posts = :postEntity")
    List<TagEntity> findTagsByPostId(@Param("postEntity")PostEntity entity);
}

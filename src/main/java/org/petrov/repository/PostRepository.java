package org.petrov.repository;

import org.petrov.entity.PostEntity;
import org.petrov.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findPostsByUserId(long userId);
    @Query("SELECT p FROM PostEntity p JOIN p.tags t WHERE t = :tagEntity")
    List<PostEntity> findPostsByTagEntity(@Param("tagEntity") TagEntity tagEntity);
}

package org.petrov.repository;

import org.petrov.entity.PostEntity;
import org.petrov.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TagRepository extends JpaRepository <TagEntity, Long> {
    TagEntity findByName(String name);
}

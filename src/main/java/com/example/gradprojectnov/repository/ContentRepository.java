package com.example.gradprojectnov.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gradprojectnov.model.ContentEntity;
import com.example.gradprojectnov.model.ContentTypeEnum;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Long>  {
	List<ContentEntity> findByContentType(ContentTypeEnum contentTypeEnum);
	Optional<ContentEntity> findById(Long id);
}
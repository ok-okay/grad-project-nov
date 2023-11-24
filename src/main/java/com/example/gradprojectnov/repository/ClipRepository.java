package com.example.gradprojectnov.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gradprojectnov.model.ClipEntity;
import com.example.gradprojectnov.model.ClipTypeEnum;

public interface ClipRepository extends JpaRepository<ClipEntity, Long> {
	Optional<ClipEntity> findByContentIdAndClipType(long contentId, ClipTypeEnum clipType);
}

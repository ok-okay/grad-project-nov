package com.example.gradprojectnov.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gradprojectnov.model.SeasonEntity;


@Repository
public interface SeasonRepository extends JpaRepository<SeasonEntity, Long>  {
	Optional<SeasonEntity> findByContentIdAndSeasonNumber(Long contentId, int SeasonNumber);
}

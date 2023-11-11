package com.example.gradprojectnov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	private String description;
	
	@Enumerated(EnumType.STRING)
    private RoleTypeEnum roleType;
	
	@ManyToOne
	@JoinColumn(name = "creative_id")
	private CreativeEntity creative;
	
	@ManyToOne
	@JoinColumn(name="content_id")
	private ContentEntity content;
	
	@ManyToOne
	@JoinColumn(name="episode_id")
	private EpisodeEntity episode;
}

package com.example.gradprojectnov.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
public class EpisodeEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String title;
    private String description;
    private String thumbnail;
    private String releaseDate;
    private int duration;
    private int episodeNumber;
    
    @ManyToOne
    @JoinColumn(name="season_id")
    private SeasonEntity season;
    
    @OneToMany(mappedBy="episode")
    private Set<RoleEntity> roles;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public int getDuration() {
		return duration;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public SeasonEntity getSeason() {
		return season;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}
}

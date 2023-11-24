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
@Table(name = "seasons")
public class SeasonEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String releaseDate;
    private int seasonNumber;
    
    @OneToMany(mappedBy="season")
    private Set<EpisodeEntity> episodes;
    
    @ManyToOne
    @JoinColumn(name="content_id")
    private ContentEntity content;

	public Long getId() {
		return id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public Set<EpisodeEntity> getEpisodes() {
		return episodes;
	}

	public ContentEntity getContent() {
		return content;
	}
}

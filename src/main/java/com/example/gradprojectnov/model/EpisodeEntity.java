package com.example.gradprojectnov.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private String releaseDate;
    private int duration;
    private String trailerUrl;
    private String posterUrl;
    
    @OneToMany(mappedBy="episode")
    private Set<RoleEntity> roles;
    
    @ManyToOne
    @JoinColumn(name="season_id")
    private SeasonEntity season;

}

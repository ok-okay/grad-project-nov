package com.example.gradprojectnov.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "content")
public class ContentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String title;
    private String description;
    private String releaseDate;
    private int duration;
    private String logoUrl;
    private String trailerUrl;
    private String posterUrl;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name="content_language",
    		joinColumns= @JoinColumn(name="content_id"),
    		inverseJoinColumns= @JoinColumn(name="language_id")
    )
    private Set<LanguageEntity> languages;

    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name="content_genre",
    		joinColumns= @JoinColumn(name="content_id"),
    		inverseJoinColumns= @JoinColumn(name="genre_id")
    )
    private Set<GenreEntity> genres;
    
    @Enumerated(EnumType.STRING)
    private RatingEnum rating;
    
    @Enumerated(EnumType.STRING)
    private ContentTypeEnum contentType;

    @OneToMany(mappedBy="content", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles;
    
    @OneToMany(mappedBy="content", fetch = FetchType.LAZY)
    private Set<SeasonEntity> seasons;
}

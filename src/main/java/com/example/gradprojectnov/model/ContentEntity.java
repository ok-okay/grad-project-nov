package com.example.gradprojectnov.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    
    @ManyToMany
    @JoinTable(
    		name="content_language",
    		joinColumns= @JoinColumn(name="content_id"),
    		inverseJoinColumns= @JoinColumn(name="language_id")
    )
    private Set<LanguageEntity> languages;

    
    @ManyToMany
    @JoinTable(
    		name="content_genre",
    		joinColumns= @JoinColumn(name="content_id"),
    		inverseJoinColumns= @JoinColumn(name="genre_id")
    )
    private Set<GenreEntity> genres;
    
    @ManyToMany
    @JoinTable(
    		name="content_collections",
    		joinColumns= @JoinColumn(name="content_id"),
    		inverseJoinColumns= @JoinColumn(name="collection_id")
    )
    private Set<CollectionEntity> collections;
    
    @Enumerated(EnumType.STRING)
    private RatingEnum rating;
    
    @Enumerated(EnumType.STRING)
    private ContentTypeEnum contentType;

    @OneToMany(mappedBy="content")
    private Set<RoleEntity> roles;
    
    @OneToMany(mappedBy="content")
    private Set<SeasonEntity> seasons;
    
    @OneToMany(mappedBy="content")
    private Set<ResourceEntity> resources;
}

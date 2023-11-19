package com.example.gradprojectnov.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "collections")
public class CollectionEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String name;
	private Boolean display;
	
	@ManyToMany(mappedBy="collections")
	private Set<ContentEntity> contents;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getDisplay() {
		return display;
	}

	public Set<ContentEntity> getContents() {
		return contents;
	}
}

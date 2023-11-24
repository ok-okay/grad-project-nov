package com.example.gradprojectnov.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "creatives")
public class CreativeEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String name;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private CreativeTypeEnum creativeType;
	
	@OneToMany(mappedBy="creative")
	private Set<RoleEntity> roles;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public CreativeTypeEnum getCreativeType() {
		return creativeType;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}
}

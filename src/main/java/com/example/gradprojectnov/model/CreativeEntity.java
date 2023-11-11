package com.example.gradprojectnov.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
	
	@OneToMany(mappedBy="creatives", fetch = FetchType.LAZY)
	private Set<RoleEntity> roles;
}

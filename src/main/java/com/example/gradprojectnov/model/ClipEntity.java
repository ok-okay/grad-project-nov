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
@Table(name = "clips")
public class ClipEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String link;
    private String description;
    private int duration;
	
    @Enumerated(EnumType.STRING)
    private ClipTypeEnum clipType;
    
    @ManyToOne
    @JoinColumn(name="content_id")
    private ContentEntity content;

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public int getDuration() {
		return duration;
	}

	public ClipTypeEnum getClipType() {
		return clipType;
	}

	public ContentEntity getContent() {
		return content;
	}
}

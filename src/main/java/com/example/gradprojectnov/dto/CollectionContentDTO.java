package com.example.gradprojectnov.dto;

import java.util.Set;

public class CollectionContentDTO {
	private Long id;
	private String title;
	private int releaseYear;
	private String thumbnailNormal;
	private String thumbnailHover;
	private String contentType;
	private int duration;
	private String rating;
	private String description;
	private String trailerUrl;
	private Set<String> languages;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getThumbnailNormal() {
		return thumbnailNormal;
	}
	public void setThumbnailNormal(String thumbnailNormal) {
		this.thumbnailNormal = thumbnailNormal;
	}
	public String getThumbnailHover() {
		return thumbnailHover;
	}
	public void setThumbnailHover(String thumbnailHover) {
		this.thumbnailHover = thumbnailHover;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Set<String> getLanguages() {
		return languages;
	}
	public void setLanguages(Set<String> languages) {
		this.languages = languages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTrailerUrl() {
		return trailerUrl;
	}
	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}
}

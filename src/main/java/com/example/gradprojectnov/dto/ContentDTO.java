package com.example.gradprojectnov.dto;

import java.util.Set;

public class ContentDTO {
	private Long id;
    private String title;
    private String description;
    private String releaseDate;
    private int duration;
    private String logoUrl;
    private String thumbnailNormal;
    private String thumbnailHover;
    private Set<Long> languageIds;
    private Set<Long> genreIds;
    private Set<Long> collectionIds;
    private String rating;
    private String contentType;
    private Set<Long> roleIds;
    private Set<Long> seasonIds;
    private Set<Long> clipIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public Set<Long> getLanguageIds() {
		return languageIds;
	}
	public void setLanguageIds(Set<Long> languageIds) {
		this.languageIds = languageIds;
	}
	public Set<Long> getGenreIds() {
		return genreIds;
	}
	public void setGenreIds(Set<Long> genreIds) {
		this.genreIds = genreIds;
	}
	public Set<Long> getCollectionIds() {
		return collectionIds;
	}
	public void setCollectionIds(Set<Long> collectionIds) {
		this.collectionIds = collectionIds;
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
	public Set<Long> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}
	public Set<Long> getSeasonIds() {
		return seasonIds;
	}
	public void setSeasonIds(Set<Long> seasonIds) {
		this.seasonIds = seasonIds;
	}
	public Set<Long> getClipIds() {
		return clipIds;
	}
	public void setClipIds(Set<Long> clipIds) {
		this.clipIds = clipIds;
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
}

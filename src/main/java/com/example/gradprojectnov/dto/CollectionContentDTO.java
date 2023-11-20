package com.example.gradprojectnov.dto;

import java.util.Set;

public class CollectionContentDTO {
	private Long id;
	private int releaseYear;
	private int duration;
	private int seasons;
	private String rating;
	private String description;
	private Set<String> languages;
	private String posterListNormal;
	private String posterListHover;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPosterListNormal() {
		return posterListNormal;
	}
	public void setPosterListNormal(String posterListNormal) {
		this.posterListNormal = posterListNormal;
	}
	public String getPosterListHover() {
		return posterListHover;
	}
	public void setPosterListHover(String posterListHover) {
		this.posterListHover = posterListHover;
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
	public int getSeasons() {
		return seasons;
	}
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}
}

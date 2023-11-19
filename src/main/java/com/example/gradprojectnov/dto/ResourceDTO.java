package com.example.gradprojectnov.dto;

public class ResourceDTO {
	private Long id;
    private String link;
    private String description;
    private int duration;
    private String resourceCategory;
    private String resourceDisplay;
    private Long contentId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getResourceCategory() {
		return resourceCategory;
	}
	public void setResourceCategory(String resourceCategory) {
		this.resourceCategory = resourceCategory;
	}
	public String getResourceDisplay() {
		return resourceDisplay;
	}
	public void setResourceDisplay(String resourceDisplay) {
		this.resourceDisplay = resourceDisplay;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
}

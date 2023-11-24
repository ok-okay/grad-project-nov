package com.example.gradprojectnov.dto;

public class ClipDTO {
	private Long id;
    private String link;
    private String description;
    private int duration;
    private String thumbnail;
    private String clipType;

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
	public String getClipType() {
		return clipType;
	}
	public void setClipType(String clipType) {
		this.clipType = clipType;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}

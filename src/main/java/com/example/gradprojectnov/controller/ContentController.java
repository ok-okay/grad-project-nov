package com.example.gradprojectnov.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradprojectnov.Service.ContentService;
import com.example.gradprojectnov.dto.CollectionContentDTO;

@RestController
@RequestMapping("/v1")
public class ContentController {
	private final ContentService contentService;
	
	@Autowired
	public ContentController(ContentService contentService) {
		this.contentService = contentService;
	}
	
	@GetMapping("/{contentType}")
	public ResponseEntity<Map<String, List<CollectionContentDTO>>> getContentsFromType (@PathVariable String contentType){
		Map<String, List<CollectionContentDTO>> collectionContentMap = contentService.getContentsFromType(contentType);
		return new ResponseEntity<>(collectionContentMap, HttpStatus.OK);
	}
}

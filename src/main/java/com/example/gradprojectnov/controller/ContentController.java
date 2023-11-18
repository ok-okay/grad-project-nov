package com.example.gradprojectnov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradprojectnov.Service.ContentService;

@RestController
@RequestMapping("/v1")
public class ContentController {
	private final ContentService contentService;
	
	@Autowired
	public ContentController(ContentService contentService) {
		this.contentService = contentService;
	}
	
	@GetMapping("/{collection}")
	public ResponseEntity<String> getContentsFromCollection(@PathVariable String collection){
		contentService.getContentsFromCollection(collection);
		return new ResponseEntity<>(collection , HttpStatus.OK);
	}
}

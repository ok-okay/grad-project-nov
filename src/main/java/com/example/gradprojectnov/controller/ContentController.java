package com.example.gradprojectnov.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradprojectnov.dto.CollectionContentDTO;
import com.example.gradprojectnov.exceptions.InvalidContentTypeException;
import com.example.gradprojectnov.service.ContentService;

@RestController
@RequestMapping("/v1")
public class ContentController {
	private final ContentService contentService;
	
	@Autowired
	public ContentController(ContentService contentService) {
		this.contentService = contentService;
	}
	
	@GetMapping("/{contentType}")
	public ResponseEntity<?> getContentsFromType (@PathVariable String contentType){
		try {
			Map<String, List<CollectionContentDTO>> collectionContentMap = contentService.getContentsFromType(contentType);			
			return new ResponseEntity<>(collectionContentMap, HttpStatus.OK);
		} catch(InvalidContentTypeException e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Invalid user input");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Server down, please try again later");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/{contentType}/{contentTitle}/{contentId}")
	public ResponseEntity<?> getContentFromIdentifiers(
				@PathVariable long contentId
			){
		try {
			Map<String, Object> contentFromId = contentService.getContentFromId(contentId);
			return new ResponseEntity<>(contentFromId, HttpStatus.OK);			
		}  catch(InvalidContentTypeException e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Invalid user input");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		} catch(NoSuchElementException e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Content not found");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Server down, please try again later");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{contentId}/{episodeNumber}")
	public ResponseEntity<?> getEpisodeFromEpisodeNumber(
				@PathVariable long contentId,
				@PathVariable int seasonNumber
			){
		try {
			contentService.getEpisodeFromEpisodeNumber(contentId, seasonNumber);
			return new ResponseEntity<>("Hello world", HttpStatus.OK);			
		}  catch(InvalidContentTypeException e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Invalid user input");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		} catch(NoSuchElementException e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Content not found");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("message", "Server down, please try again later");
			err.put("error", e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

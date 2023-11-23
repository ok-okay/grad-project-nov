package com.example.gradprojectnov.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradprojectnov.dto.CollectionContentDTO;
import com.example.gradprojectnov.dto.ContentDTO;
import com.example.gradprojectnov.dto.DTOMapper;
import com.example.gradprojectnov.dto.ClipDTO;
import com.example.gradprojectnov.exceptions.InvalidContentTypeException;
import com.example.gradprojectnov.model.CollectionEntity;
import com.example.gradprojectnov.model.ContentEntity;
import com.example.gradprojectnov.model.ContentTypeEnum;
import com.example.gradprojectnov.model.ClipEntity;
import com.example.gradprojectnov.repository.ContentRepository;

@Service
public class ContentService {
	private final ContentRepository contentRepo;
	private final DTOMapper dtoMapper;
	
	@Autowired
	public ContentService(
			ContentRepository contentRepo,
			DTOMapper dtoMapper
		) {
		this.contentRepo = contentRepo;
		this.dtoMapper = dtoMapper;
	}
	
    private boolean isValidContentType(String contentType) {
        for (ContentTypeEnum validType : ContentTypeEnum.values()) {
            if (validType.name().equalsIgnoreCase(contentType)) {
                return true;
            }
        }
        return false;
    }
	
	public Map<String, List<CollectionContentDTO>> getContentsFromType(String contentType) {
		if(!isValidContentType(contentType)) {
			throw new InvalidContentTypeException("Invalid contentType: " + contentType);
		}
		ContentTypeEnum contentTypeEnum = ContentTypeEnum.valueOf(contentType.toUpperCase());
		List<ContentEntity> contentEntityList = contentRepo.findByContentType(contentTypeEnum);

		Map<String, List<CollectionContentDTO>> collectionContentMap = new HashMap<String, List<CollectionContentDTO>>();
		
		for(int i = 0; i<contentEntityList.size(); i++) {
			ContentEntity contentEntity = contentEntityList.get(i);
			
			for(CollectionEntity collectionEntity : contentEntity.getCollections()) {
				Boolean display = collectionEntity.getDisplay();
				if(display) {
					String collectionName = collectionEntity.getName();
					if(collectionContentMap.containsKey(collectionName)) {
						List<CollectionContentDTO> collectionContentDTOList = collectionContentMap.get(collectionName);
						collectionContentDTOList.add(dtoMapper.collectionContentMapper(contentEntity));
						collectionContentMap.put(collectionName, collectionContentDTOList);
					} else {
						List<CollectionContentDTO> collectionContentDTOList = new ArrayList<CollectionContentDTO>();
						collectionContentDTOList.add(dtoMapper.collectionContentMapper(contentEntity));
						collectionContentMap.put(collectionName, collectionContentDTOList);
					}
				}
			}
		}
		return collectionContentMap;
	}
	
	public void getContentFromId(long contentId) {
		ContentEntity contentEntity = contentRepo.findById(contentId).get();
//		id
//		title
//		contentType
//		logo
//		poster blown up
//		trailer
//		release year
//		duration
//		seasons
//		languages
//		rating
//		description
//		genres
//		episodes of season 1
//		episode-id
//		episode-number
//		episode-release-date
//		episode-duration
//		episode-description
//		episode-thumbnail (TO BE ADDED)
//		clips
//		clip-id
//		clip-title
//		clip-thumbnail (TO BE ADDED)
//		clip-duration
		System.out.print(contentEntity);
	}
	
}

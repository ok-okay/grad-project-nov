package com.example.gradprojectnov.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradprojectnov.dto.CollectionContentDTO;
import com.example.gradprojectnov.dto.ContentDTO;
import com.example.gradprojectnov.dto.DTOMapper;
import com.example.gradprojectnov.dto.ResourceDTO;
import com.example.gradprojectnov.model.CollectionEntity;
import com.example.gradprojectnov.model.ContentEntity;
import com.example.gradprojectnov.model.ContentTypeEnum;
import com.example.gradprojectnov.model.ResourceEntity;
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
	
	public Map<String, List<CollectionContentDTO>> getContentsFromType(String contentType) {
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
}

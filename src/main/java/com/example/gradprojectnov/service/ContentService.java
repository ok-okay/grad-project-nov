package com.example.gradprojectnov.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradprojectnov.dto.CollectionContentDTO;
import com.example.gradprojectnov.dto.ContentDTO;
import com.example.gradprojectnov.dto.CreativeDTO;
import com.example.gradprojectnov.dto.DTOMapper;
import com.example.gradprojectnov.dto.EpisodeDTO;
import com.example.gradprojectnov.dto.GenreDTO;
import com.example.gradprojectnov.dto.LanguageDTO;
import com.example.gradprojectnov.dto.RoleDTO;
import com.example.gradprojectnov.dto.ClipDTO;
import com.example.gradprojectnov.exceptions.InvalidContentTypeException;
import com.example.gradprojectnov.model.CollectionEntity;
import com.example.gradprojectnov.model.ContentEntity;
import com.example.gradprojectnov.model.ContentTypeEnum;
import com.example.gradprojectnov.model.CreativeEntity;
import com.example.gradprojectnov.model.EpisodeEntity;
import com.example.gradprojectnov.model.GenreEntity;
import com.example.gradprojectnov.model.LanguageEntity;
import com.example.gradprojectnov.model.RoleEntity;
import com.example.gradprojectnov.model.SeasonEntity;
import com.example.gradprojectnov.model.ClipEntity;
import com.example.gradprojectnov.repository.ContentRepository;
import com.example.gradprojectnov.repository.SeasonRepository;

@Service
public class ContentService {
	private final ContentRepository contentRepo;
	private final SeasonRepository seasonRepo;
	private final DTOMapper dtoMapper;
	
	@Autowired
	public ContentService(
			ContentRepository contentRepo,
			SeasonRepository seasonRepo,
			DTOMapper dtoMapper
		) {
		this.contentRepo = contentRepo;
		this.seasonRepo = seasonRepo;
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
	
	public Map<String, Object> getContentFromId(long contentId) {
		Map<String, Object> contentFromId = new HashMap<>();
		
		ContentEntity contentEntity = contentRepo.findById(contentId).get();
		ContentDTO contentDTO = dtoMapper.contentDTOMapper(contentEntity);
		
		contentFromId.put("id", contentDTO.getId());
		contentFromId.put("title", contentDTO.getTitle());
		contentFromId.put("contentType", contentDTO.getContentType());
		contentFromId.put("logo", contentDTO.getLogoUrl());
		contentFromId.put("poster", contentDTO.getPosterUrl());
		contentFromId.put("releaseYear", Integer.parseInt(contentDTO.getReleaseDate().substring(0, 4)));
		
		if(contentFromId.get("contentType").equals("MOVIES")) {
			contentFromId.put("duration", contentDTO.getDuration());			
		} else if (contentFromId.get("contentType").equals("SERIES")) {
			contentFromId.put("seasons", contentDTO.getSeasonIds().size());
		}

		contentFromId.put("rating", contentDTO.getRating());
		contentFromId.put("description", contentDTO.getDescription());
		
		Set<LanguageEntity> languageEntitySet = contentEntity.getLanguages();
		List<LanguageDTO> languages = new ArrayList<>();
		for(LanguageEntity languageEntity : languageEntitySet) {
			languages.add(dtoMapper.languageDTOMapper(languageEntity));
		}
        Collections.sort(languages, Comparator.comparingLong(LanguageDTO::getId));
		contentFromId.put("languages", languages);

		Set<GenreEntity> genreEntitySet = contentEntity.getGenres();
		List<GenreDTO> genres = new ArrayList<>();
		for(GenreEntity genreEntity : genreEntitySet) {
			genres.add(dtoMapper.genreDTOMapper(genreEntity));
		}
		Collections.sort(genres, Comparator.comparingLong(GenreDTO::getId));
		contentFromId.put("genres", genres);
		
		Set<ClipEntity> clipEntitySet = contentEntity.getClips();
		List<ClipDTO> clips = new ArrayList<>();
		for(ClipEntity clipEntity : clipEntitySet) {
			clips.add(dtoMapper.clipDTOMapper(clipEntity));
		}
		Collections.sort(clips, Comparator.comparingLong(ClipDTO::getId));
		contentFromId.put("clips", clips);
		
		if(contentFromId.get("contentType").equals("SERIES")) {
			SeasonEntity seasonEntity = seasonRepo.findByContentIdAndSeasonNumber(contentDTO.getId(), 1).get();
			Set<EpisodeEntity> episodeEntitySet = seasonEntity.getEpisodes();
			Map<String, List<EpisodeDTO>> episodes = new HashMap<>();
			List<EpisodeDTO> episodesList = new ArrayList<>();
			for(EpisodeEntity episodeEntity : episodeEntitySet) {
				episodesList.add(dtoMapper.episodeDTOMapper(episodeEntity));
			}
			Collections.sort(episodesList, Comparator.comparingInt(EpisodeDTO::getEpisodeNumber));
			episodes.put("Season 1", episodesList);
			contentFromId.put("episodes", episodes);
		}
		
		Set<RoleEntity> roleEntitySet = contentEntity.getRoles();
		Map<String, List<Map<String, Object>>> castAndCrewMap = new HashMap<>();
		castAndCrewMap.put("CAST", new ArrayList<>());
		castAndCrewMap.put("CREW", new ArrayList<>());
		for(RoleEntity roleEntity : roleEntitySet) {
			Map<String, Object> creativeRole = new HashMap<>();
			RoleDTO roleDTO = dtoMapper.roleDTOMapper(roleEntity);
			CreativeEntity creativeEntity = roleEntity.getCreative();
			CreativeDTO creativeDTO = dtoMapper.creativeDTOMapper(creativeEntity);
			
			creativeRole.put("role", roleDTO);
			creativeRole.put("creative", creativeDTO);
			List<Map<String, Object>> castOrCrewList = castAndCrewMap.get(roleDTO.getRoleType());
			castOrCrewList.add(creativeRole);
			castAndCrewMap.put(roleDTO.getRoleType(), castOrCrewList);
		}
		contentFromId.put("cast", castAndCrewMap.get("CAST"));
		contentFromId.put("crew", castAndCrewMap.get("CREW"));

		return contentFromId;
	}
	
	public void getEpisodeFromEpisodeNumber(long contentId, int seasonNumber) {
		System.out.println(contentId);
		System.out.println(seasonNumber);
	}
	
}

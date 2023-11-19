package com.example.gradprojectnov.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.gradprojectnov.model.CollectionEntity;
import com.example.gradprojectnov.model.ContentEntity;
import com.example.gradprojectnov.model.GenreEntity;
import com.example.gradprojectnov.model.LanguageEntity;
import com.example.gradprojectnov.model.ResourceEntity;
import com.example.gradprojectnov.model.RoleEntity;
import com.example.gradprojectnov.model.SeasonEntity;

@Component
public class DTOMapper {
	private <T> Set<Long> mapEntitiesToIds(Set<T> entities, Function<T, Long> idExtractor) {
        return entities.stream()
                .map(idExtractor)
                .collect(Collectors.toSet());
    }
	
	public ContentDTO contentDTOMapper(ContentEntity contentEntity) {
		ContentDTO contentDTO = new ContentDTO();
        contentDTO.setId(contentEntity.getId());
        contentDTO.setTitle(contentEntity.getTitle());
        contentDTO.setDescription(contentEntity.getDescription());
        contentDTO.setReleaseDate(contentEntity.getReleaseDate());
        contentDTO.setDuration(contentEntity.getDuration());
        contentDTO.setLogoUrl(contentEntity.getLogoUrl());
        contentDTO.setLanguageIds(mapEntitiesToIds(contentEntity.getLanguages(), LanguageEntity::getId));
        contentDTO.setGenreIds(mapEntitiesToIds(contentEntity.getGenres(), GenreEntity::getId));
        contentDTO.setCollectionIds(mapEntitiesToIds(contentEntity.getCollections(), CollectionEntity::getId));
        contentDTO.setRating(contentEntity.getRating().toString());
        contentDTO.setContentType(contentEntity.getContentType().toString());
        contentDTO.setRoleIds(mapEntitiesToIds(contentEntity.getRoles(), RoleEntity::getId));
        contentDTO.setSeasonIds(mapEntitiesToIds(contentEntity.getSeasons(), SeasonEntity::getId));
        contentDTO.setResourceIds(mapEntitiesToIds(contentEntity.getResources(), ResourceEntity::getId));
		return contentDTO;
	}
	
	public LanguageDTO languageDTOMapper(LanguageEntity languageEntity) {
		LanguageDTO languageDTO = new LanguageDTO();
		languageDTO.setName(languageEntity.getName());
		return languageDTO;
	}
	
	public ResourceDTO resourceDTOMapper(ResourceEntity resourceEntity) {
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setId(resourceEntity.getId());
		resourceDTO.setLink(resourceEntity.getLink());
		resourceDTO.setDescription(resourceEntity.getDescription());
		resourceDTO.setDuration(resourceEntity.getDuration());
		resourceDTO.setResourceCategory(resourceEntity.getResourceCategory().name());
		resourceDTO.setResourceDisplay(resourceEntity.getResourceDisplay().name());
		resourceDTO.setContentId(resourceEntity.getContent().getId());
		
		return resourceDTO;
	}
	
	public CollectionContentDTO collectionContentMapper(ContentEntity contentEntity) {
		ContentDTO contentDTO = contentDTOMapper(contentEntity);
		CollectionContentDTO collectionContentDTO = new CollectionContentDTO();
		collectionContentDTO.setId(contentDTO.getId());
		collectionContentDTO.setReleaseYear(Integer.parseInt(contentDTO.getReleaseDate().substring(0, 4)));
		collectionContentDTO.setDescription(contentDTO.getDescription());
		collectionContentDTO.setRating(contentDTO.getRating());
		
		Set<LanguageDTO> languageDTOSet = new HashSet<LanguageDTO>();
		Set<LanguageEntity> languageEntitySet = contentEntity.getLanguages();
		for(LanguageEntity languageEntity : languageEntitySet) {
			languageDTOSet.add(languageDTOMapper(languageEntity));
		}
		
		Set<String> languageSet = new HashSet<String>();
		for(LanguageDTO languageDTO : languageDTOSet) {
			languageSet.add(languageDTO.getName());
		}
		collectionContentDTO.setLanguages(languageSet);
		
		Set<ResourceDTO> resourceDTOSet = new HashSet<ResourceDTO>();
		Set<ResourceEntity> resourceEntitySet = contentEntity.getResources();
		for(ResourceEntity resourceEntity : resourceEntitySet) {
			resourceDTOSet.add(resourceDTOMapper(resourceEntity));
		}

		for(ResourceDTO resourceDTO : resourceDTOSet) {
			String resourceCategory = resourceDTO.getResourceCategory();
			if(resourceCategory.equals("POSTER")) {
				String resourceDisplay = resourceDTO.getResourceDisplay();
				if(resourceDisplay.equals("LIST_VIEW_HOVER")) {
					collectionContentDTO.setPosterListHover(resourceDTO.getLink());
				}
				else if(resourceDisplay.equals("LIST_VIEW_NORMAL")) {
					collectionContentDTO.setPosterListNormal(resourceDTO.getLink());
				}
			}
		}
		
		return collectionContentDTO;
	}
}

package com.example.gradprojectnov.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.gradprojectnov.model.CollectionEntity;
import com.example.gradprojectnov.model.ContentEntity;
import com.example.gradprojectnov.model.GenreEntity;
import com.example.gradprojectnov.model.LanguageEntity;
import com.example.gradprojectnov.model.ClipEntity;
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
        contentDTO.setThumbnailNormal(contentEntity.getThumbnailNormal());
        contentDTO.setThumbnailHover(contentEntity.getThumbnailHover());
        contentDTO.setLanguageIds(mapEntitiesToIds(contentEntity.getLanguages(), LanguageEntity::getId));
        contentDTO.setGenreIds(mapEntitiesToIds(contentEntity.getGenres(), GenreEntity::getId));
        contentDTO.setCollectionIds(mapEntitiesToIds(contentEntity.getCollections(), CollectionEntity::getId));
        contentDTO.setRating(contentEntity.getRating().toString());
        contentDTO.setContentType(contentEntity.getContentType().toString());
        contentDTO.setRoleIds(mapEntitiesToIds(contentEntity.getRoles(), RoleEntity::getId));
        contentDTO.setSeasonIds(mapEntitiesToIds(contentEntity.getSeasons(), SeasonEntity::getId));
        contentDTO.setClipIds(mapEntitiesToIds(contentEntity.getClips(), ClipEntity::getId));
		return contentDTO;
	}
	
	public LanguageDTO languageDTOMapper(LanguageEntity languageEntity) {
		LanguageDTO languageDTO = new LanguageDTO();
		languageDTO.setName(languageEntity.getName());
		return languageDTO;
	}
	
	public ClipDTO clipDTOMapper(ClipEntity clipEntity) {
		ClipDTO clipDTO = new ClipDTO();
		clipDTO.setId(clipEntity.getId());
		clipDTO.setLink(clipEntity.getLink());
		clipDTO.setDescription(clipEntity.getDescription());
		clipDTO.setDuration(clipEntity.getDuration());
		clipDTO.setClipType(clipEntity.getClipType().name());
		clipDTO.setContentId(clipEntity.getContent().getId());
		
		return clipDTO;
	}
	
	public CollectionContentDTO collectionContentMapper(ContentEntity contentEntity) {
		ContentDTO contentDTO = contentDTOMapper(contentEntity);
		CollectionContentDTO collectionContentDTO = new CollectionContentDTO();
		
		collectionContentDTO.setId(contentDTO.getId());
		collectionContentDTO.setTitle(contentDTO.getTitle());
		collectionContentDTO.setReleaseYear(Integer.parseInt(contentDTO.getReleaseDate().substring(0, 4)));
		collectionContentDTO.setContentType(contentDTO.getContentType());
		
		if(collectionContentDTO.getContentType().equals("MOVIES")) {
			collectionContentDTO.setDuration(contentDTO.getDuration());			
		} else if (collectionContentDTO.getContentType().equals("SERIES")) {
			collectionContentDTO.setDuration(contentDTO.getSeasonIds().size());
		}
		
		collectionContentDTO.setRating(contentDTO.getRating());
		collectionContentDTO.setDescription(contentDTO.getDescription());
		collectionContentDTO.setThumbnailNormal(contentDTO.getThumbnailNormal());
		collectionContentDTO.setThumbnailHover(contentDTO.getThumbnailHover());
		
		Set<String> languageSet = new HashSet<String>();
		Set<LanguageEntity> languageEntitySet = contentEntity.getLanguages();
		for(LanguageEntity languageEntity : languageEntitySet) {
			languageSet.add(languageDTOMapper(languageEntity).getName());
		}
		collectionContentDTO.setLanguages(languageSet);

		Set<ClipEntity> clipEntitySet = contentEntity.getClips();
		for(ClipEntity clipEntity : clipEntitySet) {
			ClipDTO clipDTO = clipDTOMapper(clipEntity);
			String clipType = clipDTO.getClipType();
			if(clipType.equals("TRAILER_LATEST")) {
				collectionContentDTO.setTrailerUrl(clipDTO.getLink());
				break;
			}
		}
		
		return collectionContentDTO;
	}
}

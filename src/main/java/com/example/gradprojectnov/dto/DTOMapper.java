package com.example.gradprojectnov.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.gradprojectnov.model.CollectionEntity;
import com.example.gradprojectnov.model.ContentEntity;
import com.example.gradprojectnov.model.CreativeEntity;
import com.example.gradprojectnov.model.EpisodeEntity;
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
        contentDTO.setPosterUrl(contentEntity.getPosterUrl());
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
		languageDTO.setId(languageEntity.getId());
		languageDTO.setName(languageEntity.getName());
		return languageDTO;
	}
	
	public GenreDTO genreDTOMapper(GenreEntity genreEntity) {
		GenreDTO genreDTO = new GenreDTO();
		genreDTO.setId(genreEntity.getId());
		genreDTO.setName(genreEntity.getName());
		return genreDTO;
	}
	
	public ClipDTO clipDTOMapper(ClipEntity clipEntity) {
		ClipDTO clipDTO = new ClipDTO();
		clipDTO.setId(clipEntity.getId());
		clipDTO.setLink(clipEntity.getLink());
		clipDTO.setDescription(clipEntity.getDescription());
		clipDTO.setDuration(clipEntity.getDuration());
		clipDTO.setThumbnail(clipEntity.getThumbnail());
		clipDTO.setClipType(clipEntity.getClipType().name());
		return clipDTO;
	}
	
	public EpisodeDTO episodeDTOMapper(EpisodeEntity episodeEntity) {
		EpisodeDTO episodeDTO = new EpisodeDTO();
		episodeDTO.setId(episodeEntity.getId());
		episodeDTO.setTitle(episodeEntity.getTitle());
		episodeDTO.setDescription(episodeEntity.getDescription());
		episodeDTO.setThumbnail(episodeEntity.getThumbnail());
		episodeDTO.setReleaseDate(episodeEntity.getReleaseDate());
		episodeDTO.setDuration(episodeEntity.getDuration());
		episodeDTO.setEpisodeNumber(episodeEntity.getEpisodeNumber());
		return episodeDTO;
	}
	
	public RoleDTO roleDTOMapper(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleEntity.getId());
		roleDTO.setName(roleEntity.getName());
		roleDTO.setDescription(roleEntity.getDescription());
		roleDTO.setRoleType(roleEntity.getRoleType().name());
		return roleDTO;
	}
	
	public CreativeDTO creativeDTOMapper(CreativeEntity creativeEntity) {
		CreativeDTO creativeDTO = new CreativeDTO();
		creativeDTO.setId(creativeEntity.getId());
		creativeDTO.setName(creativeEntity.getName());
		creativeDTO.setDescription(creativeEntity.getDescription());
		creativeDTO.setCreativeType(creativeEntity.getCreativeType().name());
		return creativeDTO;
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

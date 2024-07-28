package com.springboot.converter;

import org.springframework.stereotype.Component;

import com.springboot.dto.request.NewRequestDTO;
import com.springboot.dto.response.NewResponseDTO;
import com.springboot.entity.NewEntity;

@Component
public class NewConverter {

    public NewEntity toEntity(NewRequestDTO dto) {
        NewEntity entity = new NewEntity();
        entity.setContent(dto.getContent());
        entity.setShortDescription(dto.getShortDescription());
        entity.setThumbnail(dto.getThumbnail());
        return entity;
    }

    public NewResponseDTO toDTO(NewEntity entity) {
        NewResponseDTO dto = new NewResponseDTO();
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setShortDescription(entity.getShortDescription());
        dto.setThumbnail(entity.getThumbnail());
        return dto;
    }

    public NewEntity toEntity(NewRequestDTO dto, NewEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setShortDescription(dto.getShortDescription());
        entity.setThumbnail(dto.getThumbnail());
        return entity;
    }
}

package com.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.converter.NewConverter;
import com.springboot.dto.request.NewRequestDTO;
import com.springboot.dto.response.NewResponseDTO;
import com.springboot.entity.CategoryEntity;
import com.springboot.entity.NewEntity;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.NewRepository;
import com.springboot.service.INewService;

@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter newConverter;

    @Override
    public NewResponseDTO save(NewRequestDTO newDTO) {
        NewEntity newEntity = new NewEntity();
        if (newDTO.getId() != null) {
            NewEntity oldNewEntity =
                    newRepository.findById(newDTO.getId()).orElseThrow(() -> new RuntimeException("User not found"));
            newEntity = newConverter.toEntity(newDTO, oldNewEntity);
        } else {
            newEntity = newConverter.toEntity(newDTO);
        }
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        newEntity.setCategory(categoryEntity);
        newEntity = newRepository.save(newEntity);
        return newConverter.toDTO(newEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long item : ids) {
            newRepository.deleteById(item);
        }
    }

    @Override
    public List<NewResponseDTO> findAll(Pageable pageable) {
        List<NewResponseDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll(pageable).getContent();
        for (NewEntity item : entities) {
            NewResponseDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) newRepository.count();
    }

    @Override
    public List<NewResponseDTO> findAll() {
        List<NewResponseDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll();
        for (NewEntity item : entities) {
            NewResponseDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }
}

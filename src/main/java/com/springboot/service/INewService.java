package com.springboot.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.springboot.dto.request.NewRequestDTO;
import com.springboot.dto.response.NewResponseDTO;

public interface INewService {
    NewResponseDTO save(NewRequestDTO newDTO);

    void delete(long[] ids);

    List<NewResponseDTO> findAll(Pageable pageable);

    List<NewResponseDTO> findAll();

    int totalItem();
}

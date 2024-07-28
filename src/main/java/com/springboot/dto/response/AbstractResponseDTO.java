package com.springboot.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractResponseDTO<T> {

    private List<T> listResult = new ArrayList<>();
}

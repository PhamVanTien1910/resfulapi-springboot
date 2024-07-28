package com.springboot.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewResponsePage {

    private int page;
    private int totalPage;
    private List<NewResponseDTO> listResult = new ArrayList<>();
}

package com.springboot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewRequestDTO extends AbstractRequestDTO<NewRequestDTO> {
    private String title;
    private String content;
    private String shortDescription;
    private String categoryCode;
    private String thumbnail;
}

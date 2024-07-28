package com.springboot.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewResponseDTO extends AbstractResponseDTO<NewResponseDTO> {

    String title;
    String content;
    String shortDescription;
    String categoryCode;
    String thumbnail;
    
}

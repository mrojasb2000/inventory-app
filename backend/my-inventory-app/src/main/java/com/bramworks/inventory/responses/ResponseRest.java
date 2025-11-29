package com.bramworks.inventory.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRest extends MetadataResponse {
    private CategoryResponse categoryResponse = new CategoryResponse();
}

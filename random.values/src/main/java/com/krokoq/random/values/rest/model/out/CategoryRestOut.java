package com.krokoq.random.values.rest.model.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class CategoryRestOut {
    private UUID id;
    private String name;
    private String description;
    private List<ValueRestOut> values;
}

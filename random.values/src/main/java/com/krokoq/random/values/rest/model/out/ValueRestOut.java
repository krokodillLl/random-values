package com.krokoq.random.values.rest.model.out;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ValueRestOut {
    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;
}

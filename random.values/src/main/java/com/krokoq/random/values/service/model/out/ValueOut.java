package com.krokoq.random.values.service.model.out;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ValueOut {
    private UUID id;
    private String name;
    private String description;
    private String picture;
    private UUID categoryId;
}

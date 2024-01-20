package com.krokoq.random.values.rest.model.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ValueRestIn {
    private String name;
    private String description;
    private String picture;
    @NotNull
    private UUID categoryId;
}

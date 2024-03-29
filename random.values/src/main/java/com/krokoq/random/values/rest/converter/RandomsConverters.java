package com.krokoq.random.values.rest.converter;

import com.krokoq.random.values.rest.model.in.CategoryRestIn;
import com.krokoq.random.values.rest.model.in.ValueRestIn;
import com.krokoq.random.values.rest.model.out.CategoryRestOut;
import com.krokoq.random.values.rest.model.out.ValueRestOut;
import com.krokoq.random.values.service.model.in.CategoryIn;
import com.krokoq.random.values.service.model.in.ValueIn;
import com.krokoq.random.values.service.model.out.CategoryOut;
import com.krokoq.random.values.service.model.out.ValueOut;

import java.util.UUID;

public interface RandomsConverters {
    default CategoryRestOut convert(CategoryOut out) {
        return CategoryRestOut.builder()
                .id(out.getId())
                .name(out.getName())
                .description(out.getDescription())
                .values(
                        out.getValues().stream()
                                .map(this::convert)
                                .toList()
                )
                .build();
    }

    default CategoryIn convert(CategoryRestIn restIn) {
        return CategoryIn.builder()
                .name(restIn.getName())
                .description(restIn.getDescription())
                .build();
    }

    default CategoryIn convert(CategoryRestIn restIn, UUID categoryId) {
        return CategoryIn.builder()
                .id(categoryId)
                .name(restIn.getName())
                .description(restIn.getDescription())
                .build();
    }

    default ValueRestOut convert(ValueOut out) {
        return ValueRestOut.builder()
                .id(out.getId())
                .name(out.getName())
                .description(out.getDescription())
                .picture(out.getPicture())
                .categoryId(out.getCategoryId())
                .build();
    }

    default ValueIn convert(ValueRestIn restIn) {
        return convert(restIn, null);
    }

    default ValueIn convert(ValueRestIn restIn, UUID valueId) {
        return ValueIn.builder()
                .id(valueId)
                .name(restIn.getName())
                .description(restIn.getDescription())
                .picture(restIn.getPicture())
                .categoryId(restIn.getCategoryId())
                .build();
    }
}

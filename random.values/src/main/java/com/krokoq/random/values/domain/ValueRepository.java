package com.krokoq.random.values.domain;

import com.krokoq.random.values.domain.entity.ValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ValueRepository extends JpaRepository<ValueEntity, UUID> {
    List<ValueEntity> findValueEntitiesByCategoryId(UUID categoryId);
}

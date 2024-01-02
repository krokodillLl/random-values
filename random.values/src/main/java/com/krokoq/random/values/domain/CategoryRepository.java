package com.krokoq.random.values.domain;

import com.krokoq.random.values.domain.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}

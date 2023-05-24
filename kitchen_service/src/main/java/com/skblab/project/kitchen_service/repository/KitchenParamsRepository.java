package com.skblab.project.kitchen_service.repository;

import com.skblab.project.kitchen_service.model.KitchenParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KitchenParamsRepository extends CrudRepository<KitchenParams, UUID> {
}

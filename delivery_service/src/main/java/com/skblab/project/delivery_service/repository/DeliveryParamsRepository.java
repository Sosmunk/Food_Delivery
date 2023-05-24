package com.skblab.project.delivery_service.repository;

import com.skblab.project.delivery_service.model.DeliveryParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeliveryParamsRepository extends CrudRepository<DeliveryParams, UUID> {
    List<DeliveryParams> findDeliveryParamsByTaken(int taken);
}

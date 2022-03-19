package org.kecsi.dddmodules.ordercontext.repository;

import org.kecsi.dddmodules.ordercontext.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
}

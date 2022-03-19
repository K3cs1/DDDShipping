package org.kecsi.dddmodules.shippingcontext.repository;

import org.kecsi.dddmodules.shippingcontext.model.PackageItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageItemRepository extends MongoRepository<PackageItem, String> {
}

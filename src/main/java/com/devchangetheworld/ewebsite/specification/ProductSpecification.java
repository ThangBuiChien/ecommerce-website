package com.devchangetheworld.ewebsite.specification;

import com.devchangetheworld.ewebsite.dto.product.ProductSearchCriteria;
import com.devchangetheworld.ewebsite.entities.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductSpecification {

    public Specification<Product> getSearchSpecification(ProductSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")),
                        "%" + criteria.getName().toLowerCase() + "%"));
            }

            if (criteria.getBrand() != null && !criteria.getBrand().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("brand")),
                        "%" + criteria.getBrand().toLowerCase() + "%"));
            }

            if (criteria.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("price"), criteria.getMinPrice()));
            }

            if (criteria.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("price"), criteria.getMaxPrice()));
            }

            if (criteria.getCategoryId() != null) {
                predicates.add(cb.equal(root.get("category").get("id"),
                        criteria.getCategoryId()));
            }

            if (criteria.getInStock() != null) {
                if (criteria.getInStock()) {
                    predicates.add(cb.greaterThan(root.get("inventory"), 0));
                } else {
                    predicates.add(cb.equal(root.get("inventory"), 0));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}

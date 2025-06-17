package com.OracleResults.OracleResults.dto;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification {

    public static Specification<ItemOracle> filterItems(String job, String type, String assembly,
            String assemblyDescription, String quantityMin,
            String quantityMax, String dateStart, String dateEnd, String assemblyStartingWith, String jobEndingWith, String assemblyDescriptionContains) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (job != null && !job.isEmpty()) {
                predicates.add(cb.equal(root.get("job"), job));
            }

            if (type != null && !type.isEmpty()) {
                predicates.add(cb.equal(root.get("type"), type));
            }

            if (assembly != null && !assembly.isEmpty()) {
                predicates.add(cb.equal(root.get("assembly"), assembly));
            }

            if (assemblyDescription != null && !assemblyDescription.isEmpty()) {
                predicates.add(cb.like(root.get("assemblyDescription"), "%" + assemblyDescription + "%"));
            }

            if (quantityMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantity"), quantityMin));
            }

            if (quantityMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantity"), quantityMax));
            }

            if (dateStart != null && dateEnd != null) {
                predicates.add(cb.between(root.get("dateCompleted"), dateStart, dateEnd));
            }

            if (assemblyStartingWith != null && !assemblyStartingWith.isEmpty()) {
                predicates.add(cb.like(root.get("assembly"), assemblyStartingWith + "%"));
            }

            if (jobEndingWith != null && !jobEndingWith.isEmpty()) {
                predicates.add(cb.like(root.get("job"), "%" + jobEndingWith));
            }

            if(assemblyDescriptionContains != null && !assemblyDescriptionContains.isEmpty()) {
                predicates.add(cb.like(root.get("description"), "%" + assemblyDescriptionContains));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

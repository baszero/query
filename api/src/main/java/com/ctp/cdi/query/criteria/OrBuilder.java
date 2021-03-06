package com.ctp.cdi.query.criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class OrBuilder<P> implements PredicateBuilder<P> {
    
    final Criteria<P>[] criteria;

    public OrBuilder(Criteria<P>... criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<Predicate> build(CriteriaBuilder builder, Path<P> path) {
        List<Predicate> and = new ArrayList<Predicate>(criteria.length);
        for (Criteria<P> c : criteria) {
            and.add(builder.and(
                    c.collectPredicates(builder, path).toArray(new Predicate[0])));
        }
        return Arrays.asList(builder.or(and.toArray(new Predicate[0])));
    }

}

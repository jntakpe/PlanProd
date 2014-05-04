package com.github.jntakpe.fmk.repository;

import com.github.jntakpe.fmk.domain.GenericDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Interface à étendre lors de la création d'un repository géré par le framework.
 * Permet de bénéficier grâce à {@link org.springframework.data.jpa.repository.JpaRepository} de tous les CRUDs et
 * d'autres méthodes usuelles et grâce à {@link org.springframework.data.querydsl.QueryDslPredicateExecutor} de la
 * création de méthodes de filtrage type safe.
 *
 * @author jntakpe
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor
 */
public interface GenericRepository<T extends GenericDomain<S>, S extends Number> extends JpaRepository<T, S>, QueryDslPredicateExecutor<T> {
}

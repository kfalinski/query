package core.utils;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.PathMetadataFactory;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import core.BaseEntity;
import core.QBaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static core.utils.GeoCollectors.toOrderedSet;
import static java.util.stream.Collectors.toList;

// Created by krzysztoff on 2014-11-21.
public abstract class GenericDao {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public <T extends BaseEntity> T save(T entity) {
        T savedEntity = saveInternal(entity);
        em.flush();
        return savedEntity;
    }

    @Transactional
    public <T extends BaseEntity> List<T> saveMany(List<T> entityList) {
        List<T> savedEntities = saveManyInternal(entityList).collect(toList());
        em.flush();
        return savedEntities;
    }

    @Transactional
    public <T extends BaseEntity> Set<T> saveMany(Set<T> entityList) {
        Set<T> savedEntities = saveManyInternal(entityList).collect(toOrderedSet(entityList.size()));
        em.flush();
        return savedEntities;
    }

    public <T extends BaseEntity> T findOneNoFetch(EntityPathBase<T> entityPath, Long id) {
        JPQLQuery query = buildQuery(entityPath);
        NumberPath<Long> longNumberPath = getLongNumberPath(entityPath);
        query = query.where(longNumberPath.eq(id));
        return query.singleResult(entityPath);
    }

    public <T extends BaseEntity> List<T> findAllNoFetch(EntityPathBase<T> entityPath) {
        JPQLQuery query = buildQuery(entityPath);
        return query.list(entityPath);
    }

    public <T extends BaseEntity> List<T> findManyNoFetch(EntityPathBase<T> entityPath, List<Long> ids) {
        JPQLQuery query = buildQuery(entityPath);
        NumberPath<Long> longNumberPath = getLongNumberPath(entityPath);
        query = query.where(longNumberPath.in(ids));
        return query.list(entityPath);
    }

    private <T extends BaseEntity> NumberPath<Long> getLongNumberPath(EntityPathBase<T> entityPath) {
        NumberPath<Long> qId = QBaseEntity.baseEntity.id;
        return new NumberPath<>(qId.getType(),
                PathMetadataFactory.forProperty(entityPath, qId.getMetadata().getName()));
    }

    protected <T extends BaseEntity> JPQLQuery buildQuery(EntityPathBase<T> entityPath) {
        JPQLQuery query = new JPAQuery(em);
        query = query.from(entityPath);
        return query;
    }

    public <T extends BaseEntity> void remove(T entity) {
        em.remove(entity);
        em.flush();
    }

    public <T extends BaseEntity> void removeMany(EntityPathBase<T> entityPathBase, List<Long> ids) {
        List<T> newList = findManyNoFetch(entityPathBase, ids);
        newList.forEach(em::remove);
        em.flush();
    }

    public <T extends BaseEntity> void removeAllEntities(EntityPathBase<T> entityPathBase) {
        List<T> list = findAllNoFetch(entityPathBase);
        list.forEach(em::remove);
        em.flush();
    }

    private <T extends BaseEntity> T saveInternal(T entity) {
        T savedEntity;
        if (entity.getId() == null) {
            em.persist(entity);
            savedEntity = entity;
        } else {
            savedEntity = em.merge(entity);
        }
        return savedEntity;
    }

    private <T extends BaseEntity> Stream<T> saveManyInternal(Collection<T> tmp) {
        return tmp.stream().map(this::saveInternal);
    }
}

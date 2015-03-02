package core.utils;

import com.mysema.query.jpa.JPQLQuery;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SortedSet;

// Created by krzysztoff on 2014-11-21.
public abstract class GenericDao {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public <T extends BaseEntity> void save(T entity) {
        saveInternal(entity);
        em.flush();
    }

    @Transactional
    public <T extends BaseEntity> void saveMany(Collection<T> entityList) {
        entityList.forEach(e -> saveInternal(e));
        em.flush();
    }

    private <T extends BaseEntity> void saveInternal(T entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    public <T extends BaseEntity> List<T> findAllNoFetch(EntityPathBase<T> entityPath) {
        JPQLQuery query = buildQuery(entityPath);
        return query.list(entityPath);
    }

    protected <T extends BaseEntity> JPQLQuery buildQuery(EntityPathBase<T> entityPath) {
        JPQLQuery query = new JPAQuery(em);
        query = query.from(entityPath);
        return query;
    }

    public <T extends BaseEntity> T findOneNoFetch(EntityPathBase<T> entityPath, Long id) {
        JPQLQuery query = buildQuery(entityPath);
        NumberPath<Long> longNumberPath = getLongNumberPath(entityPath);
        query = query.where(longNumberPath.eq(id));
        return query.singleResult(entityPath);
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
}

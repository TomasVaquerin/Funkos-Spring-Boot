package dev.tomas.common;

import dev.tomas.funkos.models.Funko;

import java.util.List;

public interface Repository<T, I> {

    T save(T entity);

    T findById(I id);

    List<T> findAll();

    T deleteById(I id);

    T update(I id, T f);
}

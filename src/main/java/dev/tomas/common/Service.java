package dev.tomas.common;

import java.util.List;

public interface Service<T, I, D> {
    T save(D dto);

    T findById(I id);

    List<T> findAll();

    T deleteById(I id);
}
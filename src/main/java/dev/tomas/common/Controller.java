package dev.tomas.common;

import dev.tomas.funkos.dto.FunkoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Controller<T, I> {
    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(I id);

    ResponseEntity<T> create(FunkoDto entity);

    ResponseEntity<T> update(I id, FunkoDto entity);

    ResponseEntity<Void> delete(I id);
}
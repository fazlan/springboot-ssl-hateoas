package org.fazlan.hateoas.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

public abstract class AbstractRepo<R, E> {
    private final Map<Integer, E> DB = new HashMap<>();
    protected static final AtomicInteger COUNTER = new AtomicInteger();

    protected abstract E toEntity(R resource);

    public Integer create(R resource) {
        DB.put(COUNTER.incrementAndGet(), toEntity(resource));
        return COUNTER.get();
    }

    public E get(Integer id) {
        return DB.get(id);
    }

    public List<E> list() {
        return DB.values().stream().collect(toList());
    }
}

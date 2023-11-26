package com.facol.bookstore.patterns.mapper;

public interface Mapper<T, U> {
    U map(T t, U u);
}

package ru.spbstu.knowledgetest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

    @Value("${request.default.page}")
    int DEFAULT_PAGE;
    @Value("${request.default.size}")
    int DEFAULT_SIZE;

    public Pageable of(Integer page, Integer size) {
        return PageRequest.of(
                page != null ? page : DEFAULT_PAGE,
                size != null ? size : DEFAULT_SIZE
        );
    }

    public Pageable of(Integer page, Integer size, Sort sort) {
        return PageRequest.of(
                page != null ? page : DEFAULT_PAGE,
                size != null ? size : DEFAULT_SIZE,
                sort
        );
    }
}

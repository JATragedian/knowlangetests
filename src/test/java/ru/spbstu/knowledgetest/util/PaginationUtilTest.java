package ru.spbstu.knowledgetest.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PaginationUtilTest {

    @Autowired
    PaginationUtil paginationUtil;

    @Test
    void shouldCreateDefaultPagination() {
        Pageable pageable = paginationUtil.of(null, null);

        assertEquals(0, pageable.getPageNumber());
        assertEquals(25, pageable.getPageSize());
    }
}
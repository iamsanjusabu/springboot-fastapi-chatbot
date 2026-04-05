package com.sanjusabu.springbootfastapi.repository;

import com.sanjusabu.springbootfastapi.entity.VectorTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VectorTableRepository extends JpaRepository<VectorTable, Long> {
    @Query( value =
            "SELECT content FROM documents " +
            "WHERE 1 - (embedding <=> CAST(:embedding AS VECTOR)) >= 0.30 " +
            "ORDER BY (embedding <=> CAST(:embedding AS VECTOR)) " +
            "LIMIT 5", nativeQuery = true)
    public List<String> closest5Matches(@Param("embedding") float[] embedding);
}

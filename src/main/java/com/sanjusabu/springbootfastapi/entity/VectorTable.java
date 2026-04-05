package com.sanjusabu.springbootfastapi.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Setter
@Getter
@Entity
@Table(name = "documents")
public class VectorTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "embedding", columnDefinition = "VECTOR(384)")
    @JdbcTypeCode(SqlTypes.VECTOR)
    private float[] embedding;
}

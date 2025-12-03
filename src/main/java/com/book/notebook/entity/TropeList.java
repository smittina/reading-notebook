package com.book.notebook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="trope_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TropeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="trope", nullable = false)
    private String trope;
}

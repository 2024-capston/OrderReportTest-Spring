package com.sejong.test.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column()
    private String username;

    @Column()
    private String email;

    @OneToMany(mappedBy = "user")
    private List<ProductSearchHistory> productSearchHistoryList;
}

package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "search_chart")
@Getter
@Setter
public class SearchChart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String searchQuery;
}

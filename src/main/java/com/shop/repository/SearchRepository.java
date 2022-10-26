package com.shop.repository;

import com.shop.entity.SearchChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchRepository extends JpaRepository<SearchChart, Long> {

    @Query(nativeQuery = true, value = "select sc.search_query, count(sc.search_query) as count from search_chart sc group by sc.search_query having count(sc.search_query) >= 0 order by count(sc.search_query) desc limit 10")
    List<Object[]> countSearchChart();

    //@Query("select sc.searchQuery, count(sc.searchQuery) as count from SearchChart sc group by sc.searchQuery having count(sc.searchQuery) > 1 order by count(sc.searchQuery) desc")
    //List<SearchChart> countSearchChart();

    //@Query("select sc.searchQuery, count(sc.searchQuery) from SearchChart sc group by sc.searchQuery order by count(sc.searchQuery) desc")
    //List<Object[]> countSearchChart();

    /*@Query("select sc.searchQuery from SearchChart sc")
    List<Object[]> countSearchChart();*/

    /*@Query("select count(sc.searchQuery) from SearchChart sc")
    List<Object[]> countSearchChart();*/

    // (list.get(0)[0]
}

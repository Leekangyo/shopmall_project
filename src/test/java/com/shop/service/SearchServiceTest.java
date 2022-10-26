package com.shop.service;

import com.shop.dto.SearchChartDto;
import com.shop.entity.SearchChart;
import com.shop.repository.SearchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class SearchServiceTest {

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SearchService searchService;

    public void searchDate(){

        for (int i = 0; i<8; i++){
            SearchChart searchChart = new SearchChart();
            searchChart.setSearchQuery("인텔리제이");
            searchRepository.save(searchChart);
        }
        for (int i = 0; i<9; i++){
            SearchChart searchChart = new SearchChart();
            searchChart.setSearchQuery("이클립스");
            searchRepository.save(searchChart);
        }
        for (int i = 0; i<10; i++){
            SearchChart searchChart = new SearchChart();
            searchChart.setSearchQuery("비주얼스튜디오");
            searchRepository.save(searchChart);
        }
        SearchChart sc = searchRepository.findById(1L)
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("출력 : "+sc.getId()+" "+sc.getSearchQuery());
        System.out.println();
    }

    @Test
    public void search(){
        searchDate();
        //List<SearchChartDto> searchChartDtoList = searchService.getSearchChart();

        /*List<Object[]> list = searchRepository.countSearchChart();
        for (Object[] search : list){
            System.out.println("출력: " + search[0].toString());
            System.out.println("출력: " + search[1].toString());
        }*/

        /*for (Object[] search : list){
            String st = (String) search[0];
            long lo = (long) search[1];
            System.out.println("출력 : " + st + " " + lo);
        }*/

        /*list.forEach(i ->{
            System.out.println("여기 : "+i.getId()+" "+i.getSearchQuery());
        });*/

        List<SearchChartDto> searchChartDtoList = searchService.getSearchChart();

        for (SearchChartDto searchChartDto : searchChartDtoList){
            System.out.println(searchChartDto.getSearchQuery());
            System.out.println(searchChartDto.getSearchQuantity());
        }
    }
}
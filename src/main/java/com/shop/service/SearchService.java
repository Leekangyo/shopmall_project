package com.shop.service;

import com.shop.dto.SearchChartDto;
import com.shop.entity.SearchChart;
import com.shop.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchService {

    private final SearchRepository searchRepository;

    public void saveSearch(String searchQuery){
        SearchChart searchChart = new SearchChart();
        searchChart.setSearchQuery(searchQuery);
        searchRepository.save(searchChart);
    }

    public List<SearchChartDto> getSearchChart(){
        List<SearchChartDto> searchChartDtoList = new ArrayList<>();

        List<Object[]> chart = searchRepository.countSearchChart();

        for (Object[] object : chart){
            SearchChartDto searchChartDto = new SearchChartDto();

            searchChartDto.setSearchQuery(object[0].toString());
            searchChartDto.setSearchQuantity(object[1].toString());

            searchChartDtoList.add(searchChartDto);
        }

        return searchChartDtoList;
    }





    /*public List<SearchChartDto> getSearchChart(){

        //List<SearchChart> searchChart = searchRepository.countSearchChart();
        List<SearchChartDto> searchChartDtoList = new ArrayList<>();

        for (Object object : searchChart){
            SearchChartDto searchChartDto = new SearchChartDto();
            searchChartDto.setSearchQuery(object[0]);
            searchChartDto.setSearchQuantity(object[1]);
            searchChartDtoList.add(searchChartDto);
        }
        for(int i=0; i<searchChart.size(); i++){
            searchChartDtoList.add(new SearchChartDto((String) searchChart.get(i)[0], (Long) searchChart.get(i)[1]));
        }

        return searchChartDtoList;
    }*/
}

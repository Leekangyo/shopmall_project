package com.shop.controller;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.dto.SearchChartDto;
import com.shop.service.ItemService;
import com.shop.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;
    private final SearchService searchService;

    @GetMapping(value="/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto,pageable);
        if (itemSearchDto.getSearchQuery() != ""){
            searchService.saveSearch(itemSearchDto.getSearchQuery());
        }
        List<SearchChartDto> searchChartDtoList = searchService.getSearchChart();
        //System.out.println(items.getNumber()); // 0
        //System.out.println(items.getTotalPages()); // 1
        //System.out.println(items.getTotalElements()); // 4
        model.addAttribute("items",items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        model.addAttribute("searchChart",searchChartDtoList);

        return "main";
    }



}
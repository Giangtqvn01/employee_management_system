package com.truonggiang.employee_management_system.model;

import org.springframework.data.domain.Page;

public class PagedResponeMapper {
    public static PagedResponse mapper(Page page){
        PagedResponse pagedResponse = new PagedResponse<>();
        pagedResponse.setData(page.getContent());
        pagedResponse.setPage(page.getNumber()+1); //+1 do page tinh tu 0
        pagedResponse.setSize(page.getSize());
        pagedResponse.setTotalPages(page.getTotalPages());
        pagedResponse.setTotalElements(page.getTotalElements());
        pagedResponse.setLast(page.isLast());
        return  pagedResponse;
    }
}
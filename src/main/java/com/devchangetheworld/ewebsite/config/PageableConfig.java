package com.devchangetheworld.ewebsite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@Configuration
//@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class PageableConfig implements WebMvcConfigurer {

    @Value("${paging.default-page-size:10}")
    private int defaultPageSize;

    @Value("${paging.default-page-number:0}")
    private int defaultPageNumber;

    @Value("${paging.default-sort-by:'id'}")
    private String sortBy;

    @Value("${paging.default-sort-dir:'DESC'}")
    private String sortDir;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setPageParameterName("page");
        resolver.setSizeParameterName("size");
        resolver.setOneIndexedParameters(false);

        Sort defaultSort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        resolver.setFallbackPageable(PageRequest.of(defaultPageNumber, defaultPageSize, defaultSort));
        argumentResolvers.add(resolver);
    }
}

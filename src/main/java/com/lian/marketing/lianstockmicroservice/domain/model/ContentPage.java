package com.lian.marketing.lianstockmicroservice.domain.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentPage<T> {
    private int totalPage;
    private long totalElements;
    private int pageNumber;
    private int pageSize;
    private boolean isFirst;
    private boolean isLast;
    private List<T> content;

}

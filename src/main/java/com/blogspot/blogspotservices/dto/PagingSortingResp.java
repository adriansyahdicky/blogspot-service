package com.blogspot.blogspotservices.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PagingSortingResp {

    private OutputSchema outputSchema;

    @Builder(builderMethodName = "responseBuilder")
    public PagingSortingResp(
            List<?> records,
            Integer currentPage,
            Integer currentSize,
            Integer totalPage,
            Long totalSize,
            Object ...args
    ) {
        outputSchema = new OutputSchema(records, currentPage, currentSize, totalPage, totalSize);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutputSchema {

        private List<?> records;

        private Integer currentPage;

        private Integer currentSize;

        private Integer totalPage;

        private Long totalSize;

    }

}
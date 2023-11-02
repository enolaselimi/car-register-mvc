package com.detyra.mvc.domain.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    private String field;
    private String value;
    private String operator;
    private String sort;
}

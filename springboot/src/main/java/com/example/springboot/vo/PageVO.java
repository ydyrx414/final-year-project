package com.example.springboot.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PageVO<T> {
    private List<T> result;
    private long total;

    public static <T> PageVO<T> from(IPage<T> page) {
        return new PageVO<>(page.getRecords(), page.getTotal());
    }

    public static <S,T> PageVO<T> from(IPage<S> page,Converter<S,T> converter) {
        List<T> c = page.getRecords().stream().map(converter::convert)
                .collect(Collectors.toList());
        return new PageVO<>(c, page.getTotal());
    }


    @FunctionalInterface
    public interface Converter<S,T>{
        T convert(S source);
    }
}

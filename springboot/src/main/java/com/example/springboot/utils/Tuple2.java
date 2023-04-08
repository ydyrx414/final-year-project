package com.example.springboot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tuple2<A, B> {
    private A a;
    private B b;
}

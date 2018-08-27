package com.sjkjcrm.util.layui;

import lombok.Data;

import java.util.List;

/**
 * layui分页需要的page对象
 * @param <T>
 */
@Data
public class LayPage<T> {

    private int code;

    private String msg;

    private int count;

    private List<T> data;
}

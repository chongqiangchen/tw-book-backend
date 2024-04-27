package com.tw.book.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 编码描述
     */
    private String msg;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 返回成功结果对象
     *
     * @param data 可选，可以不传
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return Result.<T>builder().code(0).msg("success").data(data).build();
    }
    public static <T> Result<T> success() {
        return Result.<T>builder().code(0).msg("success").data(null).build();
    }
}
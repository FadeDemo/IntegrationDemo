package org.fade.demo.integrationdemo.wangeditor.controller;

import lombok.Data;

/**
 * @author fade
 * @date 2021/11/02
 */
@Data
public class ResultContext<T> {

    private int code;

    private T data;

    public static <T> ResultContext<T> success(T data) {
        ResultContext<T> result = new ResultContext<>();
        result.setCode(200);
        result.setData(data);
        return result;
    }

    public static <T> ResultContext<T> success() {
        return success(null);
    }

}

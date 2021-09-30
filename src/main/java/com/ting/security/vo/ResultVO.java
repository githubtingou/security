package com.ting.security.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统一返参格式
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Data
@NoArgsConstructor
public class ResultVO<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

}

package com.ting.security.utils;

import com.ting.security.common.ResultEnum;
import com.ting.security.vo.ResultVO;

/**
 * 统一参数封装
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
public class ResultUtils<T> {

    public static <T> ResultVO<T> success(T data) {
        return buildResultByEnum(ResultEnum.SUCCESS, data);

    }

    public static ResultVO<Object> success() {
        return buildResultByEnum(ResultEnum.SUCCESS);

    }

    public static <T> ResultVO<T> error(String message) {
        return buildResult(ResultEnum.SERVER_ERROR, message);

    }

    public static ResultVO<Object> buildResultByEnum(ResultEnum resultEnum) {
        return buildResultByEnum(resultEnum, null);

    }

    public static <T> ResultVO<T> buildResultByEnum(ResultEnum resultEnum, T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setSuccess(resultEnum.isSuccess());
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());
        resultVO.setData(data);
        return resultVO;

    }

    public static <T> ResultVO<T> buildResult(ResultEnum resultEnum, String message) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setSuccess(resultEnum.isSuccess());
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(message);
        resultVO.setData(null);
        return resultVO;
    }

}

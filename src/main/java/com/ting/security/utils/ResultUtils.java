package com.ting.security.utils;

import com.alibaba.fastjson.JSON;
import com.ting.security.common.ResultEnum;
import com.ting.security.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 统一参数封装
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Slf4j
public final class ResultUtils {

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


    public static <T> void buildResultByResponse(ResultVO<T> resultVO, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(resultVO));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error("response写入失败", e);
        }
    }
}

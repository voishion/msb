package com.meishubao.graphqlstudy.common.response;

/**
 * @author lilu
 */
public class ResponseBuilder {

    public static Result ok(int code, String msg) {
        return Result.builder().code(code).msg(msg).build();
    }

}

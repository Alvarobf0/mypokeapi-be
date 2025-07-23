package com.alea.mypokeapi_be.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Alvarobf0, 22/07/2025
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstant {
    public static final String OK_CODE = "200";
    public static final String OK_REASON = "OK";

    public static final String BAD_REQUEST_CODE = "400";
    public static final String BAD_REQUEST_REASON = "Bad Request";

    public static final String INTERNAL_SERVER_ERROR_CODE = "500";
    public static final String INTERNAL_SERVER_ERROR_REASON = "Internal Server Error";
}

package ir.snappay.walletservice.util;

import ir.snappay.walletservice.dto.ResponseObject;

public class ResponseUtil {

    public static ResponseObject createResponse(Object res){
        return ResponseObject.builder().response(res).build();
    }
}

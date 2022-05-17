package com.meishubao.sample.exception;

import com.meishubao.sample.model.R;
import com.meishubao.sample.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 异常统一返回报文格式化
 *
 * @author geekren
 */
@RestController
@Slf4j
public class GlobalErrorController extends AbstractErrorController {

    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "error", produces = MediaType.APPLICATION_JSON_VALUE)
    public R<?> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            log.error(JsonUtils.toJson(new ResponseEntity<>(status)));
            return R.create(status.value(), status.getReasonPhrase());
        }
        Map<String, Object> body = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        log.error(JsonUtils.toJson(new ResponseEntity<>(body, status)));

        return R.failure(status.value(), status.getReasonPhrase());
    }

}

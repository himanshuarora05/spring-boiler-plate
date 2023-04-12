package com.company.web.advice;


import com.company.core.serdes.GsonSerializer;
import com.company.services.enums.ResponseCodeHttpStatusEnum;
import com.company.services.response.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a custom implementation of {@link ResponseEntityExceptionHandler}. We
 * intercept the calls of all controller here and handle cases like bad request,
 * missing params, missing headers, incorrect HttpMethod and provide appropriate
 * custom response to the caller.
 * <p>
 * This works only for the user module.
 *
 * @author mukulbansal
 */
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRestExceptionHandler.class);

    private static final GsonSerializer GSON_SERIALIZER = GsonSerializer.getInstance();

    /**
     * Handle invalid {@link MediaType}
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Handle missing {@link RequestHeader}
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.BAD_REQUEST);
    }

    /**
     * Handle missing {@link RequestParam}
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.BAD_REQUEST);
    }

    /**
     * Handle bad request from spring.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.BAD_REQUEST);
    }

    /**
     * Handle any uncaught exception in the controller.
     */
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> uncaughtContollerException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.SOMETHING_WRONG);
    }

    /**
     * Customize the response for TypeMismatchException.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.REQUEST_METHOD_NOT_SUPPORTED);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponse(request, ex, ResponseCodeHttpStatusEnum.NOT_FOUND);
    }

    private ResponseEntity<Object> getResponse(HttpServletRequest request, Exception ex, ResponseCodeHttpStatusEnum responseCodeHttpStatusEnum) {
        return getResponse(request.getRequestURI(), ex, responseCodeHttpStatusEnum);
    }

    private ResponseEntity<Object> getResponse(WebRequest request, Exception ex, ResponseCodeHttpStatusEnum responseCodeHttpStatusEnum) {
        return getResponse(((ServletWebRequest) request).getRequest().getRequestURI(), ex, responseCodeHttpStatusEnum);
    }

    private ResponseEntity<Object> getResponse(String requestUri, Exception ex, ResponseCodeHttpStatusEnum responseCodeHttpStatusEnum) {
        LOG.error(ex.getMessage() + " from the controller {}.", requestUri);
        ServiceResponse serviceResponse = ServiceResponse.getServiceResponse(responseCodeHttpStatusEnum);
        HttpHeaders     httpHeaders     = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<Object>(GSON_SERIALIZER.toJson(serviceResponse), httpHeaders,
                responseCodeHttpStatusEnum.getHttpStatus());
    }
}

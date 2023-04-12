package com.company.services.http.wrappers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @link https://howtodoinjava.com/servlets/httpservletrequestwrapper-example-read-request-body/
 * @author mukulbansal
 */
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private String body = StringUtils.EMPTY;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        /*
         * Special handling for multi-part data
         */
        if(getNullSafeHeaderValue(request, HttpHeaders.CONTENT_TYPE).contains(MediaType.MULTIPART_FORM_DATA_VALUE)){
            return;
        }

        StringBuilder  stringBuilder  = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int    bytesRead  = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        /*
         * Special handling for multi-part data
         */
        if(getNullSafeHeaderValue(this, HttpHeaders.CONTENT_TYPE).contains(MediaType.MULTIPART_FORM_DATA_VALUE)){
            return super.getInputStream();
        }

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    private String getNullSafeHeaderValue(HttpServletRequest request, String key){
        return StringUtils.isBlank(request.getHeader(key))
                ? StringUtils.EMPTY
                : request.getHeader(key);
    }

    public String getRawBody() {
        return this.body;
    }


}

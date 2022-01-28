package com.meishubao.redis.web.interceptor;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * 解决多次读取request中的body内容的问题
 *
 * @author lilu
 */
@Log4j2
public class BodyRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public BodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        //body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = reader.read(charBuffer)) > 0) {
                    builder.append(charBuffer, 0, bytesRead);
                }
            } else {
                builder.append("");
            }
        } catch (IOException ignored) {
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        body = builder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
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
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

}
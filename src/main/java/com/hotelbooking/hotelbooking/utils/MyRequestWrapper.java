package com.hotelbooking.hotelbooking.utils;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;

public class MyRequestWrapper extends HttpServletRequestWrapper {
    private byte[] cachedBody;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public MyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream reqInputStream = request.getInputStream();
        this.cachedBody = StreamUtils.copyToByteArray(reqInputStream);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new MyServletInputStreamWrapper(this.cachedBody);
    }
}

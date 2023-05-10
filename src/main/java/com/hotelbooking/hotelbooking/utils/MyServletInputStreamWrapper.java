package com.hotelbooking.hotelbooking.utils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyServletInputStreamWrapper extends ServletInputStream {
    private InputStream cachedBodyInputStream;

    public MyServletInputStreamWrapper(byte[] cachedBody) {
        this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
    }

    /**
     * Has the end of this InputStream been reached?
     *
     * @return <code>true</code> if all the data has been read from the stream,
     * else <code>false</code>
     * @since Servlet 3.1
     */
    @Override
    public boolean isFinished() {
        try {
            return this.cachedBodyInputStream.available() == 0;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Can data be read from this InputStream without blocking?
     * Returns  If this method is called and returns false, the container will
     * invoke {@link ReadListener#onDataAvailable()} when data is available.
     *
     * @return <code>true</code> if data can be read without blocking, else
     * <code>false</code>
     * @since Servlet 3.1
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * Sets the {@link javax.servlet.ReadListener} for this {@link javax.servlet.ServletInputStream} and
     * thereby switches to non-blocking IO. It is only valid to switch to
     * non-blocking IO within async processing or HTTP upgrade processing.
     *
     * @param listener The non-blocking IO read listener
     * @throws IllegalStateException If this method is called if neither
     *                               async nor HTTP upgrade is in progress or
     *                               if the {@link javax.servlet.ReadListener} has already
     *                               been set
     * @throws NullPointerException  If listener is null
     * @since Servlet 3.1
     */
    @Override
    public void setReadListener(ReadListener listener) {

    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an <code>int</code> in the range <code>0</code> to
     * <code>255</code>. If no byte is available because the end of the stream
     * has been reached, the value <code>-1</code> is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or <code>-1</code> if the end of the
     * stream is reached.
     * @throws java.io.IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return this.cachedBodyInputStream.read();
    }
}

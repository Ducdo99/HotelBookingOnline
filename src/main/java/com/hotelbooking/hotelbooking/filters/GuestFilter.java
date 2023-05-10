package com.hotelbooking.hotelbooking.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = "/guest/*")
public class GuestFilter implements Filter {
    @Autowired
    private Utility utility;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        HttpServletResponse resObj = (HttpServletResponse) response;
        String pageParam = reqObj.getParameter("page".trim());
        String pageSizeParam = reqObj.getParameter("pageSize".trim());
        Map<String, String> errors = new HashMap<>();
        String uri = reqObj.getRequestURI();
        try {
            if (uri.trim().equalsIgnoreCase("/guest/register".trim())
                    | uri.trim().equalsIgnoreCase("/guest/login".trim())) {
                chain.doFilter(request, response);
            } else {
                if (pageParam != null) {

                    if (!utility.validateInputString(pageParam.trim(), MyConstantVariables.NUMBER_FORMAT.trim())) {
                        errors.put("page error", "Invalid page format");
                    } else if (Integer.parseInt(pageParam.trim()) <= 0) {
                        errors.put("page error", "Invalid page number");
                    }
                }
                if (pageSizeParam != null) {

                    if (!utility.validateInputString(pageSizeParam.trim(), MyConstantVariables.NUMBER_FORMAT.trim())) {
                        errors.put("page size error", "Invalid page size format");
                    } else if (Integer.parseInt(pageSizeParam.trim()) <= 0) {
                        errors.put("page size error", "Invalid page size number");
                    }
                }
                if (errors.size() > 0) {
                    resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    chain.doFilter(request, response);
                }
            }
        } catch (IOException ex) {
            resObj.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errors.put("err_message", ex.getMessage());
        } catch (Exception ex) {
            resObj.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errors.put("err_message", ex.getMessage());
        } finally {
            if (errors.size() > 0) {
                resObj.setContentType("application/json");
                objectMapper.writeValue(resObj.getOutputStream(), errors);
            }
        }
    }
}

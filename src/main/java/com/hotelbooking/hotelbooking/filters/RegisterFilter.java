package com.hotelbooking.hotelbooking.filters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.hotelbooking.inputs.AccountRegistrationRequest;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.MyRequestWrapper;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/guest/register"})
public class RegisterFilter implements Filter {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Utility utility;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        HttpServletResponse resObj = (HttpServletResponse) response;
        Map<String, String> errors = new HashMap<>();
        try {
            // create a HttpServletRequest wrapper class
            MyRequestWrapper myRequestWrapper = new MyRequestWrapper(reqObj);

            // Get data from RequestBody
            // Get the input stream data from the wrapper
            ServletInputStream cachedServletInputStream = myRequestWrapper.getInputStream();
            // Convert input stream data to an object
            AccountRegistrationRequest accountRegistrationRequest =
                    objectMapper.readValue(cachedServletInputStream, AccountRegistrationRequest.class);
            if (accountRegistrationRequest.getEmail() == null
                    | !utility.validateInputString(
                    accountRegistrationRequest.getEmail().trim(), MyConstantVariables.EMAIL_FORMAT.trim())) {
                errors.put("email error", "Invalid email format");
            }
            if (accountRegistrationRequest.getPwd() == null
                    | !utility.validateInputString(
                    accountRegistrationRequest.getPwd().trim(), MyConstantVariables.PASSWORD_FORMAT.trim())) {
                errors.put("password error", "Invalid password format");
            }
            if (accountRegistrationRequest.getFullName() == null
                    | !utility.validateInputString(
                    accountRegistrationRequest.getFullName().trim(), MyConstantVariables.FULL_NAME_FORMAT.trim())) {
                errors.put("full name error", "Invalid full name format");
            }
            if (accountRegistrationRequest.getPhone() == null
                    | !utility.validateInputString(
                    accountRegistrationRequest.getPhone().trim(), MyConstantVariables.CELLPHONE_FORMAT.trim())) {
                errors.put("phone error", "Invalid phone format");
            }
            if (accountRegistrationRequest.getAddress() != null) {
                boolean isValidAddress = utility.validateInputString(
                        accountRegistrationRequest.getAddress().trim(), MyConstantVariables.ADDRESS_FORMAT.trim());
                if (!isValidAddress) {
                    errors.put("address error", "Invalid address format");
                }
            }
            if (accountRegistrationRequest.getIdCard() != null) {
                boolean isValidIdCard = utility.validateInputString(
                        accountRegistrationRequest.getIdCard().trim(), MyConstantVariables.ID_CARD_FORMAT.trim());
                if (!isValidIdCard) {
                    errors.put("id card error", "Invalid id card format");
                }
            }
            if (errors.size() > 0) {
                resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                chain.doFilter(myRequestWrapper, response);
            }
        } catch (JsonParseException ex) {
            resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errors.put("err_message", ex.getMessage());
        } catch (JsonMappingException ex) {
            resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errors.put("err_message", ex.getMessage());
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

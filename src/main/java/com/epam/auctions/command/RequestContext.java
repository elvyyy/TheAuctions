package com.epam.auctions.command;

import lombok.Getter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Getter
public class RequestContext {

    private Map<String, Object> requestAttributes;

    private Map<String, String[]> requestParameters;

    private Map<String, Object> sessionAttributes;

    private Cookie[] cookies;

    private HttpSession session;

    public static class Builder {
        private RequestContext context;

        public Builder() {
            context = new RequestContext();
        }

        public Builder parseHttpServletRequest(HttpServletRequest request) {
            context.requestParameters = request.getParameterMap();
            context.cookies = request.getCookies();
            context.requestAttributes = populateAttributes(request);
            context.sessionAttributes = populateSessionAttributes(request);
            context.session = request.getSession();
            return this;
        }

        private Map<String, Object> populateSessionAttributes(HttpServletRequest request) {
            Map<String, Object> result = new HashMap<>();
            HttpSession session = request.getSession(true);
            Enumeration<String> sessionAttributeNames = session.getAttributeNames();
            while (sessionAttributeNames.hasMoreElements()) {
                String sessionAttributeName = sessionAttributeNames.nextElement();
                result.put(sessionAttributeName, session.getAttribute(sessionAttributeName));
            }
            return result;
        }

        private Map<String, Object> populateAttributes(HttpServletRequest request) {
            HashMap<String, Object> result = new HashMap<>();
            Enumeration<String> attributeNames = request.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                result.put(attributeName, request.getAttribute(attributeName));
            }
            return result;
        }

        public RequestContext build() {
            if (context != null) {
                return context;
            }
            return new RequestContext();
        }
    }

    public String getParameter(String name) {
        String[] names = requestParameters.get(name);
        if (names != null && names.length != 0) {
            return names[0];
        }
        return null;
    }
}

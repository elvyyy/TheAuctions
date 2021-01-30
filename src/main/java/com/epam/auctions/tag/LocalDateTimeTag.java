package com.epam.auctions.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTag extends TagSupport {
    private LocalDateTime value;
    private String format;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String date;
        if (value == null || format == null) {
            date = "";
        } else {
            if (format.equals("short")) {
                date = value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            } else {
                date = value.format(DateTimeFormatter.ofPattern(format));
            }
        }
        try {
            out.write(date);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return SKIP_BODY;
    }

    public void setValue(LocalDateTime value) {
        this.value = value;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

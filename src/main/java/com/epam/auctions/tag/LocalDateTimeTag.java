package com.epam.auctions.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Local date time tag.
 */
public class LocalDateTimeTag extends TagSupport {
    /**
     * time format
     */
    private String format;
    /**
     * {@code LocalDateTime} value
     */
    private LocalDateTime value;

    /**
     * Formats {@code LocalDateTime}
     *
     * @return
     * @throws JspException
     */
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
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    /**
     * Sets format.
     *
     * @param format the format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(LocalDateTime value) {
        this.value = value;
    }
}

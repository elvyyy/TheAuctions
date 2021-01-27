package com.epam.auctions.validator.impl;

import com.epam.auctions.entity.Entity;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.FormValidationException;
import com.epam.auctions.util.FileUtils;
import com.epam.auctions.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LotFormCreateValidator implements Validator {
    private static final Logger LOG = LoggerFactory.getLogger(LotFormCreateValidator.class);

    private static final int MIN_30 = 30;
    private static final int HOUR_1 = 60;
    private static final int HOUR_2 = 2 * 60;
    private static final int HOUR_24 = 24 * 60;
    private static final int HOUR_48 = 48 * 60;

    @Override
    public Entity<? extends Number> validate(HttpServletRequest req) throws FormValidationException {
        Lot lot = new Lot();
        final String title = req.getParameter("lot-title");
        if (title == null) {
            throw new FormValidationException("title cannot be null");
        }
        lot.setDescription(title);
        final int time = getTimeDurationInMinutes(req);
        lot.setDurationInMinutes(time);
        final Part photo = getImagePart(req);
        String relativePath = getImageRelativePath(photo, req);
        String absolutePath = req.getServletContext().getRealPath("/") + relativePath;
//        String absolutePath = "/home/vlad/vlad/jwd-auctions/src/main/webapp/" + relativePath;
        lot.setPhotoPath(relativePath);
        try {
            FileUtils.writeStreamTo(photo.getInputStream(), absolutePath);
            FileUtils.writeStreamTo(photo.getInputStream(), "/home/vlad/vlad/jwd-auctions/src/main/webapp/" + relativePath);
        } catch (IOException e) {
            throw new FormValidationException("Cannot write image to stream");
        }

        lot.setLotStatus(LotStatus.CREATED);
        User user = (User) req.getSession().getAttribute("user");
        lot.setCreatedBy(user.getId());
        lot.setCreatedAt(LocalDateTime.now());
        final String minBidStr = req.getParameter("minimal-bid");
        final String replace = minBidStr.replace(',', '.');
        BigDecimal minBid = new BigDecimal(replace);
        lot.setMinimalBid(minBid);
        LOG.debug("{}", lot);
        return lot;
    }

    private int getTimeDurationInMinutes(HttpServletRequest req) {
        Integer choice = Integer.valueOf(req.getParameter("lot-time-duration"));
        switch (choice) {
            case 1:
                return MIN_30;
            case 2:
                return HOUR_1;
            case 3:
                return HOUR_2;
            case 4:
                return HOUR_24;
            case 5:
                return HOUR_48;
            default:
                throw new FormValidationException("No such choice exception #" + choice);
        }
    }

    private Part getImagePart(HttpServletRequest req) {
        try {
            return req.getPart("lot-photo");
        } catch (IOException | ServletException e) {
            throw new FormValidationException("Can't get image from request");
        }
    }

    private String getImageRelativePath(Part part, HttpServletRequest req) {
        final String mimeType = req.getServletContext().getMimeType(part.getSubmittedFileName());
        if (mimeType.startsWith("image/")) {
            final String filename = FileUtils.generateFilenameBasedOnUUID(part.getSubmittedFileName());
            final String relativePath = "static/media/img/" + filename;
            return relativePath;
        } else {
            throw new FormValidationException("File is not an image");
        }
    }
}

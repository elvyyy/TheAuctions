package com.epam.auctions.validator.impl;

import com.epam.auctions.entity.Entity;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.LotStatus;
import com.epam.auctions.entity.User;
import com.epam.auctions.exception.FormValidationException;
import com.epam.auctions.service.LotService;
import com.epam.auctions.service.impl.LotServiceImpl;
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

/**
 * The type Lot form create validator.
 */
public class LotFormCreateValidator implements Validator {
    private static final Logger LOG = LoggerFactory.getLogger(LotFormCreateValidator.class);

    private final LotService lotService = LotServiceImpl.INSTANCE;

    /**
     * Validates {@code HttpServletRequest}
     *
     * @param req
     * @return {@code Lot}
     * @throws FormValidationException if validation failed
     */
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

    /**
     * Gets time duration in minutes.
     *
     * @param req the req
     * @return the time duration in minutes
     */
    public int getTimeDurationInMinutes(HttpServletRequest req) {
        Integer choice = Integer.valueOf(req.getParameter("lot-time-duration"));
        return lotService.getDuration(choice);
    }

    /**
     * Gets image from {@code HttpServletRequest}
     *
     * @param req
     * @return
     */
    private Part getImagePart(HttpServletRequest req) {
        try {
            return req.getPart("lot-photo");
        } catch (IOException | ServletException e) {
            throw new FormValidationException("Can't get image from request");
        }
    }

    /**
     * Gets image relative path
     *
     * @param part
     * @param req
     * @return
     */
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

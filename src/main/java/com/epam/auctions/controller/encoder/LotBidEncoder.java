package com.epam.auctions.controller.encoder;

import com.epam.auctions.entity.LotBid;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * The type Lot bid encoder.
 */
public class LotBidEncoder implements Encoder.Text<LotBid> {
    private static final Gson gson = new Gson();

    /**
     * Encodes
     *
     * @param object
     * @return Encoded string
     */
    @Override
    public String encode(LotBid object) throws EncodeException {
        return gson.toJson(object);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}

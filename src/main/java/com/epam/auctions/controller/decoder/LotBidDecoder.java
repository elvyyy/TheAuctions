package com.epam.auctions.controller.decoder;

import com.epam.auctions.entity.LotBid;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * The type Lot bid decoder.
 */
public class LotBidDecoder implements Decoder.Text<LotBid> {
    private static final Gson gson = new Gson();

    /**
     * Decodes input string into {@link LotBid} representation
     *
     * @param s
     * @return {@link LotBid} object
     * @throws DecodeException if cannot parse input string
     */
    @Override
    public LotBid decode(String s) throws DecodeException {
        return gson.fromJson(s, LotBid.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}

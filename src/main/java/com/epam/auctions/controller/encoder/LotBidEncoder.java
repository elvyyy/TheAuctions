package com.epam.auctions.controller.encoder;

import com.epam.auctions.entity.LotBid;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class LotBidEncoder implements Encoder.Text<LotBid> {
    private static Gson gson = new Gson();
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

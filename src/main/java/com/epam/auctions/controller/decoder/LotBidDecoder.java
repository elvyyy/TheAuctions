package com.epam.auctions.controller.decoder;

import com.epam.auctions.entity.LotBid;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class LotBidDecoder implements Decoder.Text<LotBid> {
    private static Gson gson = new Gson();

    @Override
    public LotBid decode(String s) throws DecodeException {
        return gson.fromJson(s, LotBid.class);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }
}

package com.epam.auctions.controller;

import com.epam.auctions.controller.decoder.LotBidDecoder;
import com.epam.auctions.controller.encoder.LotBidEncoder;
import com.epam.auctions.entity.LotBid;
import com.epam.auctions.service.LotService;
import com.epam.auctions.service.impl.LotServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Lot controller.
 */
@ServerEndpoint(value = "/lots/{lotId}", encoders = {LotBidEncoder.class}, decoders = {LotBidDecoder.class})
public class LotController {
    private static final Logger LOG = LoggerFactory.getLogger(LotController.class);
    private static final Map<Integer, List<Session>> sessions = new ConcurrentHashMap<>();
    private final LotService lotService = LotServiceImpl.INSTANCE;
    private Session session;

    /**
     * Sends message.
     *
     * @param lotId  the lot id
     * @param lotBid the lot bid
     */
    public static void sendMessage(int lotId, LotBid lotBid) {
        sessions.getOrDefault(lotId, new ArrayList<>())
                .forEach(session -> {
                    try {
                        session.getBasicRemote().sendObject(lotBid);
                    } catch (IOException | EncodeException e) {
                        LOG.error("Cannot send message", e);
                    }
                });
    }

    /**
     * On socket close handler.
     *
     * @param session the session
     * @param lotId   the lot id
     */
    @OnClose
    public void onClose(Session session, @PathParam("lotId") Integer lotId) {
        sessions.computeIfPresent(lotId, (key, val) -> {
            val.remove(session);
            return val;
        });
    }

    /**
     * On socket error handler.
     *
     * @param session   the session
     * @param throwable the throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.error("", throwable);
    }

    /**
     * On socket message handler.
     *
     * @param session the session
     * @param msg     the msg
     */
    @OnMessage
    public void onMessage(Session session, String msg) {

    }

    /**
     * On socket open handler.
     *
     * @param session the session
     * @param lotId   the lot id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("lotId") Integer lotId) {
        this.session = session;
        LOG.debug("OPENED SOCKET lotId {}", lotId);
        LOG.debug("Session id {}", session.getId());
        sessions.merge(lotId, new ArrayList<>(Arrays.asList(session)), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

}

package com.jamesye.prototypes.realtimeserver.modules.chat;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatModule {

    private final SocketIONamespace namespace;
    private static ConnectedDTO conectados = new ConnectedDTO(0);

    @Autowired
    public ChatModule(SocketIOServer server) {

        this.namespace = server.addNamespace("/chat");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener("chat", ChatDTO.class, onChatReceived());

    }

    private DataListener<ChatDTO> onChatReceived() {
        return (client, data, ackSender) -> {
            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
            namespace.getBroadcastOperations().sendEvent("chat", data);

        };
    }

    private ConnectListener onConnected() {

        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
            conectados.addConectados();
            System.out.printf("hay %02d%n personas yay", conectados.getConectados());
            namespace.getBroadcastOperations().sendEvent("conectados", conectados);
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
            conectados.deleteConectados();
            System.out.printf("se desconecto alguien, quedan %02d%n personas", conectados.getConectados());
        };
    }

    private static final Logger log = LoggerFactory.getLogger(ChatModule.class);
}

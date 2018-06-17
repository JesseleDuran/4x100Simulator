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

import java.util.ArrayList;
import java.util.UUID;

@Component
public class ChatModule {

    private final SocketIONamespace namespace;
    private static ConnectedDTO conectados = new ConnectedDTO(0, new ArrayList<UUID>());

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

            //add 1 to total of connected and add the client's session id
            conectados.addConectados();
            conectados.getId_conectados().add(client.getSessionId());

            sendHtmlBySizeOFClients();

            namespace.getBroadcastOperations().sendEvent("cantidadConectados", conectados);

        };
    }

    private void sendHtmlBySizeOFClients() {

        System.out.println(conectados.getId_conectados().size());

        switch(conectados.getId_conectados().size()) {
            case 1 :
                namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-medio.html");
                break;
            case 2 :
                namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-derecho.html");
                namespace.getClient(conectados.getId_conectados().get(1)).sendEvent("htmlType", "pista-izquierdo.html");
                break;
            case 3 :
                namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-izquierdo.html");
                namespace.getClient(conectados.getId_conectados().get(1)).sendEvent("htmlType", "pista-medio.html");
                namespace.getClient(conectados.getId_conectados().get(2)).sendEvent("htmlType", "pista-derecho.html");
                break;
            default :
                namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-medio.html");
        }

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());

            //remove 1 to total of connected and add the client's session id
            conectados.deleteConectados();
            conectados.getId_conectados().remove(client.getSessionId());

            sendHtmlBySizeOFClients();

            System.out.printf("se desconecto alguien, quedan %02d%n personas", conectados.getConectados());
        };
    }

    private static final Logger log = LoggerFactory.getLogger(ChatModule.class);
}

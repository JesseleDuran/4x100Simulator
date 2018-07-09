package com.jamesye.prototypes.realtimeserver.modules.chat;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.jamesye.prototypes.realtimeserver.modules.chat.DTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class SocketsServerModule {

    private final SocketIONamespace namespace;
    private static ConnectedDTO conectados = new ConnectedDTO(0, new ArrayList<UUID>());
    private static boolean finishFlag = true;

    @Autowired
    public SocketsServerModule(SocketIOServer server) {
        this.namespace = server.addNamespace("/chat");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener("chat", ChatDTO.class, onChatReceived());
        this.namespace.addEventListener("firstStart", FirstStartDTO.class, sendHtmlBySizeOFClients());
        this.namespace.addEventListener("getEquipos", listEquiposDTO.class, sendEquiposToClients());

        this.namespace.addEventListener("getRecordsFinish", listEquiposDTO.class, sendRecordsToClients());

        this.namespace.addEventListener("finishAnimation", FinishAnimationDTO.class, sendAnimationType());
        this.namespace.addEventListener("cerrarModal", CerrarModalDTO.class, sendCloseModal());

        this.namespace.addEventListener("sendEquipoFase1", listEquiposDTO.class, sendEquipo1());
        this.namespace.addEventListener("sendEquipoFase2", listEquiposDTO.class, sendEquipo2());
        this.namespace.addEventListener("sendEquipoFase3", listEquiposDTO.class, sendEquipo3());
        this.namespace.addEventListener("sendEquipoFase4", listEquiposDTO.class, sendEquipo4());

        this.namespace.addEventListener("finishedSimulator", String.class, finishedSimulator());
    }

    private DataListener<String> finishedSimulator() {
        return (client, data, ackSender) -> {
            if (finishFlag) {
                namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("masterSave", "eres el principal que va a guardar");
            }
            finishFlag = false;
        };
    }

    private DataListener<ChatDTO> onChatReceived() {
        return (client, data, ackSender) -> {
            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
            namespace.getBroadcastOperations().sendEvent("chat", data);

        };
    }

    private DataListener<FinishAnimationDTO> sendAnimationType() {
        return (client, data, ackSender) -> {
            namespace.getBroadcastOperations().sendEvent("animationType", data);

        };
    }

    private DataListener<CerrarModalDTO> sendCloseModal() {
        return (client, data, ackSender) -> {
            namespace.getBroadcastOperations().sendEvent("cerrarMyModal", data);

        };
    }

    private DataListener<listEquiposDTO> sendEquipo1() {
        return (client, data, ackSender) -> {

            namespace.getBroadcastOperations().sendEvent("getEquipoFase1", data);

        };
    }

    private DataListener<listEquiposDTO> sendEquipo2() {
        return (client, data, ackSender) -> {

            namespace.getBroadcastOperations().sendEvent("getEquipoFase2", data);

        };
    }

    private DataListener<listEquiposDTO> sendEquipo3() {
        return (client, data, ackSender) -> {

            namespace.getBroadcastOperations().sendEvent("getEquipoFase3", data);

        };
    }

    private DataListener<listEquiposDTO> sendEquipo4() {
        return (client, data, ackSender) -> {

            namespace.getBroadcastOperations().sendEvent("getEquipoFase4", data);

        };
    }

    private ConnectListener onConnected() {

        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());

            //add 1 to total of connected and add the client's session id
            conectados.addConectados();
            conectados.getId_conectados().add(client.getSessionId());

            namespace.getBroadcastOperations().sendEvent("cantidadConectados", conectados);

        };
    }

    private DataListener<FirstStartDTO> sendHtmlBySizeOFClients() {
        return (client, data, ackSender) -> {
            if (data.isStart()) {
                finishFlag = true;
                namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("master", "eres el principal");
                switch (conectados.getId_conectados().size()) {
                    case 1:
                        namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-medio-medio.html");
                        break;
                    case 2:
                        namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-derecho.html");
                        namespace.getClient(conectados.getId_conectados().get(1)).sendEvent("htmlType", "pista-medio-medio.html");
                        break;
                    case 3:
                        namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-derecho.html");
                        namespace.getClient(conectados.getId_conectados().get(1)).sendEvent("htmlType", "pista-medio-medio.html");
                        namespace.getClient(conectados.getId_conectados().get(2)).sendEvent("htmlType", "pista-izquierdo.html");
                        break;
                    default:
                        namespace.getClient(conectados.getId_conectados().get(0)).sendEvent("htmlType", "pista-medio-medio.html");
                }
            }
        };

    }

    private DataListener<listEquiposDTO> sendEquiposToClients() {
        return (client, data, ackSender) -> {
            namespace.getBroadcastOperations().sendEvent("equipoList", data);
        };

    }

    private DataListener<listEquiposDTO> sendRecordsToClients() {
        return (client, data, ackSender) -> {
            namespace.getBroadcastOperations().sendEvent("recordsList", data);
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());

            //remove 1 to total of connected and add the client's session id
            conectados.deleteConectados();
            conectados.getId_conectados().remove(client.getSessionId());

            System.out.printf("se desconecto alguien, quedan %02d%n personas", conectados.getConectados());
        };
    }

    private static final Logger log = LoggerFactory.getLogger(SocketsServerModule.class);
}

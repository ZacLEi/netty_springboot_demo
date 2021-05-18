package com.liulei.demo.netty_springboot_demo.ws;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.util.Map;

@ServerEndpoint(path = "/ws/api")
@Component
public class MyWebsocket {

//    @BeforeHandshake
//    public void handshake(Session session, HttpHeaders headers,
//                          @RequestParam String req,
//                          @RequestParam MultiValueMap reqMap,
//                          @PathVariable String arg,
//                          @PathVariable Map pathMap){
//        session.setSubprotocols("stomp");
//        if (!"ok".equals(req)){
//            System.out.println("Authentication failed!");
//            session.close();
//        }
//    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers){
        System.out.println("new Connection!");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection closed!");
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session,String message) {
        System.out.println(message);
        session.sendText(message);
    }

    @OnEvent
    public void onEvent(Session session,Object evt) {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()){
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("writer idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
            }
        }
    }
}

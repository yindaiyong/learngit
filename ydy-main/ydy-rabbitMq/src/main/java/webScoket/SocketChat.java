package webScoket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value= "/chat")
public class SocketChat {
	
	private static Logger LOGGER = Logger.getLogger(SocketChat.class);
	
	
	private static CopyOnWriteArraySet<SocketChat> webSocketSet = new CopyOnWriteArraySet<SocketChat>();
	private Session session;    //与某个客户端的连接会话，需要通过它来给客户端发送数据

	@OnOpen
	public void ToOpen(Session session,EndpointConfig config){
		LOGGER.info("开启websocket通道..."+session.getId());
		this.session = session;
		webSocketSet.add(this);
		String message = session+"登录！";
		broadcast(message);
	}

	@OnClose
	public void ToClose(Session session){
		System.out.println("关闭websocket通道..."+session.getId());
		 webSocketSet.remove(this); 
		 String message = session+"关闭！";
		 broadcast(message);
	}
	
	
	@OnMessage
	public void GetMessage(Session session ,String msg){
		System.out.println(msg);
		broadcast(session+msg);
	}
	
	/**
     * 广播消息
     * @param message
     */
    public void broadcast(String message){
        for(SocketChat chat: webSocketSet){
            try {
                chat.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    /**
     * 发生错误时调用
     * @param error
     */
    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }

}

package org.support.project.knowledge.websocket;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.support.project.common.bat.JobResult;
import org.support.project.common.log.Log;
import org.support.project.common.log.LogFactory;
import org.support.project.knowledge.bat.CreateExportDataBat;
import org.support.project.web.websocket.CallBatchEndpoint;
import org.support.project.web.websocket.EndpointConfigurator;

import net.arnx.jsonic.JSONException;

@ServerEndpoint(value = "/exporting", configurator = EndpointConfigurator.class)
public class DataExportEndpoint extends CallBatchEndpoint {
    /** ログ */
    private static final Log LOG = LogFactory.getLog(MethodHandles.lookup());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        if (super.isAdmin(session)) {
            call(session, CreateExportDataBat.class);
        }
    }

    @OnClose
    public void onClose(Session session) {
    }

    @OnMessage
    public void onMessage(String text, Session session) throws JSONException, IOException {
    }

    @OnError
    public void onError(Throwable t) {
        LOG.warn("websocket on error." + t.getClass().getName() + " : " + t.getMessage());
        if (LOG.isDebugEnabled()) {
            LOG.warn("websocket error -> ", t);
        }
    }

    @Override
    public void finishJob(JobResult result, Class<?> batch, List<Session> sessions) {
    }
}

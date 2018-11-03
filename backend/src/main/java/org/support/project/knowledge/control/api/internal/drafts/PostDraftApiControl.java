package org.support.project.knowledge.control.api.internal.drafts;

import java.lang.invoke.MethodHandles;

import org.support.project.common.log.Log;
import org.support.project.common.log.LogFactory;
import org.support.project.di.DI;
import org.support.project.di.Instance;
import org.support.project.knowledge.logic.KnowledgeDataEditLogic;
import org.support.project.knowledge.vo.api.Knowledge;
import org.support.project.web.bean.NameId;
import org.support.project.web.boundary.Boundary;
import org.support.project.web.common.HttpStatus;
import org.support.project.web.control.ApiControl;
import org.support.project.web.control.service.Post;
import org.support.project.web.exception.InvalidParamException;

import net.arnx.jsonic.JSONException;

@DI(instance = Instance.Prototype)
public class PostDraftApiControl extends ApiControl {
    /** ログ */
    private static final Log LOG = LogFactory.getLog(MethodHandles.lookup());
    /**
     * 記事を下書き保存
     * @throws Exception 
     */
    @Post(path="_api/drafts", checkCookieToken=false, checkHeaderToken=true)
    public Boundary execute() throws Exception {
        LOG.trace("access user: " + getLoginUserId());
        try {
            Knowledge data = getJsonObject(Knowledge.class);
            long id = KnowledgeDataEditLogic.get().saveDraft(data, getLoginedUser());
            return send(HttpStatus.SC_200_OK, new NameId(data.getTitle(), String.valueOf(id)));
        } catch (JSONException e) {
            LOG.debug("json parse error", e);
            return sendError(HttpStatus.SC_400_BAD_REQUEST);
        } catch (InvalidParamException e) {
            return sendError(e);
        } catch (Exception e) {
            LOG.error("error", e);
            return sendError(HttpStatus.SC_500_INTERNAL_SERVER_ERROR);
        }
    }
}

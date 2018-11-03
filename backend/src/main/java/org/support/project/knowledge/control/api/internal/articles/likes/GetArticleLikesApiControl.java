package org.support.project.knowledge.control.api.internal.articles.likes;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.support.project.common.log.Log;
import org.support.project.common.log.LogFactory;
import org.support.project.di.DI;
import org.support.project.di.Instance;
import org.support.project.knowledge.control.api.internal.articles.AbstractArticleApi;
import org.support.project.knowledge.dao.LikesDao;
import org.support.project.knowledge.entity.LikesEntity;
import org.support.project.web.bean.ApiParams;
import org.support.project.web.boundary.Boundary;
import org.support.project.web.common.HttpStatus;
import org.support.project.web.control.service.Get;
import org.support.project.web.logic.invoke.Open;

@DI(instance = Instance.Prototype)
public class GetArticleLikesApiControl extends AbstractArticleApi {
    /** ログ */
    private static final Log LOG = LogFactory.getLog(MethodHandles.lookup());
    /**
     * 記事のイイネの一覧を取得
     * @throws Exception 
     */
    @Get(path="_api/articles/:id/likes")
    @Open
    public Boundary execute() throws Exception {
        LOG.trace("GET _api/articles/:id/likes");
        long knowledgeId = super.getRouteArticleId();
        
        ApiParams apiParams = super.getCommonApiParams();
        int limit = apiParams.getLimit();
        int offset = apiParams.getOffset();
        long total = LikesDao.get().countOnKnowledgeId(knowledgeId);
        super.setPaginationHeaders(total, offset, limit);
        
        List<LikesEntity> likes = LikesDao.get().selectOnKnowledge(knowledgeId, offset, limit);
        for (LikesEntity likesEntity : likes) {
            if (likesEntity.getInsertUser() == null || likesEntity.getInsertUser().intValue() <= 0) {
                likesEntity.setUserName("Anonymous");
            }
        }
        
        return send(HttpStatus.SC_200_OK, likes);
    }

}

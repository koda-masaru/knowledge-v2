SELECT
    COUNT(*)
    FROM
        STOCK_KNOWLEDGES
            INNER JOIN KNOWLEDGES
                ON STOCK_KNOWLEDGES.KNOWLEDGE_ID = KNOWLEDGES.KNOWLEDGE_ID
    WHERE
        STOCK_KNOWLEDGES.STOCK_ID = ?
        AND KNOWLEDGES.DELETE_FLAG = 0

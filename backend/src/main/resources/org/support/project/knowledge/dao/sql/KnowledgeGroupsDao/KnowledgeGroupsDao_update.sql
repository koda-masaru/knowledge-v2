UPDATE KNOWLEDGE_GROUPS
SET 
   INSERT_USER = ?
 , INSERT_DATETIME = ?
 , UPDATE_USER = ?
 , UPDATE_DATETIME = ?
 , DELETE_FLAG = ?
WHERE 
GROUP_ID = ?
 AND KNOWLEDGE_ID = ?
;

UPDATE MAIL_POSTS
SET 
   POST_KIND = ?
 , ID = ?
 , SENDER = ?
 , INSERT_USER = ?
 , INSERT_DATETIME = ?
 , UPDATE_USER = ?
 , UPDATE_DATETIME = ?
 , DELETE_FLAG = ?
WHERE 
MESSAGE_ID = ?
;

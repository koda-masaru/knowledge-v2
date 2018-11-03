UPDATE CSRF_TOKENS
SET 
   TOKEN = ?
 , EXPIRES = ?
 , ROW_ID = ?
 , INSERT_USER = ?
 , INSERT_DATETIME = ?
 , UPDATE_USER = ?
 , UPDATE_DATETIME = ?
 , DELETE_FLAG = ?
WHERE 
PROCESS_NAME = ?
 AND USER_ID = ?
;

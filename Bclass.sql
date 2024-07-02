INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '첫번째 댓글입니다.', 1, 'admin', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '첫번째 댓글입니다.', 13, 'user01', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '두번째 댓글입니다.', 13, 'user02', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '세번째 댓글입니다.', 13, 'admin', '20240603', DEFAULT);

commit;

select * from member;
select * from board;
delete from member
where 'user_id'='admin';



select * from board;
--이중에 조회수 높은 다섯개만 가져갈 것 
SELECT * FROM BOARD ORDER BY COUNT DESC;
-- 조회수 순으로 출력해본거
select * from notice;


SELECT 
    BOARD_NO,
    BOARD_TITLE,
    BOARD_WRITER,
    COUNT,
    CREATE_DATE,
    ORIGIN_DATE
(SELECT 
        BOARD_NO,
        BOARD_TITLE,
        BOARD_WRITER,
        COUNT,
        CREATE_DATE,
        ORIGIN_DATE
    FROM 
        BOARD 
    ORDER
    BY
        COUNT DESC)
    
WHERE ROWNUM BTWEEN 1 AND 5 ;





INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, 'ù��° ����Դϴ�.', 1, 'admin', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, 'ù��° ����Դϴ�.', 13, 'user01', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '�ι�° ����Դϴ�.', 13, 'user02', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '����° ����Դϴ�.', 13, 'admin', '20240603', DEFAULT);

commit;

select * from member;
select * from board;
delete from member
where 'user_id'='admin';



select * from board;
--���߿� ��ȸ�� ���� �ټ����� ������ �� 
SELECT * FROM BOARD ORDER BY COUNT DESC;
-- ��ȸ�� ������ ����غ���
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





   SELECT * FROM BOARD_COMMENT bc ;
    
CREATE TABLE BOARD_COMMENT(
        "BOARD_COMMENT_NO" NUMBER,
        "BOARD_COMMENT_LEVEL" NUMBER DEFAULT 1, --댓글에 대한 순번
        "BOARD_COMMENT_WRITER" VARCHAR2(15), 
        "BOARD_COMMENT_CONTENT" VARCHAR2(2000), 
        "BOARD_REF" NUMBER, --게시글 번호(BOARD_NO)의 FK :특정 게시글 번호 밑에 연결하기 위한 댓글번호 : QNA_NO2
        "BOARD_COMMENT_REF" NUMBER, --특정댓글에 대한 답글(대댓글) :QNACO_REF
        "BOARD_COMMENT_DATE" DATE DEFAULT SYSDATE, 
        CONSTRAINT PK_BOARD_COMMENT_NO PRIMARY KEY(BOARD_COMMENT_NO),
        CONSTRAINT FK_BOARD_COMMENT_WRITER FOREIGN KEY(BOARD_COMMENT_WRITER) REFERENCES MEMBER(USERID) ON DELETE SET NULL, --작성자는 회원db에 연결되어있음
        CONSTRAINT FK_BOARD_REF FOREIGN KEY(BOARD_REF) REFERENCES BOARD(BOARD_NO) ON DELETE CASCADE,
        CONSTRAINT FK_BOARD_COMMENT_REF FOREIGN KEY(BOARD_COMMENT_REF) REFERENCES BOARD_COMMENT(BOARD_COMMENT_NO) ON DELETE CASCADE
    );
    
   COMMENT ON COLUMN BOARD_COMMENT.BOARD_COMMENT_NO IS '게시판댓글번호';
    COMMENT ON COLUMN BOARD_COMMENT.BOARD_COMMENT_LEVEL IS '게시판댓글 레벨';
    COMMENT ON COLUMN BOARD_COMMENT.BOARD_COMMENT_WRITER IS '게시판댓글 작성자';
    COMMENT ON COLUMN BOARD_COMMENT.BOARD_COMMENT_CONTENT IS '게시판댓글';
    COMMENT ON COLUMN BOARD_COMMENT.BOARD_REF IS '참조원글번호';
    COMMENT ON COLUMN BOARD_COMMENT.BOARD_COMMENT_REF IS '게시판댓글 참조번호';
    COMMENT ON COLUMN BOARD_COMMENT.BOARD_COMMENT_DATE IS '게시판댓글 작성일';

        
    CREATE SEQUENCE SEQ_BOARD_COMMENT_NO
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCYCLE
    NOCACHE;
    
   SELECT * FROM BOARD ORDER BY BOARD_NO DESC;
  SELECT * FROM BOARD_COMMENT;
 SELECT * FROM BOARD_COMMENT WHERE BOARD_REF=?
 START WITH BOARD_COMMENT_LEVEL=1
 CONNECT BY PRIOR BOARD_COMMENT_NO=BOARD_COMMENT_REF;
SELECT * FROM BOARD_COMMENT WHERE BOARD_REF=22 START WITH BOARD_COMMENT_LEVEL=1 CONNECT BY PRIOR BOARD_COMMENT_NO=BOARD_COMMENT_REF;
SELECT * FROM BOARD_COMMENT WHERE QNA_NO2=? START WITH BOARD_COMMENT_LEVEL=1 CONNECT BY PRIOR BOARD_COMMENT_NO=QNACO_REF

INSERT INTO BOARD_COMMENT VALUES(SEQ_BOARD_COMMENT_NO.NEXTVAL, DEFAULT, 'opqr','글 잘읽었습니다.',22,NULL,DEFAULT);
INSERT INTO BOARD_COMMENT VALUES(SEQ_BOARD_COMMENT_NO.NEXTVAL, DEFAULT, 'efgh','좋은 정보 감사합니다.',22,NULL,DEFAULT);

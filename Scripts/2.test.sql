select max(board_num) from board;
select now() du;

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0, 0, 0, 0, '2021-03-03');


INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)
VALUES(2, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0);

select * from board ;

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, 
		BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
from board 
order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
limit 0,10;

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, 
		BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
from board 
order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
limit 10,10;

-- listcount
select count(*) from board; 

-- 내용보기
select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
				BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
				from board where BOARD_NUM = 1;
-- read board
select BOARD_READCOUNT
				from board where BOARD_NUM = 1;	

update board 
	set BOARD_READCOUNT =BOARD_READCOUNT+1
	where BOARD_NUM =1;
			
select 1 from board  where BOARD_NUM = 28 and BOARD_PASS ="1111";

update board 
	set BOARD_SUBJECT = '444' ,BOARD_CONTENT ='555'
	where BOARD_NUM = 22;
		
update board 
	set BOARD_RE_SEQ = BOARD_RE_SEQ +1
	where BOARD_RE_REF =40 and BOARD_RE_SEQ >0;
			

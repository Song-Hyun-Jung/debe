
CREATE SEQUENCE sequenceAnswerId
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE sequencePostId
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE sequenceSolutionId
	INCREMENT BY 1
	START WITH 1;

DROP TABLE QuestionAnswer CASCADE CONSTRAINTS PURGE;

DROP TABLE Question CASCADE CONSTRAINTS PURGE;

DROP TABLE Bookmark CASCADE CONSTRAINTS PURGE;

DROP TABLE checkCount CASCADE CONSTRAINTS PURGE;

DROP TABLE checkScore CASCADE CONSTRAINTS PURGE;

DROP TABLE RecommendSolution CASCADE CONSTRAINTS PURGE;

DROP TABLE Recommend CASCADE CONSTRAINTS PURGE;

DROP TABLE Post CASCADE CONSTRAINTS PURGE;

DROP TABLE ServiceUser CASCADE CONSTRAINTS PURGE;

DROP TABLE Subject CASCADE CONSTRAINTS PURGE;

CREATE TABLE Post
(
	postId               INTEGER NOT NULL ,
	title                VARCHAR2(2000) NOT NULL ,
	postDate             DATE DEFAULT  SYSDATE  NOT NULL ,
	postContent          VARCHAR2(2000) NOT NULL ,
	userId               INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKPost ON Post
(postId   ASC);

ALTER TABLE Post
	ADD CONSTRAINT  XPKPost PRIMARY KEY (postId);

CREATE TABLE Question
(
	questionLanguage     VARCHAR2(30) DEFAULT  'JAVA'  NOT NULL ,
	solve                CHAR DEFAULT  'n'  NOT NULL  CONSTRAINT  questionSolve CHECK (solve IN ('y', 'n')),
	questionAdopt        CHAR DEFAULT  'n'  NOT NULL  CONSTRAINT  questionAdopt_1551414205 CHECK (questionAdopt IN ('y', 'n')),
	postId               INTEGER NOT NULL ,
	subjectId            INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKQuestion ON Question
(postId   ASC);

ALTER TABLE Question
	ADD CONSTRAINT  XPKQuestion PRIMARY KEY (postId);

CREATE TABLE Recommend
(
	difficulty           VARCHAR2(10) DEFAULT  'mid'  NOT NULL  CONSTRAINT  recommendDifficulty_925832157 CHECK (difficulty IN ('high', 'mid', 'low')),
	recommendCount       INT DEFAULT  0  NOT NULL  CONSTRAINT  recommendCount_1113143039 CHECK (recommendCount >= 0),
	algorithm            VARCHAR2(2000) NOT NULL ,
	postId               INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKRecommend ON Recommend
(postId   ASC);

ALTER TABLE Recommend
	ADD CONSTRAINT  XPKRecommend PRIMARY KEY (postId);

CREATE TABLE RecommendSolution
(
	solutionContent      VARCHAR2(2000) NOT NULL ,
	solutionScore        FLOAT NOT NULL  CONSTRAINT  solutionScore_1101631870 CHECK (solutionScore BETWEEN 0.0 AND 10.0),
	solutionDate         DATE DEFAULT  SYSDATE  NOT NULL ,
	postId               INTEGER NOT NULL ,
	userId               INTEGER NOT NULL ,
	solutionId           INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKRecommendSolution ON RecommendSolution
(solutionId   ASC);

ALTER TABLE RecommendSolution
	ADD CONSTRAINT  XPKRecommendSolution PRIMARY KEY (solutionId);

CREATE TABLE ServiceUser
(
	userId               INTEGER NOT NULL  CONSTRAINT  studentNo CHECK (userId BETWEEN 10000000 AND 20999999),
	userPassword         VARCHAR2(20) NOT NULL ,
	userName             VARCHAR2(10) NOT NULL ,
	userNickname         VARCHAR2(20) NOT NULL ,
	userLevel            INTEGER DEFAULT  1  NOT NULL  CONSTRAINT  userLevel_486682058 CHECK (userLevel >= 1),
	subjectId            INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKUser ON ServiceUser
(userId   ASC);

ALTER TABLE ServiceUser
	ADD CONSTRAINT  XPKUser PRIMARY KEY (userId);

CREATE UNIQUE INDEX XAK1Nickname ON ServiceUser
(userNickname   ASC);

ALTER TABLE ServiceUser
ADD CONSTRAINT  XAK1Nickname UNIQUE (userNickname);

CREATE TABLE QuestionAnswer
(
	answerContent        VARCHAR2(2000) NOT NULL ,
	answerAdopt          CHAR DEFAULT  'n'  NOT NULL  CONSTRAINT  answerAdopt_1909575635 CHECK (answerAdopt IN ('y', 'n')),
	answerDate           DATE DEFAULT  SYSDATE  NOT NULL ,
	postId               INTEGER NOT NULL ,
	userId               INTEGER NOT NULL ,
	answerId             INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKQuestionAnswer ON QuestionAnswer
(answerId   ASC);

ALTER TABLE QuestionAnswer
	ADD CONSTRAINT  XPKQuestionAnswer PRIMARY KEY (answerId);

CREATE TABLE Bookmark
(
	userId               INTEGER NOT NULL ,
	postId               INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKBookmark ON Bookmark
(userId   ASC,postId   ASC);

ALTER TABLE Bookmark
	ADD CONSTRAINT  XPKBookmark PRIMARY KEY (userId,postId);

CREATE TABLE checkCount
(
	userId               INTEGER NOT NULL ,
	checked              CHAR(18) NULL  CONSTRAINT  checkCount_2078261443 CHECK (checked IN ('y', 'n')),
	postId               INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKcheckCount ON checkCount
(userId   ASC,postId   ASC);

ALTER TABLE checkCount
	ADD CONSTRAINT  XPKcheckCount PRIMARY KEY (userId,postId);

CREATE TABLE checkScore
(
	solutionId           INTEGER NOT NULL ,
	userId               INTEGER NOT NULL ,
	checked              CHAR(18) NULL  CONSTRAINT  checkScore CHECK (checked IN ('y', 'n')),
	giveScore            INTEGER NULL 
);

CREATE UNIQUE INDEX XPKcheckScore ON checkScore
(solutionId   ASC,userId   ASC);

ALTER TABLE checkScore
	ADD CONSTRAINT  XPKcheckScore PRIMARY KEY (solutionId,userId);

CREATE TABLE Subject
(
	subjectId            INTEGER NOT NULL ,
	subjectTitle         VARCHAR2(2000) NOT NULL 
);

CREATE UNIQUE INDEX XPKSubject ON Subject
(subjectId   ASC);

ALTER TABLE Subject
	ADD CONSTRAINT  XPKSubject PRIMARY KEY (subjectId);

ALTER TABLE Post
	ADD (CONSTRAINT R_29 FOREIGN KEY (userId) REFERENCES ServiceUser (userId));

ALTER TABLE Question
	ADD (CONSTRAINT R_17 FOREIGN KEY (postId) REFERENCES Post (postId) ON DELETE CASCADE);

ALTER TABLE Question
	ADD (CONSTRAINT R_26 FOREIGN KEY (subjectId) REFERENCES Subject (subjectId) ON DELETE SET NULL);

ALTER TABLE Recommend
	ADD (CONSTRAINT R_18 FOREIGN KEY (postId) REFERENCES Post (postId) ON DELETE CASCADE);

ALTER TABLE RecommendSolution
	ADD (CONSTRAINT RecommendSolution FOREIGN KEY (postId) REFERENCES Recommend (postId));

ALTER TABLE RecommendSolution

create database day15;
use day15;

--用户表
CREATE TABLE S_User(
	userID INT  NOT NULL AUTO_INCREMENT, --主键ID
	userName VARCHAR(50)   NULL,  --用户姓名
	logonName VARCHAR(50)   NULL, --登录名
	logonPwd VARCHAR(50)  NULL,   --密码--
	sex VARCHAR(10)  NULL,        --性别（例如：男，女）
	birthday VARCHAR(50) NULL,    --出生日期
	education VARCHAR(20)  NULL,  --学历（例如：研究生、本科、专科、高中）
	telephone VARCHAR(50)  NULL,  --电话 
	interest VARCHAR(20)  NULL,   --兴趣爱好（例如：体育、旅游、逛街）
	path VARCHAR(500)  NULL,      --上传路径（path路径）
	filename VARCHAR(100)  NULL,  --上传文件名称（文件名）
	remark VARCHAR(500)  NULL,    --备注
	PRIMARY KEY (userID)
) 

--初始化数据：默认用户名和密码是admin
INSERT INTO s_user (userID,userName,logonName,logonPwd) VALUES (1,'超级管理员','admin','admin')

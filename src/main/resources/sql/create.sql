-- 用户表
create table myschema.user(id integer,name text,password text)
insert into myschema.user values(1001,'admin','123456')
insert into myschema.user values(1002,'hello','123456')
insert into myschema.user values(1003,'world','123456')


-- 角色表
create table myschema.role(id integer,role_name text)
insert into myschema.role values(10001,'管理员角色')

-- 用户和角色关系表
create table myschema.user_role(id integer,user_id integer,role_id integer)
insert into myschema.user_role values(100001,1001,10001)



-- 权限表
create table myschema.permission(id integer,permission_name text)

insert into myschema.permission values(101,'query')
insert into myschema.permission values(102,'insert')

-- 角色和权限关系表
create table myschema.role_permission(id integer,role_id integer,permission_id integer)
insert into myschema.role_permission values(1000001,10001,101)
insert into myschema.role_permission values(1000002,10001,102)


-- 建表语句
create sequence seq increment by 1 start with 1001;
alter sequence seq restart with 1001;
create table myschema.asset(
asset_id integer not null default nextval('myschema.seq'),
asset_no character varying(100),
asset_name character varying(100),
asset_type character varying(100),
asset_status character varying(100),
asset_nature character varying(100),
borrow_person character varying(100),
borrow_time date,
asset_value numeric,
buy_time date,
stored_section character varying(100)
);

insert into myschema.asset(asset_no,asset_name,asset_type,asset_status,asset_nature,borrow_person,borrow_time,asset_value,buy_time,stored_section) values('2131','桌子','电子产品','使用中','公有财产','张山','2020-10-11',21.213,'2020-11-23','仓库')

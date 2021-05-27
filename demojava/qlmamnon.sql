create database QLNHATRE1
go
use QLNHATRE1
go

create table hocsinh
(
	mahs char(10) not null,
	malop char(10)not null,
	hoten nvarchar(50) not null,
	ngaysinh varchar(50),
	giotinh nchar(6) not null default N'nữ',
	diachi nvarchar(50) not null,
	dantoc nvarchar(30) not null,
	tongiao nvarchar(10)not null,
	primary key (mahs)
);

insert into hocsinh
values
	('hs01','08M1',N'Hồ Thị Thi','01/02/2007',N'nữ',N'Đà Nẵng',N'Kinh',N'Không'),
	('hs02','08M1',N'hồ Thi Nhân','01/02/2007',N'nữ',N'Đà Nẵng','Kinh',N'không'),
	('hs03','08M1',N'hồ Thị Hiền','01/02/2007',N'nữ',N'Đà Nẵng','Kinh',N'không'),
	('hs04','08M1',N'hồ văn Lan','01/02/2007',N'nam',N'Đà Nẵng','Kinh',N'không');
create table tongdiem
(
	mahs char(10)not null foreign key references hocsinh primary key,
	toan float(2),
	vannghe float(2),
	theduc float(2),
	mythuat float(2),
	tongdiem float(2) ,
	hocluc nvarchar(10)
);
insert into tongdiem
values
	('hs01',10,9,5,6,7,N'giỏi'),
	('hs02',7,5,7,8,9,N'khá'),
	('hs03',5,4,3,8,9,N'Trung Bình'),
	('hs04',8,8,9,7,5,N'khá');
create table dangnhap
(
	magv char(10)not null,
	mk char(30) ,
	primary key(magv)
)

insert into dangnhap
values
('gv01','gv01'),
('gv02','gv02'),
('gv03','gv03'),
('gv04','gv04'),
('gv05','gv05')

create table suckhoe
(
	mahs char(10)not null foreign key references hocsinh primary key,
	chieucao float(2),
	cannang float(2),
	trangthai nvarchar(30)
)
insert into suckhoe
values
	('hs01',22,130,N'Bình thường'),
	('hs02',22,130,N'Bình thường'),
	('hs03',22,130,N'Bình thường'),
	('hs04',22,130,N'Bình thường')
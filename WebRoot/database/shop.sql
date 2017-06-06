create table t_product(
  id int primary key auto_increment,
  name varchar(20),
  description varchar(100),
  price double
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table t_user(
  id int primary key auto_increment,
  username varchar(20),
  password varchar(30),
  phone varchar(20),
  address varchar(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table t_order(
  id int primary key auto_increment,
  user_id int references t_user(id),
  orderNo varchar(20),
  totalPrice double
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table t_item(
  id int primary key auto_increment,
  product_id int references t_product(id),
  num int,
  order_id int references t_order(id),
  itemPrice double
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_product(name,description,price) values('苹果','产自南美',99.0);
insert into t_product(name,description,price) values('香蕉','产自海南',66.0);
insert into t_product(name,description,price) values('西瓜','甜而多汁',77.0);
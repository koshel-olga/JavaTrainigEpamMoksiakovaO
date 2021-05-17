-- Поиск products по title
select * from public.products where title like '%SHRE%';

-- Поиск products по price = 9.99 и category = 8 и отсортировать по category и price
select title, category, actor, price from products where price = 9.99 and category = 8 order by actor, title;

-- Поиск products у которых category = 8 или 15
select title, category, actor, price from products where category in (8, 15) order by actor, title;

-- Поиск products у которых price между 10 и 20 (используйте BETWEEN)
select title, category, actor, price from products where price between 10 and 20 order by actor, title;

-- Поиск orders у которых orderdate между 2004-01-05 и 2004-02-05
select * from orders where  '[2004-01-05, 2004-02-05]'::daterange @> orderdate;

-- Сгруппировать данные в orders по полю customerid и посчитать количество относительно каждого customerid
select count(orderid), customerid from orders group by customerid;

-- Сгруппировать данные в orders по полям customerid и orderdate и просуммировать totalamount, при этом сумма totalamount должна быть больше 100
select sum(totalamount) as totalsum, customerid, orderdate from orders o group by customerid, orderdate having sum(totalamount) > 100; 

-- Написать запрос к таблицам customer, orders, orderlines и products с использованием JOIN и вывести firstname, lastname, title, orderdate
select c.firstname, c.lastname, p.title, o.orderdate
from customers c 
join orders o on o.customerid = c.customerid 
join orderlines ol on ol.orderid = o.orderid 
join products p on ol.prod_id = p.prod_id;
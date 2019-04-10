# DB_PRO  

group member: (blank for group leader),　11710924 Void,　11712008 ShiqingLiu,　11712641
<br/>

## Project Introduction  

This project is intended as a relational database system for new-retailing chain store management and sales.  

The system provides service mainly for three types of users including manager, customer and deliverer.  

#### It enables managers to
>* sign in
>* add a new account for newly-employed manager or deliverer
>* add a newly-built warehouse or retailing chain store
>* monitor and replenish goods in the warehouses
>* consult purchase record
>* expand the region business scale
>* formulate the next season sales strategy.  

#### Customers are allowed to
>* sign in
>* sign up
>* surf the goods on sale
>* add goods to the virtual track online
>* purchase goods both online and offline
>* make a delivery order
>* view his/her history order  

#### For deliverers, they are free to
>* sign in
>* view the deliver orders that he needs to complete
>* view the delivery destination of his current order 

## Design

### part 1
* user
* manager
* customer
* deliver
  We first implement users to our database. They are manager customer deliver  

## Some examples in the function realization  

### functions for manager
>monitor and replenish goods in the warehouses  

If a manager wants to replenish the goods in his warehouse,  
* first he should use the `warehouse_id` and `goods_id` in the `goods_in_warehouse` table to consult the left amount.  
* Then after purchasing, he should add a new record in the purchase table for history monitoring.  
* After that, the amount of such type of goods in `goods_in_ warehouse` table is added.  

<br/>

>add a new account for newly-employed manager or deliverer  

If the company hires a new manager,  

* first he should add a new entity to the `user` table, initialing the default `password` and adding `phone_number`  
* After that, the `warehouse_warehouse_id` in the `manager` table, the one whose primary key is `user_id`, is given according to the actual job arrangement.  

<br/>

>add a newly-built warehouse or retailing chain store  

If a new warehouse is added to the company's commercial expansion plan,  

* first the manager should add a new entity to the `warehouse` table, initialing its properties such as `address`, `shelf_volume` and `warehouse_long`&`warehouse_lati`  
* then when the manager wants to transport goods from one warehouse to the span-new site, he just needs to change the `warehouse_warehouse_id` in the `goods_in_warehouse` table to denote the transportation of the goods.  
* additionally, once a store is attached to that warehouse, a new entity should be added in the `store` table, indicating its `warehouse_warehouse_id` as well as recording  its location in the city in the `address`.  

## Time table

>week 9  

* Improve and complete the logical physical structure design of DB
* Realize functions for manager
* Start design the graphic interface

>week 10  

* Realize functions for customer and deliverer
* Check the progress of GUI
* Test the concurrent connect of multiple type of users

>week 11  

* Connect GUI and program together
* Implement some optional functions based on process

>week 12  

* Debugging and improving
* Complete and test the operation and maintenance of DBS


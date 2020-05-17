# SPRING事务的特性

### 事务遵循四个特性，既ACID
* 原子性：事务做为一个整体，要不全部执行，要不全部不执行
* 一致性：事务执行前后保持一致，从一个一致性的状态变为另一个一致性状态
* 隔离性：事务之间的执行不会相互干扰，并发执行的不会相互干扰
* 持久性：事务一旦提交，对数据更改是持久性的操作

这里要解释下D持久性这个特性，例如事务提交后，即使断电等操作，数据库没有将数据写入磁盘，下次数据库恢复正常，也会将数据重新写入磁盘。因为数据库，在操作的数据的时候，都会将数据的操作，写入redo log文件中，当事务一旦提交，即使发生断电的操作，下次启动的时候，会重新读取redo_log文件，从上次执行位置继续执行事务提交未完成的操作，成功将数据写入磁盘。同理，若事务发生rollback，发生断电，通过undo log将数据恢复到事务执行之前的状态。
redo log,undo log与binlog参考文章https://www.jianshu.com/p/090087c22820


### 事务的隔离级别
* read_uncommitted：读未提交，一个事务可以读取到另一个update未提交的内容
* read_committed：读已提交，一个事务可以读取到另一个事务update提交的内容
* repeatable_read：可重复读，一个事务可以读取到另一个事务insert提交的内容，mysql默认的隔离级别
* serializable：串行化执行，事务串行执行，资源消耗最大，性能最低，但是最安全

spring在事务操作的时候，若不指定隔离级别，default级别默认使用数据库的隔离级别
不同的隔离会带来数据的脏读，幻读，不可重复读，看下面表格
|  | 脏读 | 不可重复读 | 幻读 |
| --- | --- | --- | --- |
|read_uncommitted|yes|yes|yes|
|read_committed|no|yes|yes|
|repeatable_read|no|no|yes|
|serializable|no|no|no|

![image](https://github.com/GitForSl/photos/blob/master/spring-demo/transaction/20200517173336.png)


### 事务的传播特性
* Propagation.REQUIRED：若当前存在事务，就加入当前事务，若没有事务则新启一个事务
* Propagation.REQUIRED_NEW：不管当前存不存在事务，都新启一个事务，将当前存在的事务挂起
* Propagation.SUPPORTS：支持当前事务，若当前存在事务，就加入当前事务，若当前没有事务，就以非事务的方式运行
* Propagation.NOT_SUPPORTS：以非事务的方式运行，若当前存在事务，就将当前事务挂起
* Propagation.MANDATORY：以事务方式运行，若当前存在事务，就加入当前事务，若当前没有事务，就抛出异常
* Propagation.NEVER：以非事务方式运行，若当前存在事务，就抛出异常
* Propagation.NESTED：嵌套事务，若当前存在事务，就当前事务里嵌套一个事务运行，若当前不存在事务，则以Propagation.REQUIRED方式，既开启一个新的事务




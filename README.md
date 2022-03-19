# canal数据同步
#两个数据库名跟表名要一致

#在mysql数据库配置文件添加
log-bin=mysql-bin
binlog_format=ROW
server_id=1

#查看数值是否为ON
show VARIABLES LIKE 'log_bin'
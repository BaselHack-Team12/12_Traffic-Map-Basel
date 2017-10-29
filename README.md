# Traffic-Map-Basel


# Project goal
Feed radar data to the backend in order to show the most dangerous streets on a Basel map.
We are using this dataset: https://github.com/StakaBS/BaselHack/tree/master/radardaten

### Installation
Install java, mysqld, and a web server 
On Debian/Ubuntu: 
```sh
$ apt install openjdk-8-jre mysql-client mysql-server apache2
```
On RHEL/CentOS: 
```sh
$ yum install java-1.8.0-openjdk mariadb mariadb-server httpd
```
Configure mysql with mysql_secure_installation and the structure provided.
Clone this repository; set up a basic VirtualHost containing our Javascript files
You will need to configure data sources accourding to your installation inside "backend/src/main/resources/hibernate.cfg.xml"
Then you can launch the java backend.
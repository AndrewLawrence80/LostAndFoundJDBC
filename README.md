# LostAndFoundJDBC

A toy JDBC lost and found application in JavaFX GUI developed during my sophomore year. Search function is not implemented.

## Requirements

1. MySQL (Docker or local installed)
2. JDK

## How to run the demo

I recommend on Debian/Ubuntu because I only tried it out on Debain.

On Debian Bullseye

**Step 1**

``` bash
git clone https://github.com/AndrewLawrence80/LostAndFoundJDBC.git
```

``` bash
cd LostAndFoundJDBC
```

**Step 2**
Execute file ```db_init.sql``` in your MySQL server. If there's already a database called lostandfound, modify the database name in line 1 and 2

**Step 3**

Edit the MySQL configuration first in <font color='red'>```config/mysql.properties```</font>. In most cases, you only need to modify the database url, username and password

``` bash
sudo apt install maven default-jdk default-jre
```

**To just peek the demo**
``` bash
mvn clean javafx:run
```

**Build an executable package**

```bash
mvn compile package
```

```bash
java -jar target/lost-and-found-jdbc.jar
```
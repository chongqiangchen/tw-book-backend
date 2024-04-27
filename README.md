# TW Book Server

### local run

```
    // install mysql
    docker run -d --name mysql_container -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 mysql
    
    // run application
    ./gradlew bootRun
```


### docker run
```
    chmod +x build_and_run.sh
    
    ./build_and_run.sh
```

### 


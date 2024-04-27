# TW Book Server

### Guide

Online Server: http://95.217.235.245:8083

Online Web: https://tw-book-frontend.vercel.app

### Local Run
```
    // install mysql
    docker run -d --name mysql_container -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 mysql
    
    // create tw_book database
    docker exec -it mysql_container mysql -uroot -p123456 -e "create database tw_book"
    
    // run application
    ./gradlew bootRun
```


### Docker Run
```
    chmod +x build_and_run.sh
    
    ./build_and_run.sh
```

### Deploy
```
    // use github action to deploy
```


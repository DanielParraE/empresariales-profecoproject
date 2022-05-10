# Important Configuration

## RabbitMQ Server

you have to run a RabbitMQ Server in port 5672
```
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```

# Google CLient Credentials

If you test google sign in, you have to add oauth credentials for truemarket-web, trueconsumer-web and controlprofeco-web 
![config](https://i.imgur.com/JglGneu.png)
![config](https://i.imgur.com/P9dQFIB.png)
 
# Running projects

![run](https://i.imgur.com/f6L6zNn.png )

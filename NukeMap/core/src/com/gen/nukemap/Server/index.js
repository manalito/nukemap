var app = require('express')();
var server = require ('http').Server(app);
var io = require('socket.io')(server);
var players = [];
var sockets = [];
const numberOfPlayers = 2;

var enemies = [];

var bombId=0;

server.listen(8080,function(){
    console.log("Server is now running...");
});

enemies.push(new enemy(32425, 200, 100, "FRONT"));
enemies.push(new enemy(32426, 200, 200, "FRONT"));

io.on('connection',function(socket){

  console.log("Player is Connected");
    sockets.push(socket);
    socket.emit('socketID',{id: socket.id });
    socket.emit('addOtherPlayers',players);
    socket.broadcast.emit('newPlayer', { id: socket.id });
    /*socket.on('addMonsters',function(data){
          ennemies.push(new enemy(32425, 200, 100, "FRONT"));
          console.log("Ennemi added : " + "ID : " + "DONE");
         //socket.broadcast.emit("addMonsters",{ id: 32425, x: 200, y: 100, state: "FRONT" });
    });*/

    for(var i = 0; i < enemies.length; i++){
        socket.emit("addMonsters", {id: enemies[i].id, x: enemies[i].x, y: enemies[i].y, 
            state: enemies[i].state});
    }

    //socket.emit("addMonsters",{ id: 32425, x: 200, y: 100, state: "FRONT" });
    socket.on('playerMoved',function(data){
           data.id=socket.id;
           socket.broadcast.emit('playerMoved',data);

            //console.log("Player has moved : " + "ID : " + data.id + "X: " + data.x + "Y: " + data.y  + "State: " + data.state);

    });

    socket.on('playerDied',function(data){
          socket.emit('endGame',data);

          console.log("Player died : ");
    });
        socket.on('bombDropped',function(data){

                   data.id=bombId++;
                   io.sockets.emit('bombDroppedValid',data);
                   console.log("Bomb dropped : " + "ID : " + data.id + "X: " + data.x + "Y: " + data.y);
                   //We wait 3 seconds before explosion occures
                   var timer = 3000;
                   setTimeout(function() {
                        // get all the cells,monsters and ennemies that has to be destroyed/killed
                        // Whatever you want to do after the wait
                        console.log("Bomb exploded after 3 seconds !");
                        io.sockets.emit('bombExplose',data);
                   }, timer);

            });

  socket.on('disconnect',function(){
    console.log("Player is disconnected");
      socket.broadcast.emit('playerDisconnected',{id: socket.id });
      for(var i =0 ; i< players.length ; i++){
          if(players[i].id == socket.id){
              players.splice(i,1);
          }
      }
  });
  players.push(new player(socket.id,64,64));
});

setInterval(function(){
    if(players.length == numberOfPlayers){
        for(var k = 0; k < enemies.length; k++){
            var d = randomInt(0,3);
            io.sockets.emit('enemyMoves', {id: enemies[k].id, direction: d});
        }
    }
}, 800);

function player(id,x,y,state){
    this.id = id;
    this.x = x;
    this.y = y;
    this.state = state;
}

function enemy(id,x,y,state){
    this.id = id;
    this.x = x;
    this.y = y;
    this.state = state;
}

function randomInt (low, high) {
    return Math.floor(Math.random() * (high - low + 1) + low);
}

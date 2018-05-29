var app = require('express')();
var server = require ('http').Server(app);
var io = require('socket.io')(server);
var players = [];

var ennemies = [];
var ennemyId = 0;

server.listen(8080,function(){
    console.log("Server is now running...");
});

io.on('connection',function(socket){

  console.log("Player is Connected");
    socket.emit('socketID',{id: socket.id });
    socket.emit('addOtherPlayers',players);
    socket.broadcast.emit('newPlayer', { id: socket.id });
    socket.on('addMonsters',function(data){
          ennemies.push(new enemy(32425, 200, 100, "FRONT"));
          console.log("Ennemi added : " + "ID : " + "DONE");
          socket.broadcast.emit("addMonsters",{ id: 32425, x: 200, y: 100, state: "FRONT" });
    });

    socket.emit("addMonsters",{ id: 32425, x: 200, y: 100, state: "FRONT" });
    socket.on('playerMoved',function(data){
           data.id=socket.id;
           socket.broadcast.emit('playerMoved',data);

            console.log("Player has moved : " + "ID : " + data.id + "X: " + data.x + "Y: " + data.y  + "State: " + data.state);

           /*for(var i=0; i < players.length; i++){
                if(players.id ==data.id){
                    players[i].x = data.x;
                    players[i].y = data.y;
                    //players[i].state = data.state;
                }
           }*/

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

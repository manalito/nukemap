fs = require('fs')

var scores = {
	table: []
};
var nbPlayers = 4;
var JSONScore;
var scoreFile = 'score.json'
var matchPlayersId = new Map();

exports.createScoreTable = function (playersID) {
	fs.open(scoreFile, 'w', function (err, file) {
		if (err) throw err;
		console.log('Saved!');
	});

	for (var i = 0; i < nbPlayers; i++) {
		matchPlayersId.set(playersID[i], i);
		scores.table.push({ playerID: playersID[i], score: 0 });
	}
	console.log('Pushed scores!');

	JSONScore = JSON.stringify(scores);
	fs.writeFile(scoreFile, JSONScore, 'utf8', function (err) {
		if (err) {
			return console.log(err);
		}
		console.log("The file was saved!");
	}); // write it back // write it back
}


exports.readScoreFile = function (data) {
	fs.readFile(scoreFile, 'utf8', function (err, data) {
		if (err) {
			return console.log(err);
		}
		console.log(data);
	});
}

exports.updateScoreFile = function (playerID, addScore) {
	fs.readFile(scoreFile, 'utf8', function (err, data) {
		if (err) {
			console.log(err);
		} else {
			scoreToUpdate = JSON.parse(data); // extract Json datas
			console.log(scoreToUpdate);

			//console.log(scores.table[playerID].score );

			//if(playerID == scoreToUpdate.playerID){ // get Json payload of correspondant playerID
			scoreToUpdate.table[matchPlayersId.get(playerID)].score += addScore; // modify score of player with specified playerID

			//}
			//console.log(scoreToUpdate.table[playerID].score );
			console.log(scoreToUpdate);

			JSONScore = JSON.stringify(scoreToUpdate); //convert it back to json
			fs.writeFile(scoreFile, JSONScore, 'utf8', function (err) {
				if (err) {
					return console.log(err);
				}
				console.log("The file was saved!");
			}); // write it back
		}
	});

}



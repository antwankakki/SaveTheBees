var router = require('express').Router();
var state = {
  waiting: 0,
  started: 1,
  ended: 2
};
var curState = 0;
var puzzle, gameMaster;
var curUsers =[];
module.exports = function(){
  router.get('/', function(req,res){
    res.json({status:'working'});
  })
  router.post('/login',function(req,res){
    if(curState === state.waiting && req.body.hasOwnProperty('nickName') && req.body.hasOwnProperty('puzzle')){
      if(curUsers.indexOf(req.body.nickName)< 0){
        req.session.nickName = req.body.nickName;
        curUsers.push(req.body.nickName);
        puzzle = req.body.puzzle;
        res.sendStatus(200);
      }
      else{
        res.status(400).json('Username already in use');
      }
    }
    else{
      res.status(400).json('Game in session or missing data');
    }
  })
  router.get('/users', function(req,res){
    res.json(curUsers);
  })
  router.get('/check', function(req,res){
    if(curState === state.waiting){
      res.status(400).json({status:'waiting for game to start'});
    }
    else if(curState === state.started){
      res.json({puzzle:puzzle,startedBy:gameMaster,status:'game is in session'});
    }
    else{
      res.json({winner:gameMaster, status:'game ended'});
    }
  })
  router.post('/start', function(req,res){
    if(req.session.hasOwnProperty('nickName')){
      gameMaster = req.session.nickName;
      curState = state.started;
      res.sendStatus(200);
    }
    else{
      res.status(400).json({status:'missing session info'});
    }
  })
  router.post('/solved', function(req,res){
    if(req.session.hasOwnProperty('nickName')){
      curState = state.ended;
      gameMaster = req.session.nickName;
      setTimeout(function () {
        curState = 0;
      }, 5000);
      res.sendStatus(200);
    }
    else{
      res.status(400).json({status:'missing session info'});
    }
  })
  router.get('/logout',function(req,res){
    curUsers.splice(curUsers.indexOf(req.session.nickName),1);
    req.session.destroy();
    if(curUsers.length===0){
      curState = state.waiting;
    }
    res.json({logout:true});
  })
  router.post('/test',function(req,res){
    res.json({body:req.body,query:req.query,params:req.params,sessionID:req.sessionID,session:req.session});
  })
  return router;
}

var router = require('express').Router();
module.exports = function(){
  router.get('/', function(req,res){
    console.log(req.session);
    res.json({success:true});
  })
  router.post('/login',function(req,res){
    if(req.body.hasOwnProperty('nickName')){
      req.session.nickName = req.body.nickName;
      res.json({login:true});
    }
    else{
      res.json({login:false});
    }
  })
  router.get('/logout',function(req,res){
    req.session.destroy();
    res.json({logout:true});
  })
  router.get('/test',function(req,res){
    res.json({body:req.body,query:req.query,params:req.params,sessionID:req.sessionID,session:req.session});
  })
  return router;
}

var router = require('express').Router();
module.exports = function(){
  router.get('/', function(req,res){
    console.log(req.session);
    res.json({success:true});
  })
  router.get('/login',function(req,res){
    req.session.nickName = 'fadee';
    res.json({login:true});
  })
  router.get('/logout',function(req,res){
    req.session.destroy();
    res.json({logout:true});
  })
  router.get('/test',function(req,res){
    res.json(req.sessionID);
  })
  return router;
}

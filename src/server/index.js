var app = require('express')();
var router = require('./routes');
var bodyParser = require('body-parser');
var session = require('express-session');

app.server = require('http').createServer(app);
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
var sess = session({
  secret: 'SuperSyaian',
  resave: false,
  saveUninitialized: false,
  name: 'beeQueen'
});
app.use(sess)
app.use('/',router())
app.server.listen(9000);

var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var cors = require('cors');

/**
 * CORS
 *
 * @type {{origin: string, method: string[], allowedHeaders: string}}
 */
var corsOptions = {
    origin: '*',
    method: ['GET', 'POST'],
    allowedHeaders: '*'
};
app.use(cors());

/**
 * Express 설정
 */
app.set('port', (process.env.PORT || 3000));
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static(__dirname + '/public'));


// app.use(function (req, res, next) {
//     // disable caching
//     res.setHeader('Cache-Control', 'no-cache');
//     next();
// });

app.listen(app.get('port'), function () {
    console.log('Server started (port: ' + app.get('port') + ')');
});
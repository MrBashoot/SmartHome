var express = require('express');
var router = express.Router();
var five = require("johnny-five"),
    board = new five.Board();


/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express' });
});

router.post('/LEDon', function(req, res) {
    console.log('LEDon button pressed!');

    var array = new five.Leds([6, 9, 10,11]);

    array.on();

    res.status(200);
});
router.post('/LEDoff', function(req, res) {
    console.log('LEDoff button pressed!');
        // Create an Led on pin 13
    var array = new five.Leds([6, 9, 10,11]);

        // Strobe the pin on/off, defaults to 100ms phases
        array.stop().off();

    res.status(200);
});
module.exports = router;

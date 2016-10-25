/**
 * Created by Mohammedyasser on 20-Oct-16.
 */
var five = require("johnny-five");
var board = new five.Board();

board.on("ready", function() {

    var slider = new five.Sensor("A0");
    var array = new five.Leds([6, 9, 10,11]);

    // Scale the sensor's value to the LED's brightness range
    slider.scale([0, 255]).on("data", function() {
        array.brightness(this.value);
    });
});
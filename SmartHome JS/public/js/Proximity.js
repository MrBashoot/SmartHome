var five = require("johnny-five");
var board = new five.Board();

board.on("ready", function() {
    var proximity = new five.Proximity({
        controller: "HCSR04",
        pin: 7
    });
    var array = new five.Leds([6, 9, 10,11]);


    proximity.on("data", function() {
        console.log("Proximity: ");
        if(this.cm>5){
            array[0].off();
        }
        else{
            array[0].on();
        }
        if(this.cm>10){
            array[1].off();
        }
        else{
            array[1].on();
        }
        if(this.cm>15){
            array[2].off();
        }
        else{
            array[2].on();
        }
        if(this.cm>20){
            array[3].off();
        }
        else{
            array[3].on();
        }
        console.log("  cm  : ", this.cm);
        console.log("  in  : ", this.in);
        console.log("-----------------");
    });

   /* proximity.on("change", function() {
        console.log("The obstruction has moved.");
    });*/
});

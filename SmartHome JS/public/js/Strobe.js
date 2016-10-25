'use strict';
$('#ledon-button').click(function() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:7070/LEDon'
    });
});
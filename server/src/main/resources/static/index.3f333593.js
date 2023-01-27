var intervalId;
var timerValue;
function startTimer() {
    intervalId = setInterval(function() {
        var currentTime = document.getElementById("timer").innerHTML;
        var timestamp = currentTime.split(":");
        var minutes = parseInt(timestamp[0]);
        var seconds = parseInt(timestamp[1]);
        if (seconds >= 59) {
            minutes++;
            seconds = 0;
        } else seconds++;
        if (minutes < 10) minutes = "0" + minutes;
        if (seconds < 10) seconds = "0" + seconds;
        timerValue = minutes + ":" + seconds;
        document.getElementById("timer").innerHTML = minutes + ":" + seconds;
    }, 1000);
}
function stopTimer() {
    clearInterval(intervalId);
}
function resetTimer() {
    stopTimer();
    document.getElementById("timer").innerHTML = "00:00";
}
function getTimerValue() {
    return timerValue;
}

//# sourceMappingURL=index.3f333593.js.map

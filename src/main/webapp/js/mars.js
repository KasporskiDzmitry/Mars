/**
 * Created by User on 04.10.2018.
 */

var cvs = document.getElementById("canvas");
var ctx = cvs.getContext("2d");

var images = ['images/bg.png','images/car.png'];
var loadedImages = {};
var promiseArray = images.map(function(imgurl){
    var prom = new Promise(function(resolve,reject){
        var img = new Image();
        img.onload = function(){
            loadedImages[imgurl] = img;
            resolve();
        };
        img.src = imgurl;
    });
    return prom;
});


Promise.all(promiseArray).then(draw);


function draw(numberOfFirstBlock, carPosition) {
    let n = numberOfFirstBlock | 0;
    let c = carPosition | 0;

    ctx.drawImage(loadedImages['images/bg.png'], 0, 0);
    ctx.drawImage(loadedImages['images/car.png'], 50 * (c - n), 375, 50, 60);

    ctx.beginPath();

    ctx.moveTo(0, 425);
    ctx.lineTo(cvs.width, 425);
    ctx.fill();

    for (let i = 0, j = numberOfFirstBlock | 0; i < cvs.width; i += 50, j++) {
        ctx.moveTo(i, 425);
        ctx.lineTo(i, 563);
        ctx.font = "20px Georgia";
        ctx.strokeText(j, i+20, 500);
        ctx.fill();
    }
    ctx.stroke();
}

class Rover {
    constructor() {
        this.speed = this.startSpeed;
        this.position = 0;
    };

    acceleration() {
        this.position += this.speed;
        this.speed *= 2;

        this.refreshInfo();

        let bound = Math.floor(this.position / 15) * 15;
        if (this.speed > 0 && this.position >= bound) {
            requestAnimationFrame(draw(bound, this.position));
        } else if (this.speed < 0 && this.position >= bound) {
            requestAnimationFrame(draw(bound ,this.position));
        } else {
            requestAnimationFrame(draw(0, this.position));
        }
    }

    reverse() {
        this.startSpeed = -this.startSpeed;
        this.speed = this.startSpeed;
        this.refreshInfo();
    }

    reset() {
        this.speed = 1;
        this.position = 0;
        this.refreshInfo();
        requestAnimationFrame(draw(0, this.position));
    }

    refreshInfo() {
        document.getElementById("currentSpeed").innerText = "Current speed: " + rover.speed;
        document.getElementById("currentPosition").innerText = "Current position: " + rover.position;
    }
}

Rover.prototype.startSpeed = 1;

const rover = new Rover();
rover.refreshInfo();


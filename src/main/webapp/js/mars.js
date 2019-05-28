/**
 * Created by User on 04.10.2018.
 */
const cvs = document.getElementById("canvas");
const ctx = cvs.getContext("2d");
const path = document.getElementById("path");
const currentSpeed = document.getElementById("currentSpeed");
const currentPosition = document.getElementById("currentPosition");
const images = ['images/bg.png','images/car.png'];
const loadedImages = {};
const promiseArray = images.map(function(imgurl){
    let prom = new Promise(function(resolve,reject){
        let img = new Image();
        img.onload = function(){
            loadedImages[imgurl] = img;
            resolve();
        };
        img.src = imgurl;
    });
    return prom;
});

const rover = {
    speed : 1,
    position : 0,
    path : "",

    acceleration : function () {
        this.path += "A";
        this.position += this.speed;
        this.speed *= 2;

        let bound = Math.floor(this.position / 15) * 15;
        if (this.speed > 0 && this.position >= bound) {
            requestAnimationFrame(() => {
                draw(bound, this.position)
            });
        } else if (this.speed < 0 && this.position >= bound) {
            requestAnimationFrame(() => {
                draw(bound ,this.position)
            });
        } else {
            requestAnimationFrame(() => {
                draw(0, this.position)
            });
        }
    },

    reverse : function () {
        this.path += "R";
        let bound = Math.floor(this.position / 15) * 15;
        requestAnimationFrame(() => {
            draw(bound, this.position);
        });

        if (this.speed >= 1) {
            this.speed = -1;
        } else {
            this.speed = 1;
        }
    },

    reset : function () {
        this.path = "";
        this.speed = 1;
        this.position = 0;
        requestAnimationFrame(() => {
            draw(0, this.position)
        });
    },

    refreshInfo : function () {
        currentSpeed.innerText = "Current speed: " + rover.speed;
        currentPosition.innerText = "Current position: " + rover.position;
        path.innerText = "Path: " + rover.path;
    }
};

function draw(numberOfFirstBlock, carPosition) {
    let c = carPosition | 0;
    let n = numberOfFirstBlock | 0;

    ctx.drawImage(loadedImages['images/bg.png'], 0, 0);
    ctx.drawImage(loadedImages['images/car.png'], 50 * (c - n), 375, 50, 60);

    ctx.beginPath();
    ctx.moveTo(0, 425);
    ctx.lineTo(cvs.width, 425);
    ctx.fill();

    for (let i = 0, j = n | 0; i < cvs.width; i += 50, j++) {
        ctx.moveTo(i, 425);
        ctx.lineTo(i, 563);
        ctx.font = "20px Georgia";
        ctx.strokeText(j, i+20, 500);
        ctx.fill();
    }
    ctx.stroke();

    rover.refreshInfo();
};

document.getElementById("acceleration").addEventListener('click', (e) => {rover.acceleration();});
document.getElementById("reverse").addEventListener('click', (e) => {rover.reverse();});
document.getElementById("reset").addEventListener('click', (e) => {rover.reset();});

Promise.all(promiseArray).then(draw);


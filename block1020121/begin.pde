void begin() {
        if (bgm_begin.isPlaying() == false) { // play bgm
          bgm_begin.rewind();
          bgm_begin.play();
        }
        cursor();
        background(255);
        fill(100, 181, 193);
        textAlign(CENTER, CENTER);
        textSize(40);
        if (width/2 - 160 < mouseX && mouseX < width/2 + 160 && height*2/5 - 40 < mouseY && mouseY < height*2/5 + 40) {
                if (flag) { // play SE
                        select.rewind();
                        select.play();
                }
                flag = false;
                text("View HighScore", width/2, height*3/5);
                text("Exit", width/2, height*4/5);
                textSize(30);
                text("Config", 80, height-50);
                filter(BLUR, 3);
                textSize(40);
                text("Click to Start", width/2, height*2/5);
                textSize(80);
                text(title, width/2, height/5);
                if (mousePressed == true && mouseButton == LEFT) { // if push LEFT, play SE, load high score, initialize, pause bgm and change scene
                        push.rewind();
                        push.play();
                        high_score = loadHiScore();
                        init();
                        bgm_begin.pause();
                        scene = "game";
                }
        }
        else if (width/2 - 160 < mouseX && mouseX < width/2 + 160 && height*3/5 - 40 < mouseY && mouseY < height*3/5 + 40) {
                if (flag) { // play SE
                        select.rewind();
                        select.play();
                }
                flag = false;
                text("Click to Start", width/2, height*2/5);
                text("Exit", width/2, height*4/5);
                textSize(30);
                text("Config", 80, height-50);
                filter(BLUR, 3);
                textSize(40);
                text("View HighScore", width/2, height*3/5);
                textSize(80);
                text(title, width/2, height/5);
                if (mousePressed == true && mouseButton == LEFT) { // if push LEFT, play SE and change scene
                        push.rewind();
                        push.play();
                        scene = "viewHighscore";
                }
        }
        else if (width/2 - 60 < mouseX && mouseX < width/2 + 60 && height*4/5 - 40 < mouseY && mouseY < height*4/5 + 40) {
                if (flag) { // play SE
                        select.rewind();
                        select.play();
                }
                flag = false;
                text("Click to Start", width/2, height*2/5);
                text("View HighScore", width/2, height*3/5);
                textSize(30);
                text("Config", 80, height-50);
                filter(BLUR, 3);
                textSize(40);
                text("Exit", width/2, height*4/5);
                textSize(80);
                text(title, width/2, height/5);
                if (mousePressed == true && mouseButton == LEFT) { // if push LEFT, play SE and change scene
                        push.rewind();
                        push.play();
                        scene = "escape";
                }
        }
        else if (20 < mouseX && mouseX < 130 && height - 70 < mouseY && mouseY < height - 30) {
                if (flag) { // play SE
                        select.rewind();
                        select.play();
                }
                flag = false;
                text("Click to Start", width/2, height*2/5);
                text("View HighScore", width/2, height*3/5);
                text("Exit", width/2, height*4/5);
                filter(BLUR, 3);
                textSize(30);
                text("Config", 80, height-50);
                textSize(80);
                text(title, width/2, height/5);
                if (mousePressed == true && mouseButton == LEFT) { // if push LEFT, play SE and chabge scene
                        push.rewind();
                        push.play();
                        scene = "config";
                }
        }
        else {
                flag = true; // manage SE
                text("Click to Start", width/2, height*2/5);
                text("View HighScore", width/2, height*3/5);
                text("Exit", width/2, height*4/5);
                textSize(30);
                text("Config", 80, height-50);
                filter(BLUR, 3);
                textSize(80);
                text(title, width/2, height/5);
        }
}

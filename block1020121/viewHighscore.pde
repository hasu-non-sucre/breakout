void viewHighscore() {
        if (bgm_begin.isPlaying() == false) { // play BGM
                bgm_begin.rewind();
                bgm_begin.play();
        }
        background(255);
        fill(100, 181, 193);
        textAlign(CENTER, CENTER);
        textSize(30);
        if (20 < mouseX && mouseX < 130 && 30 < mouseY && mouseY < 70) {
                if (flag) { // play SE
                        select.rewind();
                        select.play();
                }
                flag = false;
                text("Back", 80, 50);
                if (mousePressed == true && mouseButton == LEFT) { // if push LEFT, play SE and change scene
                        push.rewind();
                        push.play();
                        scene = "begin";
                }
        }
        else {
                flag = true; // manage SE
                text("Back", 80, 50);
                filter(BLUR, 3);
        }
        textSize(100);
        text("HighScore:" + String.valueOf(high_score), width/2, height/2);
}

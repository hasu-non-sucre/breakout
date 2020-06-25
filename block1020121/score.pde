void score() {
        if (bgm_score.isPlaying() == false) { // play BGM
                bgm_score.rewind();
                bgm_score.play();
        }
        cursor(); // show cursor
        textAlign(CENTER,CENTER);
        background(255);
        textSize(20);
        score = (int)((50-cnt) * 10000 + (60-fin_time) * 10000);
        if (0 < frameCount%60 && frameCount%60 < 30) { // flash sentence
                fill(0);
                text("Click to Title", width/2, 50);
        }
        textSize(50);
        fill(100, 181, 193);
        if (score > high_score) {  // if score > high score, show high score and store score as high score
                text("Hiscore", width/2, height/4);
                storeHiScore(score);
        }
        else { // show score
                text("Score", width/2, height/4);
        }
        if (cnt == 50 || fin_time == 60) { // show GameOver
                score_str = "0";
                textSize(50);
                text("GameOver", width/2, height*3/4);
        }
        else { // show formula
                score_str = String.valueOf(score);
                textSize(50);
                text(String.valueOf(50-cnt) + " x " + 10000 + " + " + String.format("%.1f", 60-fin_time) + " x " + 10000, width/2, height*3/4);
        }
        textSize(200);
        text(score_str, width/2, height/2);

        if (mousePressed == true && mouseButton == LEFT) { // if push LEFT, load high score, pause BGM and change scene
                init();
                high_score = loadHiScore();
                bgm_score.pause();
                scene = "begin";
        }
}

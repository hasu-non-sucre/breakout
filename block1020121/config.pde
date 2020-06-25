void config() {
        if (bgm_begin.isPlaying() == false) { // play bgm
                bgm_begin.rewind();
                bgm_begin.play();
        }
        background(255);
        fill(100, 181, 193);
        textAlign(CENTER, CENTER);
        textSize(30);
        if (flag_config) { // set slider
                config_bgm.addSlider("volume_bgm")
                .setPosition(150, 300)
                .setRange(-60, 10)
                .setSize(700, 100)
                ;
                config_SE.addSlider("volume_SE")
                .setPosition(150,500)
                .setRange(-60, 10)
                .setSize(700, 100)
                ;
        }
        else { // show slider
                config_bgm.show();
                config_SE.show();
        }
        flag_config = false;
        bgm_begin.setGain(volume_bgm);
        bgm_game.setGain(volume_bgm-10);
        bgm_score.setGain(volume_bgm);
        shot.setGain(volume_SE);
        hit_wall.setGain(volume_SE);
        hit_blocks.setGain(volume_SE);
        select.setGain(volume_SE);
        push.setGain(volume_SE);

        if (20 < mouseX && mouseX < 130 && 30 < mouseY && mouseY < 70) {
                if (flag) { // play SE
                        select.rewind();
                        select.play();
                }
                flag = false;
                text("Back", 80, 50);
                if (mousePressed == true && mouseButton == LEFT) { // if push LEFT, play SE, hide slider and change scene
                        push.rewind();
                        push.play();
                        config_bgm.hide();
                        config_SE.hide();
                        scene = "begin";
                }
        }
        else {
                flag = true; // manage SE
                text("Back", 80, 50);
                filter(BLUR, 3);
        }
        textSize(50);
        text("Sound Config", width/2, height/6);
        textSize(30);
        text("BGM", width/2, 270);
        text("SE", width/2, 470);
}

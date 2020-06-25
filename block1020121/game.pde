void game() {
        noCursor(); // hide cursor
        if (bgm_game.isPlaying() == false) { // play bgm
                bgm_game.rewind();
                bgm_game.play();
        }
        if (keyPressed == true && key == 'r') { // if push 'r', play SE, pause bgm and change scene
                push.rewind();
                push.play();
                bgm_game.pause();
                scene = "begin";
        }
        if (keyPressed == true && key == 'd') {
                cnt_demo++;
        }
        if (cnt_demo == 1) {
          demo1();
        }
        else if (cnt_demo == 10) {
          demo2();
        }

        x += dx;
        y += dy;

        fin = true;
        for(i = 1; i < 5; i++) {
                for(j = 1; j < 11; j++) {
                        if (hp_blocks[i][j] > 0) {
                                fin = false;
                        }
                }
        }

        float tmp = constrain(mouseX, wall, width - wall); // constrain mouseX
        float angle = map(tmp, wall, width - wall, -70, 70); // create angle
        if (x == width/2 && y == height - wall - r/2 && mousePressed == true && mouseButton == LEFT && scene == "game") { // if push LEFT, set ball move, plus count and play SE
                tmp_time = time;
                dx = sin(radians(angle)) * 20;
                dy = cos(radians(angle)) * -20;
                cnt++;
                shot.rewind();
                shot.play();
        }
        if (x < wall + r/2) { // reflect and play SE
                hit_wall.rewind();
                hit_wall.play();
                x = wall + r/2;
                dx *= -1;
        }
        if (x > width - r/2 - wall) { // reflect and play SE
                hit_wall.rewind();
                hit_wall.play();
                x = width - r/2 - wall;
                dx *= -1;
        }
        if (y < r/2 + wall) { // reflect and play SE
                hit_wall.rewind();
                hit_wall.play();
                y = r/2 + wall;
                dy *= -1;
        }
        if (y + r/2 > height) { // finish the action
                x = width/2;
                y = height - wall - r/2;
                dx = 0;
                dy = 0;
        }

        time = String.format("%.1f", frameCount/60.);
        if ((fin == true || cnt == 40 || Double.parseDouble(time) > 60) && x == width/2 && y == height - wall - r/2 && Double.parseDouble(tmp_time) < Double.parseDouble(time)) { // if finish the game, change scene
                if (Double.parseDouble(time) > 60) {
                        fin_time = 60;
                }
                else {
                        fin_time = Double.parseDouble(time);
                }
                bgm_game.pause();
                scene = "score";
        }

        // println(angle);

        background(#202060);
        fill(255);
        circle(width/2 + sin(radians(angle)) * 50, height - wall - r/2 - cos(radians(angle)) * 50, 5); // aim
        existBlocks(); // exist or unexist row block
        checkBlocks(); // whether block color is black
        dynBlocks(); // check reflect
        showBlocks(); // show blocks
        fill(0);
        noStroke();
        circle(x, y, r); // show ball
        fill(#e0f0f0);
        showWalls();
        textSize(20);
        fill(0);
        text("count:" + (40-cnt), 70, 20);
        textAlign(CENTER, CENTER);

        text("time:" + String.format("%.1f", 60-Double.parseDouble(time)), width/2, 20);
}

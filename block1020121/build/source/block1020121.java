import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class block1020121 extends PApplet {

int i, j;
float dx = 0,
      dy = 0;
float x,
      y,
      r = 18;
int wall = 40;
float block_width, block_heigth;
boolean[][] check_blocks = new boolean[5][12];
boolean[][] exist_blocks = new boolean[5][12];
int[][] hp_blocks = new int[5][12];
int cnt = 0;
boolean fin;
String scene = "begin";
String time;
double fin_time;

Minim minim;
AudioPlayer shot;
AudioPlayer hit_wall;
AudioPlayer hit_blocks;
AudioPlayer select;
AudioPlayer push;
AudioPlayer bgm_begin;
AudioPlayer bgm_game;
AudioPlayer bgm_score;
String tmp_time;
String score_str;
int score;
int high_score;
boolean flag = true;

ControlP5 config_bgm;
ControlP5 config_SE;
int volume_bgm = -50;
int volume_SE = -40;
boolean flag_config = true;
String title = "Through and Break";
boolean flag_exit = true;
int cnt_demo = 0;

public void setup() {
        
        x = width/2;
        y = height - wall - r/2;
        block_width = (width - wall*2) / 12;
        block_heigth = block_width;
        high_score = loadHiScore();
        init();
        minim = new Minim(this);
        shot = minim.loadFile("knife-throw1.mp3");
        hit_wall = minim.loadFile("billiard-ball1.mp3");
        hit_blocks = minim.loadFile("glass-break4.mp3");
        select = minim.loadFile("button47.mp3");
        push = minim.loadFile("button57.mp3");
        bgm_begin = minim.loadFile("Morning.mp3");
        bgm_game = minim.loadFile("Sound_Wave.mp3");
        bgm_score = minim.loadFile("South_Wind.mp3");
        bgm_begin.setGain(-50);
        bgm_game.setGain(-60);
        bgm_score.setGain(-50);
        shot.setGain(-40);
        hit_wall.setGain(-40);
        hit_blocks.setGain(-40);
        select.setGain(-40);
        push.setGain(-40);
        config_bgm = new ControlP5(this);
        config_SE = new ControlP5(this);
}

public void draw() {
        if (scene == "begin") { // change scene
                begin();
        }
        else if (scene == "game") { // change scene
                game();
        }
        else if (scene == "score") { // change scene
                score();
        }
        else if (scene == "viewHighscore") { // change scene
                viewHighscore();
        }
        else if (scene == "config") { // change scene
                config();
        }
        else if (scene == "escape") { // change scene
                escape();
        }
}

public void mouseClicked() { // check info
        if (scene == "game") {
                if (mouseButton == RIGHT) {
                        println("check_blocks");
                        for(i = 1; i < 5; i++) {
                                for(j = 1; j < 11; j++) {
                                        print(PApplet.parseInt(check_blocks[i][j]), " ");
                                }
                                println();
                        }
                        println("exist_blocks");
                        for(i = 1; i < 5; i++) {
                                for(j = 1; j < 11; j++) {
                                        print(PApplet.parseInt(exist_blocks[i][j]), " ");
                                }
                                println();
                        }
                        println("hp_blocks");
                        for(i = 1; i < 5; i++) {
                                for(j = 1; j < 11; j++) {
                                        print(hp_blocks[i][j], " ");
                                }
                                println();
                        }
                        println("fin:", fin);
                }
        }
}
public void begin() {
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
public void checkBlocks() { // manage block color
        for(i = 1; i < 5; i++) {
                if (exist[i] == false) {
                        for(j = 1; j < 11; j++) {
                                float block_x = wall + block_width * j;
                                float block_y = wall + block_width * i;
                                if (block_x < x && x < block_x + block_width &&
                                    block_y < y && y < block_y + block_heigth) {
                                        if (hp_blocks[i][j] > 0) {
                                                check_blocks[i][j] = true;
                                        }
                                }
                        }
                }
        }
}
public void config() {
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
public void demo1() {
  for(j = 1; j < 11; j++) {
    check_blocks[1][j] = true;
  }
}

public void demo2() {
  for(i = 1; i < 5; i++) {
    for(j = 1; j < 11; j++) {
      if (i == 4 && j == 10) {
        break;
      }
      hp_blocks[i][j] = 0;
      check_blocks[i][j] = true;
    }
  }
}
public void escape() {
        if (flag_exit) { // manage frameCount
                frameCount = 0;
        }
        background(255);
        textAlign(CENTER,CENTER);
        fill(0);
        text("Bye", width/2, height/2);
        if (frameCount == 60) { // if 1s passes, exit
                exit();
        }
        flag_exit = false; 
}
boolean[] exist = {false, false, false, false, false};

public void existBlocks() { // manage block exist
        for(i = 1; i < 5; i++) {
                boolean tmp = true;
                for(j = 1; j < 11; j++) {
                        if (check_blocks[i][j] == false) {
                                tmp = false;
                        }
                }
                if (tmp == true) {
                        exist[i] = true;
                }
        }
        for(i = 1; i < 5; i++) {
                for(j = 1; j < 11; j++) {
                        if (exist[i] == true) {
                                exist_blocks[i][j] = true;
                        }
                }
        }
}
public void game() {
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

        time = String.format("%.1f", frameCount/60.f);
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

        background(0xff202060);
        fill(255);
        circle(width/2 + sin(radians(angle)) * 50, height - wall - r/2 - cos(radians(angle)) * 50, 5); // aim
        existBlocks(); // exist or unexist row block
        checkBlocks(); // whether block color is black
        dynBlocks(); // check reflect
        showBlocks(); // show blocks
        fill(0);
        noStroke();
        circle(x, y, r); // show ball
        fill(0xffe0f0f0);
        showWalls();
        textSize(20);
        fill(0);
        text("count:" + (40-cnt), 70, 20);
        textAlign(CENTER, CENTER);

        text("time:" + String.format("%.1f", 60-Double.parseDouble(time)), width/2, 20);
}
public void storeHiScore(int hi_score) {
        String lines [] = new String[1];
        lines[0] = str(hi_score);
        saveStrings("data.txt", lines); // save high score
}

public int loadHiScore() {
        String lines[];
        lines = loadStrings("data.txt");
        if (lines == null) { // if data.txt is unlnown
                return 0;
        }
        else {
                return PApplet.parseInt(lines[0]);
        }
}
public boolean hitBlocks(float x1, float y1, float w1, float h1,
                  float x2, float y2, float w2, float h2) { // check hit
        return x1 < x2+w2 && x2 < x1+w1 && y1 < y2+h2 && y2 < y1+h1;
}

public int hitCheck(float x, float y, float w, float h,
             float bx, float by, float bw, float bh, float dx, float dy) { // check where hitted
        int xflag = 0, yflag = 0;

        if (!hitBlocks(x,y,w,h,bx+dx,by+dy,bw,bh)) {
                return 0;
        }

        if (hitBlocks(x,y,w,h,bx+dx,by,bw,bh)) {
                xflag = 1;
        }
        if (hitBlocks(x,y,w,h,bx,by+dy,bw,bh)) {
                yflag = 2;
        }
        return xflag + yflag;
}

public void dynBlocks() { // manage ball move
        for (i = 1; i < 5; i++) {
                for(j = 1; j < 11; j++) {
                        if (exist_blocks[i][j] == true && hp_blocks[i][j] > 0) {
                                int ret = hitCheck(wall + block_width * j, wall + block_width * i, block_width, block_heigth, x-r/2, y-r/2, r, r, dx, dy);
                                if (ret > 0) {
                                        if (ret == 1) {
                                                dx = -dx;
                                        }
                                        else if (ret == 2) {
                                                dy = -dy;
                                        }
                                        else if (ret == 3) {
                                                dx = -dx;
                                                dy = -dy;
                                        }
                                        hp_blocks[i][j]--; // minus hp
                                        hit_blocks.rewind(); // play SE
                                        hit_blocks.play();
                                        return;
                                }
                        }
                }
        }
}
public void init() { // initialize blocks
        x = width/2;
        y = height - wall - r/2;
        for(i = 1; i < 5; i++) {
                for(j = 1; j < 11; j++) {
                        hp_blocks[i][j] = 1;
                        check_blocks[i][j] = false;
                        exist_blocks[i][j] = false;
                        exist[i] = false;
                        cnt = 0;
                        frameCount = 0;
                }
        }
}
public void score() {
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
public void showBlocks() { // show blocks
        for(i = 1; i < 5; i++) {
                for(j = 1; j < 11; j++) {
                        if (hp_blocks[i][j] > 0) {
                                float block_x = wall + block_width * j;
                                float block_y = wall + block_width * i;
                                stroke(0);
                                fill(255);
                                if (check_blocks[i][j] == true) {
                                        fill(0);
                                        stroke(255);
                                }
                                rect(block_x, block_y, block_width, block_heigth);
                        }
                }
        }
}
public void showWalls() { // show wall
        rect(0, 0, wall, height); // wall
        rect(0, 0, width, wall); // wall
        rect(width-wall, 0, wall, height); // wall
        rect(0, height-wall, width, wall); // wall
}
public void viewHighscore() {
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
  public void settings() {  size(1000, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "block1020121" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

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
import ddf.minim.*;
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
import controlP5.*;
ControlP5 config_bgm;
ControlP5 config_SE;
int volume_bgm = -50;
int volume_SE = -40;
boolean flag_config = true;
String title = "Through and Break";
boolean flag_exit = true;
int cnt_demo = 0;

void setup() {
        size(1000, 800);
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

void draw() {
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

void mouseClicked() { // check info
        if (scene == "game") {
                if (mouseButton == RIGHT) {
                        println("check_blocks");
                        for(i = 1; i < 5; i++) {
                                for(j = 1; j < 11; j++) {
                                        print(int(check_blocks[i][j]), " ");
                                }
                                println();
                        }
                        println("exist_blocks");
                        for(i = 1; i < 5; i++) {
                                for(j = 1; j < 11; j++) {
                                        print(int(exist_blocks[i][j]), " ");
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

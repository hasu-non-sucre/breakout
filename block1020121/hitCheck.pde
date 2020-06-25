boolean hitBlocks(float x1, float y1, float w1, float h1,
                  float x2, float y2, float w2, float h2) { // check hit
        return x1 < x2+w2 && x2 < x1+w1 && y1 < y2+h2 && y2 < y1+h1;
}

int hitCheck(float x, float y, float w, float h,
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

void dynBlocks() { // manage ball move
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

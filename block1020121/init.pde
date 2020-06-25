void init() { // initialize blocks
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

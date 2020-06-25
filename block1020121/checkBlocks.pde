void checkBlocks() { // manage block color
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

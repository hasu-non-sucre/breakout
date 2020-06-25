void showBlocks() { // show blocks
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

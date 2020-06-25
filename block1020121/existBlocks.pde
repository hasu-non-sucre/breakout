boolean[] exist = {false, false, false, false, false};

void existBlocks() { // manage block exist
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

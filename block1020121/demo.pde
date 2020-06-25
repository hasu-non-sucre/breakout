void demo1() {
  for(j = 1; j < 11; j++) {
    check_blocks[1][j] = true;
  }
}

void demo2() {
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

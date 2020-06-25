void showWalls() { // show wall
        rect(0, 0, wall, height); // wall
        rect(0, 0, width, wall); // wall
        rect(width-wall, 0, wall, height); // wall
        rect(0, height-wall, width, wall); // wall
}

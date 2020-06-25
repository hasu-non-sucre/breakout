void escape() {
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

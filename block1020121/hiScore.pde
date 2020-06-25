void storeHiScore(int hi_score) {
        String lines [] = new String[1];
        lines[0] = str(hi_score);
        saveStrings("data.txt", lines); // save high score
}

int loadHiScore() {
        String lines[];
        lines = loadStrings("data.txt");
        if (lines == null) { // if data.txt is unlnown
                return 0;
        }
        else {
                return int(lines[0]);
        }
}

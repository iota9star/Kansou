package star.iota.kansou;

interface Contracts {
    interface Menu {
        String INDEX = "放送開始予定作品";
        int INDEX_ID = 1;
        String INDEX_URL = "http://www.kansou.me/";

        String ANIMEKA = "媒体不明(新作)";
        int ANIMEKA_ID = 2;
        String ANIMEKA_URL = "http://www.kansou.me/animeka/";

        String MOVIE = "新作劇場版アニメ";
        int MOVIE_ID = 3;
        String MOVIE_URL = "http://www.kansou.me/animeka/movie.html";

        String OVA = "新作OVA";
        int OVA_ID = 4;
        String OVA_URL = "http://www.kansou.me/animeka/ova.html";

        String OAD = "新作OAD付コミック";
        int OAD_ID = 5;
        String OAD_URL = "http://www.kansou.me/animeka/oad.html";

        String BOX = "アニメBOX発売予定";
        int BOX_ID = 6;
        String BOX_URL = "http://www.kansou.me/animeka/box.html";
    }

    interface Type {
        int TWO = 2;
        int THREE = 3;
    }
}

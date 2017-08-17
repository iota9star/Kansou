package star.iota.kansou;


interface PVContract {
    interface Presenter {
        void unsubscribe();

        void get(int type, String url);
    }

    interface View {

        void success(Bean result);

        void error(String error);

    }
}

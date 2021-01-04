public class Loader implements Runnable {
    private Loading loader;
    private Thread t;

    public Loader(){
        Loading loading=new Loading();
        loading.setText("Please wait ... Training Neural Network");
        loading.setTitle("Please wait");
        loading.setVisible(false);
        loader=loading;
    }

    @Override
    public void run(){
        loader.setVisible(true);
    }

    public void start(){
        t=new Thread(this, "loader");
        t.start();
    }

    public Thread  getThread(){
        return t;
    }

    public Loading getObject(){
        return loader;
    }
}

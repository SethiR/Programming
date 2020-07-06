interface Player{
    void play();
    void stop();
    void pause();
    void rewind();
}

interface PhysicalPlayer extends Player {
    void switchOff();
}

class DVDPlayer implements  PhysicalPlayer{

    @Override
    public void play() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void rewind() {
    }

    public void switchOff(){}
}



class IphoneMediaApp implements  Player{

    @Override
    public void play() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void rewind() {
    }

    public void install(){}

    public void uninstall(){}
}


public class Main9 {
    public static void main(String[] args) {
        PhysicalPlayer myDVDplayer = new DVDPlayer();
        myDVDplayer.switchOff();
    }
}

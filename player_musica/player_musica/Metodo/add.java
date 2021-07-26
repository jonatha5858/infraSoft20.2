package Metodo;

import Classes.Musica;
import Classes.playList;

public class adicionar extends lock implements Runnable {
    private final playList playlist ;
    private final Musica list ;
    private final int selectedMusicInd;

    public adicionar(playList playlist, Musica list, int index){
        this.selectedMusicInd = index;
        this.list = list;
        this.playlist=playlist;                             
    }
    public void run() {
        getAcessLock().lock();
        try {
            while (isOcupied()) getCondition().await();
            setOcupied(true);
            playlist.addMusic(list.getMus(selectedMusicInd));
            setOcupied(false);
            getCondition().signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}

package Metodo;

import Classes.playList;

public class remover extends lock implements Runnable {
    private final playList playlist;
    private final int selectedMusicInd;

    public remover(int index, playList playlist) {
        this.selectedMusicInd = index;               
        this.playlist = playlist;        
    }
    public void run() {
        getAcessLock().lock();
        try {
            while (isOcupied()) getCondition().await();
            setOcupied(true);
            playlist.removeMusic(selectedMusicInd);
            setOcupied(false);
            getCondition().signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}
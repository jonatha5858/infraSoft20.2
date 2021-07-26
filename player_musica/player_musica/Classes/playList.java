package Classes;

import java.util.ArrayList;

public class playList {
    private final ArrayList<Musica>playlist;
   
    public playList(){ 
        this.playlist= new ArrayList<>();
    }

    public void addMusic(Music music){ 
        playlist.add(music);
        System.out.println(music.getMusic()+" Adicionada com sucesso!");
    }

    public void removeMusic (int i){ 
        if(playlist.isEmpty()){
            System.out.println("A playList está vazia");
        }

        else if(i<0||i>playlist.size()-1){ 
            System.out.println("Música não encontrada!");
        }else{
            System.out.println(playlist.get(i).getMusic()+" removida com sucesso!");
            playlist.remove(i);
        }

    }
    public void getPlaylistString() { 
        for (int i =0;i<playlist.size();i++){
            System.out.println(i+"-"+playlist.get(i).getMusic());
        }

    {
   

    
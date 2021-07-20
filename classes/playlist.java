package Classes;

import java.util.ArrayList;

public class playList {
    private final l_Array<music>playlist;

    //construtor play
    public playList(){ 
        this.playlist= new l_Array<>();
    }
    //adicionar música no l_array
    public void addMusic(music musica){ 
        playlist.add(musica);
        System.out.println(musica.getNomeMusica()+" foi Adicionada com sucesso");
    }
    public void removeMusic (int i){ //remover música do arrayList
        if(playlist.isEmpty()){
            System.out.println("A playList está vazia");
        }else if(i<0||i>playlist.size()-1){ //checa se o index remoção é válido
            System.out.println("não encontrado!");
        }else{
            System.out.println(playlist.get(i).getNomeMusica()+" foi removida com sucesso!");
            playlist.remove(i);
        }
    }

    public void getPlaylistString() { //printa a playList atual
        for (int i =0;i<playlist.size();i++){
            System.out.println(i+"-"+playlist.get(i).getNomeMusica());
        }
    }


}
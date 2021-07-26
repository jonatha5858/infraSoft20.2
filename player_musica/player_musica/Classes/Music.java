package Classes;

public class Musica {
    private String Music;
    private String Album;
    private String Singer;

    public Musica(String Music, String Album, String Singer){
        this.Music = Music;
        this.Album = Album;
        this.Singer = Singer;
    }

    public String getMusic(int selectedMusicInd) {
        return Music;
    }

    public String getSingerr() {
        return Singer;
    }

    public String getAlbum(){
        return Album;
    }

}


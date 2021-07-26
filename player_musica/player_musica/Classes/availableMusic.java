package Classes;

public class availableMusics {
   public Music[] musics = new Music[20];

   public availableMusics() {
       for (int i =0;i < 20;i++){
           musics[i] = new music("Chlorine" + i, "Twenty One Pilots");
       }
   }
   public music getMusic(int index){
       return this.musics[index];
   }
   public String getMusic(int index){
       return this.musics[index].getMusic();
   }
}
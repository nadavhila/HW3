import java.util.Objects;

public class Song implements Cloneable{
    private final String  name;
    private final String artist;
    private final Genre genre;
    private final int duration;

    public Song(String name, String artist, Genre genre,int duration) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return name+","+artist+","+secondToMin(duration);
    }
    public String secondToMin(int duration){
        int min=duration%60;
        int sec=duration-(min*60);
        if(sec<10)
            return min+":"+"0"+sec;
        return min+":"+sec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return name.equals(song.name) && artist.equals(song.artist);
    }

    @Override
    public int hashCode() {
        return (name).hashCode()+(artist).hashCode();
    }
    @Override
    public Song clone(){
        try{
            return (Song)super.clone();
        }catch (CloneNotSupportedException e){
            return null;
        }
    }
    public enum Genre{
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO;
    }
}
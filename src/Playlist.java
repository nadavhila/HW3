import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Playlist implements Cloneable {
    private List<Song> songsList;

    public Playlist() {
        this.songsList = new Vector<>();
    }
    public void addSong(Song song){
        if(songsList.size()==0)
            songsList.add(song);
        else
            for(Song s1:songsList){
                if(song.equals(s1))
                    throw new SongAlreadyExistsException();
            }
        songsList.add(song);
    }
    public boolean removeSong(Song song){
        return songsList.remove(song);
    }

    @Override
    public String toString() {
        return songsList.toString();
    }
    @Override
    public Playlist clone(){
        try{
            Playlist copy=(Playlist) super.clone();
            copy.songsList.clear();
            for(Song s: songsList){
                copy.songsList.add(s.clone());
            }
            return copy;
        }catch(CloneNotSupportedException e){
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist1 = (Playlist) o;
        if (playlist1.songsList.size()!=songsList.size())
            return false;
        int counter=0;
        List<Song> otherP=playlist1.songsList;
        for(Song s1:songsList){
            for(Song s2:otherP)
                if(s1.equals(s2))
                    counter++;
        }
        return counter==songsList.size();
    }
    @Override
    public int hashCode() {
        int hash=0;
        for(Song s:songsList)
            hash+=s.hashCode();
        return hash;
    }
}
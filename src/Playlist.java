import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * represent a playlist of songs
 */
public class Playlist implements Cloneable,FilteredSongIterable,OrderedSongIterable,Iterable<Song> {
    private Vector<Song> songsList;
    private Filter filter;

    /**
     * constructor that initialize the attributes
     */
    public Playlist() {
        this.songsList = new Vector<>();
        this.filter=new Filter();
    }

    /**
     * adding a song to the vector
     * @param song a song that we want to add
     * @throws SongAlreadyExistsException if the song is already in
     */
    public void addSong(Song song) {
        if (songsList.size() == 0) {
            songsList.add(song);
            song.setAdded(songsList.size());
            return;
        } else
            for (Song s1 : songsList) {
                if (song.equals(s1))
                    throw new SongAlreadyExistsException();
            }
        songsList.add(song);
        song.setAdded(songsList.size());
    }

    /**
     * remove the song from the vector
     * @param song the song that we want to remove
     * @return true if the removal succeeds false if it isnt
     */
    public boolean removeSong(Song song) {
        return songsList.remove(song);
    }

    /**
     * represent the vector as a list of songs
     * @return the list of the songs as string
     */
    @Override
    public String toString() {
        StringBuilder str= new StringBuilder("[");
        for(Song s: songsList) {
            str.append("(").append(s).append("), ");
        }
        return str.substring(0,str.length()-2)+"]";
    }

    /**
     * give an Iterator of the playlist
     * @return Iterator of the playlist
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * clone the playlist by a deep clone
     * @return a deep clone of the playlist
     */
    @Override
    public Playlist clone() {
        try {
            Playlist copy = (Playlist) super.clone();
            copy.songsList= (Vector<Song>) this.songsList.clone();
            int i=0;
            for (Song s : copy.songsList) {
                copy.songsList.set(i++,s.clone());
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * check if two playlists are equal
     * @param o the playlist we want to do the check with
     * @return true if they are equal, false if they arent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist1 = (Playlist) o;
        if (playlist1.songsList.size() != songsList.size())
            return false;
        int counter = 0;
        List<Song> otherP = playlist1.songsList;
        for (Song s1 : songsList) {
            for (Song s2 : otherP)
                if (s1.equals(s2))
                    counter++;
        }
        return counter == songsList.size();
    }

    /**
     * gives a hashcode for a playlist
     * @return a number that is the hashcode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (Song s : songsList)
            hash += s.hashCode();
        return hash;
    }

    /**
     * sets the filter by artist
     * @param artist the artist that we are doing filter by
     */
    @Override
    public void filterArtist(String artist) {
         this.filter.setFilterArtist(artist);
    }

    /**
     * sets the filter by genre
     * @param genre the genre that we are doing filter by
     */
    @Override
    public void filterGenre(Song.Genre genre) {
        this.filter.setFilterGenre(genre);
    }

    /**
     * sets the filter by duration
     * @param duration the duration that we are doing filter by
     */
    @Override
    public void filterDuration(int duration){
        this.filter.setFilterDuration(duration);
    }

    /**
     * set the order of the scaning
     * @param s enum that represent the order type
     */
    @Override
    public void setScanningOrder(ScanningOrder s) {
        if (s== ScanningOrder.ADDING) {
            Comparator<Song> comp = Comparator.comparing(Song::getAdded);// sorting by the index of the adding(when the object added)
            songsList.sort(comp);
        } else if (s == ScanningOrder.DURATION) {
            Comparator<Song> comp = Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist);
            //sorting by the duration and if 2 songs has the same duration the sorting by the artists name
            songsList.sort(comp);
        } else {
            Comparator<Song> comp = Comparator.comparing(Song::getName).thenComparing(Song::getArtist);
            //sorting by the name of the song and if 2 songs have the same name then the sorting is by the artist name
            songsList.sort(comp);
        }
    }

    /**
     * a class that represents the playlist iterator
     */
    class PlaylistIterator implements Iterator<Song>{
        private Iterator<Song> it;//the vector iterator
        private Song nextElement;//the next song

        /**
         * the constructor of the playlist iterator
         */
        public PlaylistIterator() {
            this.it =songsList.iterator();
        }

        /**
         * checks if there is a next element to the playlist
         * @return true if there is a next element else false
         */
        @Override
        public boolean hasNext() {
            while (it.hasNext()) {
                this.nextElement = it.next();//set the next element
                if(filter.checkFilter(nextElement))// checks if the song match the filter
                    return true;
            }
            filter.setDurFlag(false);// if there is no next element or we finished iterating the list we turn off the duration flag of the filter
            // the duration flag represents if someone set a filter by duration
            return false;
        }

        /**
         * retrieve the next song of the playlist
         * @return the next song
         */
        @Override
        public Song next() {
            return this.nextElement;
        }
    }
}
/**
 * represent an object a song
 */
public class Song implements Cloneable {
    private final String name;
    private final String artist;
    private  Genre genre;
    private  int duration;
    private int added;// the number of the adding of the song the first time

    /**
     * give the name of the song
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * give the artist of the song
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * give the genre of the song
     * @return give the genre of the song
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * give the duration of the song
     * @return give the duration of the song
     */
    public int getDuration() {
        return duration;
    }

    /**
     * a constructor of the song
     * @param name the name of the song
     * @param artist the artist of the song
     * @param genre the genre of the song
     * @param duration the duration of the song
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    /**
     * sets the added attribute
     * @param added the number of the adding of the song the first time
     */
    public void setAdded(int added) {
        this.added = added;
    }

    /**
     * gives the added attribute
     * @return the added attribute
     */
    public int getAdded() {
        return added;
    }

    /**
     * gives the song as a string
     * @return a string that represent the song
     */
    @Override
    public String toString() {
        return name + ", " + artist + ", " + genre+", "+ secondToMin(duration);
    }

    /**
     * convert the duration to min:sec presentation
     * @param duration the duration attribute of the song
     * @return min:sec presentation of the duration
     */
    public String secondToMin(int duration) {
        int min = duration / 60;
        int sec = duration - (min * 60);
        if (sec < 10)
            return min + ":" + "0" + sec;
        return min + ":" + sec;
    }

    /**
     * check if one song is equal to another by its name and artist
     * @param o a song object
     * @return boolean, true if equal and false if it isnt
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return name.equals(song.name) && artist.equals(song.artist);
    }

    /**
     * gives a hashcode to a song by its name and artist
     * @return a number that is the hashcode
     */
    @Override
    public int hashCode() {
        return (name).hashCode() + (artist).hashCode();
    }

    /**
     * a function that clone the song
     * @return a deep copy of the song
     */
    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * sets the duration attribute
     * @param duration set the duration attribute of the song
     */
    public void setDuration(int duration) {
        this.duration=duration;
    }

    /**
     * represent the genre of the song
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO;
    }
}
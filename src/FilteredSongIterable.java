/**
 * an interface that defines that every class that implements it is iterable by a type of filter
 */
public interface FilteredSongIterable extends Iterable<Song>{
    /**
     * set filter by the artist of the song
     * @param artist the artist that we are doing filter by
     */
    void filterArtist(String artist);

    /**
     * set filter by the genre of the song
     * @param genre the genre that we are doing filter by
     */
    void filterGenre(Song.Genre genre);

    /**
     * set filter by the duration of the song
     * @param duration the duration that we are doing filter by
     */
    void filterDuration(int duration);
}

/**
 * a class that represent the filters
 */
public class Filter {
    private String filterArtist;
    private Song.Genre filterGenre;
    private int filterDuration;
    private boolean durFlag;//check if we called by a filter of duration or not

    /**
     * initialize the filter by artist
     * @param filterArtist the artist which is the filter
     */
    public void setFilterArtist(String filterArtist) {
        this.filterArtist = filterArtist;
    }

    /**
     * initialize the filter by genre
     * @param filterGenre the genre which is the filter
     */
    public void setFilterGenre(Song.Genre filterGenre) {
        this.filterGenre = filterGenre;
    }

    /**
     * initialize the filter by duration
     * @param filterDuration the duration which is the filter
     */
    public void setFilterDuration(int filterDuration) {
        this.filterDuration = filterDuration;
        this.durFlag=true;
    }

    /**
     * initialize the flag attribute
     * @param durFlag a boolean true or false
     */
    public void setDurFlag(boolean durFlag) {
        this.durFlag = durFlag;
    }

    /**
     * checks which filter need to be used
     * @param song the current song
     * @return boolean to know if the song is good according to the filter or not
     */
    public boolean checkFilter(Song song){
        if(filterArtist!=null)
            if(!(filterArtist.equals(song.getArtist())))
                return false;
        if(filterGenre!=null)
            if(filterGenre!=song.getGenre())
                return false;
        if(durFlag)
          if(filterDuration<song.getDuration()){
              return false;
          }

        return true;
    }
}

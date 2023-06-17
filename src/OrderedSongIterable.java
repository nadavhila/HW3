/**
 * an interface that defines that every class that implements it is iterable by a scanning order
 */
public interface OrderedSongIterable extends Iterable<Song>{
    /**
     * defines the order of the scanning
     * @param s enum that represent the order type
     */
    void setScanningOrder(ScanningOrder s);
}

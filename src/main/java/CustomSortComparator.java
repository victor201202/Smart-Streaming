import java.util.Comparator;

public class CustomSortComparator implements Comparator<Stream> {
    @Override
    public int compare(Stream o1, Stream o2) {
        if (o1.getDateAdded() / 86400 < o2.getDateAdded() / 86400)
            return 1;
        else if (o1.getDateAdded() / 86400 > o2.getDateAdded() / 86400)
            return -1;
        else {
            if(o1.getNoOfStreams() > o2.getNoOfStreams())
                return -1;
            else
                return 1;
        }
    }
}

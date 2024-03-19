public abstract class Stream {
    String type;

    abstract public Integer getStreamerId();
    abstract public Integer getId();
    abstract public String getName();
    abstract public Long getNoOfStreams();
    abstract public Long getLength();
    abstract public Long getDateAdded();
    abstract public void setNoOfStreams(Long noOfStreams);
}

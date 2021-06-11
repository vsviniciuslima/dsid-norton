import java.util.LinkedHashMap;

public interface Part {

    public Long getId();
    public String getName();
    public String getDescription();
    public LinkedHashMap<Long, Part> getSubParts();
}

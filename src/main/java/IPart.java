import java.util.LinkedHashMap;

public interface IPart {

    public Long getId();
    public String getName();
    public String getDescription();
    public String getRepository();
    public LinkedHashMap<Long, IPart> getSubParts();
    public boolean clearSubParts();
    public boolean addSubParts(IPart part);
}

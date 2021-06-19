import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public interface IPart {

    public int getId();
    public String getName();
    public String getDescription();
    public String getRepository();
    public Map<Long, Part> getSubParts();
    public void clearSubParts();
    public void addSubParts(Long quantity, Part part);
}

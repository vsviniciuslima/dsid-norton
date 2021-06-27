import java.util.Map;
import java.util.Set;

public interface IPart {

    Long getId();
    String getName();
    String getDescription();
    String getRepository();
    Set<Long[]> getSubParts();
    void clearSubParts();
    void addSubParts(Long quantity, Long partId);
}

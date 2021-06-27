import java.util.Set;

public interface IPart {

    // Getters
    Long getId();
    String getName();
    String getDescription();
    String getRepository();
    Set<SubpartContainer> getSubParts();

    // Métodos auxiliares
    void setRepository(String repository);
    void clearSubParts();
    void addSubParts(Long quantity, Part part);
    void printPartInfo();
}

import java.io.Serializable;
import java.util.*;

public class Part implements IPart, Serializable {

    private final Long id;
    private String name;
    private final String description;
    private String repository;
    private final Set<Long[]> subParts = new HashSet<>();
    private boolean isOnAnotherRepository = false;

    public Part(String name, String description, String repository, String serverprefix) {
        Long tempId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.id = Long.valueOf(serverprefix + tempId.toString().substring(0, 5));
        this.name = name;
        this.description = description;
        this.repository = repository;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getRepository() {
        return repository;
    }
    public Set<Long[]> getSubParts() {
        return subParts;
    }
    public boolean checkIfPartWasAddedToAnotherRepository() { return this.isOnAnotherRepository; }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public void clearSubParts() {
        this.subParts.clear();
    }
    public void addSubParts(Long quantity, Long partId) {
        this.subParts.add(new Long[]{quantity, partId});
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPartIsOnAnotherRepository() { this.isOnAnotherRepository = true; }

    public void printPartInfo() {
        String partType = this.subParts.size() == 0 ? "primitiva" : "agregada";

        System.out.println("Nome da peça: " + this.getName());
        System.out.println("Descrição da peça: " + this.getDescription());
        System.out.println("Tipo de peça: " + partType);
        System.out.println("Subcomponentes: " + this.subParts.size());
    }
}

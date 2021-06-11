import java.util.LinkedHashMap;

public class Part implements IPart {

    public Long id;
    public String name;
    public String description;
    public String repository;
    public LinkedHashMap<Long, Part> subParts = new LinkedHashMap<>();

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
    public LinkedHashMap<Long, Part> getSubParts() {
        return subParts;
    }
    public void clearSubParts() {
        this.subParts.clear();
    }
    public void addSubParts(Long quantity, Part part) {
        this.subParts.put(quantity, part);
    }
}

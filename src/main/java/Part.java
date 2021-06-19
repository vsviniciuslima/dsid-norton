import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Part implements IPart, Serializable {

    public Part(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Part(int id, String name, String description, String repository, LinkedHashMap<Long, Part> subParts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.repository = repository;
        this.subParts = subParts;
        this.identifier = UUID.randomUUID().getMostSignificantBits();
    }

    public Part() {}

    public int id;
    public String name;
    public String description;
    public String repository;
    public LinkedHashMap<Long, Part> subParts = new LinkedHashMap<>();

    public Long identifier;

    public int getId() {
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

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", repository='" + repository + '\'' +
                ", subParts=" + subParts +
                ", uuid=" + identifier +
                '}';
    }


}

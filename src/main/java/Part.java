import java.util.LinkedHashMap;

public class Part implements IPart {

    public Long id;
    public String name;
    public String description;
    public String repository;
    public LinkedHashMap<Long, IPart> subParts = new LinkedHashMap<Long, IPart>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getRepository() {
        return repository;
    }

    @Override
    public LinkedHashMap<Long, IPart> getSubParts() {
        return subParts;
    }

    @Override
    public boolean clearSubParts() {
        this.subParts.clear();
    }

    @Override
    public boolean addSubParts(Long quantity, IPart part) {
        return this.subParts.put(quantity, part);
    }
}

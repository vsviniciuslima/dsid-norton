package model;

import java.io.Serializable;
import java.util.*;

public class Part implements IPart, Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private String repository;
    private Set<SubpartContainer> subParts = new HashSet<>();

    // Getters
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
    public Set<SubpartContainer> getSubParts() {
        return subParts;
    }

    // Métodos auxiliares
    public void setRepository(String repository) {
        this.repository = repository;
    }
    public void clearSubParts() {
        this.subParts.clear();
    }
    public void addSubParts(Long quantity, Part part) {
        this.subParts.add(new SubpartContainer(quantity, part));
    }
    public void printPartInfo() {
        String partType = this.subParts.size() == 0 ? "primitiva" : "agregada";

        System.out.println("Nome: " + this.getName() + ", descrição: " + this.getDescription());
        System.out.println("Servidor: " + this.repository);
        System.out.println("Tipo de peça: " + partType);
        if(partType.equals("agregada")) {
            System.out.println("Subcomponentes: " + this.subParts.size());
            for(SubpartContainer subpart: getSubParts()) {
                System.out.print("* Quantidade: " + subpart.getQuantity());
                subpart.getPart().printSubpartInfo();
            }
        }
    }
    public void printSubpartInfo() {
        String partType = this.subParts.size() == 0 ? "primitiva" : "agregada";
        System.out.print(", nome: " + this.getName());
        System.out.print(", servidor: " + this.repository);
        System.out.println(", tipo de peça: " + partType);
    }

    // Construtores
    public Part(String name, String description, String repository, String serverprefix) {
        Long tempId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.id = Long.valueOf(serverprefix + tempId.toString().substring(0, 5));
        this.name = name;
        this.description = description;
        this.repository = repository;
    }
    public Part(String name, String description, String repository, String serverprefix, Set<SubpartContainer> subParts) {
        Long tempId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.id = Long.valueOf(serverprefix + tempId.toString().substring(0, 5));
        this.name = name;
        this.description = description;
        this.repository = repository;
        this.subParts = subParts;
    }
}

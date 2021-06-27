import java.rmi.RemoteException;
import java.util.ArrayList;

public class PartRepository implements IPartRepository {

    String currentServerName;
    ArrayList<Part> parts = new ArrayList<>();

    public PartRepository(String name, String serverPrefix) {
        this.currentServerName = name;
        populateRepository(serverPrefix);
    }

    public PartRepository() { }

    public String getCurrentServerName() {
        return currentServerName;
    }

    public Integer getQuantityOfPartsInCurrentRepository() throws RemoteException {
        return parts.size();
    }
    public ArrayList<Part> getCurrentRepositoryParts() throws RemoteException {
        return this.parts;
    }
    public Part findPartById(Long id) throws RemoteException {
        return parts.stream().filter(part -> part.getId().equals(id)).findFirst().get();
    }
    public void addNewPartToRepository(Part part) throws RemoteException {
        part.setPartIsOnAnotherRepository();
        this.parts.add(part);
    }

    public void rename(int id, String name) throws RemoteException {
        this.parts.get(id).setName(name);
    }

    private void populateRepository(String serverPrefix) {
        parts.add(new Part("Velas de ignição", "Conduzem a corrente elétrica do transformador até a câmara de combustão. Dessa maneira, inflama a mistura de ar e combustível, gerando uma alta tensão", currentServerName, serverPrefix));
        parts.add(new Part("Filtro de óleo", "O filtro de óleo tem a função de eliminar resíduos e sujeiras no sistema de lubrificação que possam atingir o motor.", currentServerName, serverPrefix));
        parts.add(new Part("Pistão", "A função do pistão é receber a explosão da mistura de ar e combustível, contribuindo com um deslocamento de gases, o que faz a peça passar a força expandida para frente, alimentando a potência do motor por meio da energia gerada pela combustão", currentServerName, serverPrefix));
        parts.add(new Part("Caixa de câmbio", "A caixa de câmbio é um multiplicador de velocidade e pode ser manual ou automática. Trata-se de toda a engrenagem que completa o sistema de marcha do seu carro, funcionando segundo as escalas de velocidade do motor ou multiplicação do torque.", currentServerName, serverPrefix));
        parts.add(new Part("Suspensão", "Responsável pelo sistema de estabilidade do veículo, a suspensão absorve todos os impactos do solo, como desníveis, buracos ou objetos que estejam na pista. Situada nas rodas, é uma peça do carro que expressa possíveis problemas por meio de ruídos.", currentServerName, serverPrefix));
        parts.add(new Part("Bomba de combustível", "A bomba de combustível pode ser definida como um equipamento de importância fundamental para o bom funcionamento do veículo porque leva o combustível até o motor.", currentServerName, serverPrefix));
        parts.add(new Part("Farol", "Quando você sair de noite ou mesmo de dia nas rodovias brasileiras, uma peça automotiva essencial é o farol. Em via de regra, utilize sempre o farol baixo para não dificultar a visão dos outros motoristas.", currentServerName, serverPrefix));
        parts.add(new Part("Lâmpada", "responsável pela iluminação", currentServerName, serverPrefix));
        parts.add(new Part("Lente", "responsável pela direção da luz", currentServerName, serverPrefix));
        parts.add(new Part("Direção", "Outra peça fundamental é a direção do carro. Ela está ligada à caixa de direção e permite que o motorista faça diferentes movimentos com as rodas. De forma geral, a direção funciona através do sistema mecânico e servo-assistida.", currentServerName, serverPrefix));
        parts.add(new Part("Radiador", "Ele tem como função ajudar na eficiência do motor do veículo. Contribui com as trocas de calor entre ar/água ou ar/óleo ao mesmo tempo em que mantém o motor em uma temperatura considerada ideal.", currentServerName, serverPrefix));
        parts.add(new Part("Freios", "Uma peça fundamental para a segurança de todos no trânsito é o sistema de freios do carro.", currentServerName, serverPrefix));
        parts.add(new Part("Pastilha", "responsável pelo atrito que freia o carro", currentServerName, serverPrefix));
        parts.add(new Part("Disco", "objeto a ser parado pela pastilhas de freio", currentServerName, serverPrefix));
        parts.add(new Part("Alternador", "O alternador pode ser considerado um gerador de corrente alternada que vira corrente contínua através de componentes eletrônicos.", currentServerName, serverPrefix));
        parts.add(new Part("Cinto de segurança", "Cinto de segurança é um dispositivo criado para proteger motoristas e passageiros de possíveis impactos violentos no interior do carro", currentServerName, serverPrefix));
        parts.add(new Part("Airbag frontal", "Desde 2014, os carros fabricados no Brasil são obrigados a integrar o dispositivo. Sua característica principal é inflar conforme o tipo de colisão.", currentServerName, serverPrefix));
        parts.add(new Part("Multimídia Digital", "Um aparelho que reúne TV digital, GPS, DVD player e MP3 player e tela sensível ao toque", currentServerName, serverPrefix));
        parts.add(new Part("Roda", "Ponto de contato entre o veículo e o solo, ele é formado por diversos componentes, que vão diferir de acordo com a superfície.", currentServerName, serverPrefix));
        parts.add(new Part("Pneu", "parte externa da roda que entra em contato com o solo", currentServerName, serverPrefix));
        parts.add(new Part("Calota", "peça côncavo-convexa que se prende à parte central externa das rodas dos automóveis.", currentServerName, serverPrefix));
        parts.add(new Part("Câmara", " responsável por inflar os pneus.", currentServerName, serverPrefix));
    }
}

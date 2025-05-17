package agricultor.trasporteservice.dto;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class TransporteCreateDTO {

    private String placa;
    private String marca;
    private String color;
    private String linea;
    private String modelo;
    private Long  idAgricultor;

}

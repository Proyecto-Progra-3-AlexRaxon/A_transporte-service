package agricultor.trasporteservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransporteaBDTO {
    private String placa;
    private Long idAgricultor;
}

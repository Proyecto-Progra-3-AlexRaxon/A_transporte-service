package agricultor.trasporteservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "transporte")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transporte")
    private Integer id;

    @Column(name = "placa", nullable = false, unique = true, length = 20)
    private String placa;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "linea", length = 20)
    private String linea;

    @Column(name = "modelo", length = 30)
    private String modelo;

    @Column(name = "estado", columnDefinition = "boolean default true")
    private Boolean estado = true;

    @Column(name = "disponible", columnDefinition = "boolean default true")
    private Boolean disponible = true;

    @Column(name = "pesaje_asociado")
    private Integer pesajeAsociado;
}

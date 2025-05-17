package agricultor.trasporteservice.service;

import agricultor.trasporteservice.dto.TransporteCreateDTO;
import agricultor.trasporteservice.dto.TransporteaBDTO;
import agricultor.trasporteservice.model.Transporte;
import agricultor.trasporteservice.repository.TransporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransporteService {

    private final TransporteRepository transporteRepository;
    private final RestTemplate restTemplate;

    // URL del microservicio B
    private final String microservicioBUrl = "http://localhost:9091/b_transportes";

    @Transactional
    public Transporte crear(TransporteCreateDTO dto) {
        // Verificar placa duplicada
        if (transporteRepository.existsByPlaca(dto.getPlaca())) {
            throw new RuntimeException("La placa ya está registrada");
        }

        // Enviar a microservicio B
        TransporteaBDTO payload = TransporteaBDTO.builder()
                .placa(dto.getPlaca())
                .idAgricultor(dto.getIdAgricultor())
                .build();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    microservicioBUrl,
                    payload,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Microservicio B rechazó la solicitud: " + response.getStatusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error comunicándose con Microservicio B: " + e.getMessage());
        }

        // Crear objeto en base de datos local (Microservicio A)
        Transporte nuevo = Transporte.builder()
                .placa(dto.getPlaca())
                .marca(dto.getMarca())
                .color(dto.getColor())
                .linea(dto.getLinea())
                .modelo(dto.getModelo())
                .estado(true)
                .disponible(true)
                .pesajeAsociado(null)
                .build();

        return transporteRepository.save(nuevo);
    }

    public List<Transporte> obtenerTodos() {
        return transporteRepository.findAll();
    }
}

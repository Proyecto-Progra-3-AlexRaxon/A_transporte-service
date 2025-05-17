package agricultor.trasporteservice.rest;

import agricultor.trasporteservice.dto.TransporteCreateDTO;
import agricultor.trasporteservice.model.Transporte;
import agricultor.trasporteservice.service.TransporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/a_transportes")
@RequiredArgsConstructor
public class TransporteController {

    private final TransporteService transporteService;

    // Obtener todos los transportes
    @GetMapping
    public ResponseEntity<List<Transporte>> obtenerTodos() {
        return ResponseEntity.ok(transporteService.obtenerTodos());
    }

    // Crear nuevo transporte (y enviar a Microservicio B)
    @PostMapping
    public ResponseEntity<Transporte> crear(@RequestBody TransporteCreateDTO transporteDTO) {
        Transporte nuevo = transporteService.crear(transporteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}

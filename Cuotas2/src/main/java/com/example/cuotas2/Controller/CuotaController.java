package com.example.cuotas2.Controller;

import com.example.cuotas2.Entity.CuotaEntity;
import com.example.cuotas2.Models.Alumno;
import com.example.cuotas2.Models.Nota;
import com.example.cuotas2.Service.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/cuota")
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class CuotaController {

    @Autowired
    private CuotaService cuotaService;

    @PostMapping
    public ResponseEntity<Map<String,Object>> agregarCuota(@RequestBody CuotaEntity cuota){
        Map<String,Object> response = new HashMap<>();
        if (cuota != null){
            response.put("cuota",cuotaService.agregarCuota(cuota));
            response.put("status",true);
            return ResponseEntity.ok(response);
        }
        response.put("status",false);
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> obtenerCuotas(){
        Map<String,Object> response = new HashMap<>();
        response.put("status",true);
        response.put("cuotas",cuotaService.obtenerCuotas());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> obtenerCuota(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        response.put("status",true);
        response.put("cuotas",cuotaService.obtenerCuota(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> actualizarCuota(@PathVariable Long id, @RequestBody CuotaEntity cuota){
        Map<String,Object> response = new HashMap<>();
        if (cuota != null){
            response.put("cuota",cuotaService.actualizarCuota(id,cuota));
            response.put("status",true);
            return ResponseEntity.ok(response);
        }
        response.put("status",false);
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/crearCuotas")
    public void crearCuotas(@RequestBody Alumno alumno){
        cuotaService.crearCuotas(alumno);
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<List<CuotaEntity>> cuotasByID(@PathVariable Long id){
        List<CuotaEntity> cuotas = cuotaService.obtenerCuotasAlumno(id);
        System.out.println("Se entro con el id: " + id);
        return ResponseEntity.ok(cuotas);
    }

    @PutMapping("/pagarCuota/{id}")
    public ResponseEntity<Map<String,Object>> pagarCuota(@PathVariable Long id){
        cuotaService.pagarCuota(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/actualizar")
    public void actualizarNotas(@RequestBody List<Nota> notas){
        cuotaService.actualizarNotas(notas);
    }
}

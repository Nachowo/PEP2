package com.example.cuotas.Controller;

import com.example.cuotas.Entity.CuotaEntity;
import com.example.cuotas.Models.Alumno;
import com.example.cuotas.Service.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/cuota")
@CrossOrigin(origins = "*")
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
        response.put("cuota",cuotaService.obtenerCuota(id));
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
        System.out.println("se ingreso a crear cuotas con el siguiente alumno: " + alumno.toString());
        cuotaService.crearCuotas(alumno);
    }
}

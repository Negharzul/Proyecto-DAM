package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.EmpresaInputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpresaOutputDTO;
import com.enlaceFP.enlaceFP.Models.Empresa;
import com.enlaceFP.enlaceFP.Services.EmpleoService;
import com.enlaceFP.enlaceFP.Services.EmpresaService;
import com.enlaceFP.enlaceFP.Services.MailService;
import com.enlaceFP.enlaceFP.mappers.EmpresaInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.EmpresaOutputDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/Empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpleoService empleoService;
    private final MailService mailService;
    private final EmpresaOutputDTOMapper empresaOutputDTOMapper;
    private final EmpresaInputDTOMapper empresaInputDTOMapper;

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaOutputDTO> getEmpresa(@PathVariable Long idEmpresa) {

        try {
            Empresa empresa = empresaService.obtenerEmpresaPorId(idEmpresa);
            EmpresaOutputDTO empresaOutputDTO=empresaOutputDTOMapper.apply(empresa);
            return ResponseEntity.ok(empresaOutputDTO);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<EmpresaOutputDTO>> getAllEmpresas(){
        List<Empresa> empresas=empresaService.obtenerEmpresas();
        List<EmpresaOutputDTO> empresasOutputDTO= empresas.stream().map(empresaOutputDTOMapper).toList();

        return ResponseEntity.ok(empresasOutputDTO);
    }


    @PostMapping("/nuevaEmpresa")
    public ResponseEntity<Map<String,Long>> crearEmpresa(@RequestBody EmpresaInputDTO empresaInputDTO){
        if(empresaInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Empresa empresa =empresaInputDTOMapper.apply(empresaInputDTO);
        Empresa empresaPersistida=empresaService.crearEmpresa(empresa);
        Map<String,Long> response= new HashMap<>();
        response.put("id",empresaPersistida.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @PatchMapping("/Modificar/{idEmpresa}")
    public ResponseEntity<EmpresaOutputDTO> modificarEmpresa(@RequestBody EmpresaInputDTO empresaInputDTO,@PathVariable Long idEmpresa){
        if(empresaInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Empresa empresa=empresaInputDTOMapper.apply(empresaInputDTO);
        Empresa empresaPersistida=empresaService.modificarEmpresa(empresa,idEmpresa);
        EmpresaOutputDTO empresaOutputDTO=empresaOutputDTOMapper.apply(empresaPersistida);

        return ResponseEntity.ok(empresaOutputDTO);
    }

    @DeleteMapping("Borrar/{idEmpresa}")
    public ResponseEntity<EmpresaOutputDTO> borrarEmpresa(@PathVariable Long idEmpresa){
        try{
            empresaService.eliminarEmpresaPorId(idEmpresa);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

}

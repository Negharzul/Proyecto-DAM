package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.Services.ExcelService;
import com.enlaceFP.enlaceFP.Services.MailService;
import com.enlaceFP.enlaceFP.Services.TitulacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('Profesor') || hasRole('Admin')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final ExcelService excelService;
    private final AlumnoService alumnoService;
    private final MailService mailService;
    private final TitulacionService titulacionService;


    @PostMapping("/excelAlumno")
    public ResponseEntity<?> crearAlumnosconExcel(@RequestParam("excel")MultipartFile excel) throws IOException {

        Pattern formato=Pattern.compile(".*\\.xlsx");
        Matcher mat=formato.matcher(excel.getOriginalFilename());

        if(excel.isEmpty() || !mat.matches()) return ResponseEntity.badRequest().build();

        List<Alumno> alumnos = excelService.leerAlumnosExcel(excel.getInputStream(),titulaciones());
        alumnoService.crearAlumnosEnBloque(alumnos);
        mailService.correoRegistroMultiple(alumnos);

        return ResponseEntity.ok().build();
    }

    public Map<String, Long> titulaciones(){
        return titulacionService.obtenerTitulaciones()
                .stream()
                .collect(Collectors.toMap(
                        Titulacion::getTitulo,
                        Titulacion::getId));
    }

}

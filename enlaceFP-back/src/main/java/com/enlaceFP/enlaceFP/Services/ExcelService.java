package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.AlumnoTitulacion;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import com.monitorjbl.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ExcelService {

    private final PasswordEncoder passwordEncoder;

    public List<Alumno> leerAlumnosExcel(InputStream is, Map<String,Long> titulaciones){
        Workbook workbook = StreamingReader.builder()
                .open(is);

        List<Alumno> alumnos = StreamSupport.stream(workbook.spliterator(), false)
                .flatMap(hoja-> StreamSupport.stream(hoja.spliterator(), false)
                        .skip(1)
                        .map(fila-> {

                            Alumno alumno=Alumno.builder()
                                    .nombre(fila.getCell(0).getStringCellValue())
                                    .apellidos(fila.getCell(1).getStringCellValue())
                                    .correoElectronico(fila.getCell(2).getStringCellValue())
                                    .dni(fila.getCell(3).getStringCellValue())
                                    .password(passwordEncoder.encode(fila.getCell(3).getStringCellValue()))
                                    .build();

                            List<AlumnoTitulacion> estudios= new ArrayList<>();
                            for(String titulo:fila.getCell(4).getStringCellValue().split(",")){

                                estudios.add(AlumnoTitulacion
                                        .builder()
                                        .titulacion(Titulacion
                                                .builder()
                                                .id(titulaciones.get(titulo.trim().toLowerCase()))
                                                .build())
                                        .alumno(alumno)
                                        .build());
                            }
                            alumno.setEstudios(estudios);
                            return alumno;}
                        ))
                .toList();

        return alumnos;
    }

}

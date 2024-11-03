package com.ctfera.proposta_app.controller;

import com.ctfera.proposta_app.dto.PropostaRequestDTO;
import com.ctfera.proposta_app.dto.PropostaResponseDTO;
import com.ctfera.proposta_app.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@AllArgsConstructor //Anotação Lombok para criar construtor em tempo de execução, substitue o @Autowired para definir o uso da instância gerenciada pelo Spring
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    //@Autowired //Anotação para definir o uso da instância gerenciada pelo Spring
    //Será instanciado pelo construtor gerado em tempo de execução pela anotação AllArgsConstructor do lombok
    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO){

        PropostaResponseDTO responseDTO = propostaService.criar(requestDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri()).body(responseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<PropostaResponseDTO>> obterProposta(){
       return ResponseEntity.ok().body(propostaService.obterProposta());
    }
}

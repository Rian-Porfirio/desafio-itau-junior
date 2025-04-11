package br.com.rianporfirio.desafio_itau.controller;

import br.com.rianporfirio.desafio_itau.dto.TransacaoDto;
import br.com.rianporfirio.desafio_itau.dto.TransacaoEstatisticasDto;
import br.com.rianporfirio.desafio_itau.service.TransacaoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

  private final TransacaoService transacaoService;

  public TransacaoController(TransacaoService transacaoService) {
    this.transacaoService = transacaoService;
  }

  @PostMapping
  public ResponseEntity<TransacaoDto> create(@Valid @RequestBody TransacaoDto transacaoDto) {
    try {
      transacaoService.create(transacaoDto);
      return ResponseEntity.status(201).build();
    } catch (ValidationException ex) {
      throw new ValidationException();
    }
  }

  @DeleteMapping
  public ResponseEntity<TransacaoDto> delete() {
    transacaoService.delete();
    return ResponseEntity.ok().build();
  }

  @GetMapping("/estatistica")
  public ResponseEntity<TransacaoEstatisticasDto> get() {
    return ResponseEntity.ok(transacaoService.getEstatisticas());
  }
}

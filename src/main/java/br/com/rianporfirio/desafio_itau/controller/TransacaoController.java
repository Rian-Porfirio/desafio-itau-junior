package br.com.rianporfirio.desafio_itau.controller;

import br.com.rianporfirio.desafio_itau.dto.TransacaoDto;
import br.com.rianporfirio.desafio_itau.dto.TransacaoEstatisticasDto;
import br.com.rianporfirio.desafio_itau.service.TransacaoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
      log.info("Requisição recebida: registro de nova transação.");
      transacaoService.create(transacaoDto);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (ValidationException ex) {
      log.warn("Requisição negada. Verificar motivo e corrigir.");
      throw new ValidationException();
    }
  }

  @DeleteMapping
  public ResponseEntity<TransacaoDto> delete() {
    log.info("Requisição recebida: remover todas as transações.");
    transacaoService.delete();
    return ResponseEntity.ok().build();
  }

  @GetMapping("/estatistica")
  public ResponseEntity<TransacaoEstatisticasDto> get() {
    log.info("Requisição recebida: gerar estatísticas sobre as transações registradas.");
    return ResponseEntity.ok(transacaoService.getEstatisticas());
  }
}

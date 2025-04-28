package br.com.rianporfirio.desafio_itau.controller;

import br.com.rianporfirio.desafio_itau.dto.TransacaoEstatisticasDto;
import br.com.rianporfirio.desafio_itau.service.EstatisticasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    public EstatisticasController(EstatisticasService estatisticasService) {
        this.estatisticasService = estatisticasService;
    }

    @GetMapping
    public ResponseEntity<TransacaoEstatisticasDto> get(
            @RequestParam(
                    name = "intervaloEmSegundos",
                    defaultValue = "60")
            int intervaloDeTempo) {
        log.info("Requisição recebida: gerar estatísticas sobre as transações registradas.");
        return ResponseEntity.ok(estatisticasService.getEstatisticas(intervaloDeTempo));
    }
}

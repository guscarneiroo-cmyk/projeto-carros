package com.example.projetoteste.service;

import com.example.projetoteste.domain.merged.BrasilFipeMergeResponse;
import com.example.projetoteste.domain.merged.MergedMenorValorDTO;
import org.springframework.context.annotation.Bean;

public interface MergedService {

    @Bean
    BrasilFipeMergeResponse mergeByCodigoFipe(String codigoFipe);

}

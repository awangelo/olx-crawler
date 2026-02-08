package com.awangelo.service

import com.awangelo.model.SearchConfig
import groovyx.net.http.HttpBuilder

import static groovyx.net.http.HttpBuilder.configure

final class CrawlerService {
    private static final String BASE_URL = 'https://www.olx.com.br'
    private static final String USER_AGENT = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36'
    private HttpBuilder httpBuilder
    private int paginasParaLer

    CrawlerService(SearchConfig searchConfig) {
        if (!searchConfig.isValid()) throw new IllegalArgumentException("Configurações de busca inválidas: ${searchConfig}")

        this.paginasParaLer = searchConfig.paginasParaLer

        StringBuilder base = new StringBuilder(BASE_URL)
        if (searchConfig.estado) base.append('/estado-').append(searchConfig.estado)
        if (searchConfig.regiao) base.append('/').append(searchConfig.regiao)
        if (searchConfig.termoBusca) base.append('?q=').append(searchConfig.termoBusca)
        if (searchConfig.menorPreco) base.append('&sp=2')
        String baseUrl = base.toString()

        this.httpBuilder = configure {
            request.uri = baseUrl
            request.headers['User-Agent'] = USER_AGENT
        }
    }
}
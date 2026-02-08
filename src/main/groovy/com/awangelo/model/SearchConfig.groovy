package com.awangelo.model

final class SearchConfig {
    String estado      // "/estado-ce"
    String regiao      // "/grande-goiania-e-anapolis"
    String termoBusca  // "?q=iphone+11"
    boolean menorPreco // "&sp=2"
    int paginasParaLer

    boolean isValid() {
        return estado && termoBusca && menorPreco && paginasParaLer > 0
    }
}

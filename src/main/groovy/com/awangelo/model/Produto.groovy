package com.awangelo.model

import groovy.transform.Canonical

@Canonical
final class Produto {
    String titulo
    BigDecimal valor
    static BigDecimal media
    String endereco
    String url
}
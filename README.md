# OLX Crawler

## Build e Execução

```bash
./gradlew build
./gradlew run --console=plain
```

## Estrutura do Projeto

```
src/main/groovy/com/awangelo/
├── Main.groovy
├── model
│   ├── Produto.groovy          # Representa os dados de um anúncio
│   └── SearchConfig.groovy     # Configurações de busca (estado, termo, etc.)
├── parser
│   └── OlxHtmlParser.groovy    # Faz o parsing do HTML da OLX
└── service
    ├── CleanerService.groovy   # Limpeza e cálculos de média
    ├── CrawlerService.groovy   # Lógica de navegação e extração de dados
    └── CsvService.groovy       # Geração de relatório CSV
```

Feito por Ângelo.
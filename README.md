# Indica Aí — Let Parque

![img.png](public/logo.png)

![Status](https://img.shields.io/badge/status-em_desenvolvimento-yellow)
![Kotlin](https://img.shields.io/badge/backend-Kotlin-7F52FF)
[![Issues](https://img.shields.io/github/issues/JonathanCabral/indica-ai-let-parque)](https://github.com/JonathanCabral/indica-ai-let-parque/issues)

Plataforma para moradores do Le Parque encontrarem prestadores de serviço e compartilharem indicações, contraindicações e experiências.

O projeto nasceu de uma necessidade recorrente no condomínio: reunir os contatos e relatos compartilhados entre vizinhos em um lugar organizado, pesquisável e fácil de consultar.

## Estado do projeto

O projeto está em desenvolvimento, com foco inicial no backend e na construção do MVP.

As funcionalidades descritas neste documento representam o escopo definido para a primeira entrega. A implementação e as decisões técnicas são acompanhadas nas issues e no Project.

- [Planejamento do projeto](https://github.com/users/JonathanCabral/projects/3)
- [Backlog e issues](https://github.com/JonathanCabral/indica-ai-let-parque/issues)

## Escopo do MVP

### Acesso à comunidade

- Cadastro individual de usuários.
- Solicitação de vínculo com apartamento.
- Aprovação do acesso pelo administrador.
- Permissões conforme o vínculo aprovado.
- Gestão de apartamentos e consulta de unidades sem ocupantes registrados.

### Catálogo de prestadores

- Cadastro de prestadores e informações de contato.
- Organização por categorias e tags.
- Busca por nome, categoria e tags.
- Consulta do perfil e das experiências compartilhadas.

### Indicações e experiências

- Publicação de indicações e contraindicações com nota e relato.
- Registro de novas experiências com o mesmo prestador.
- Consulta das avaliações e da reputação do profissional.
- Moderação de conteúdo.
- Preservação da autoria e do histórico quando houver mudança de ocupantes.

Fotos e anexos fazem parte da evolução planejada, após a implementação do fluxo principal.

## Regras de negócio

O acesso ao conteúdo será restrito aos usuários aprovados da comunidade.

Proprietários e inquilinos aprovados poderão contribuir. Hóspedes terão acesso somente de leitura.

Propriedade e residência serão tratadas separadamente. Um proprietário que não reside na unidade não será considerado seu ocupante.

A marcação de locação temporária pertence ao apartamento. Alterar essa marcação não deve conceder novas permissões aos usuários vinculados.

Cada nova experiência poderá gerar um novo registro de avaliação. A contratação do prestador não será obrigatória para compartilhar uma experiência.

A média de um prestador considerará todas as notas visíveis. A mudança de vínculo de um usuário com um apartamento não deverá transferir nem apagar a autoria das avaliações anteriores.

## Tecnologias

| Componente | Tecnologia | Situação |
| --- | --- | --- |
| Backend | Kotlin e Spring Boot | Base atual |
| Build | Gradle | Base atual |
| Persistência | PostgreSQL | Direção definida para a evolução |
| Validação de entrada | Jakarta Bean Validation | Prevista nas entregas da API |
| Integração contínua | GitHub Actions | Planejada no backlog |
| Ambiente em containers | Docker e Docker Compose | Planejado no backlog |
| Interface web | React | Evolução planejada |

As versões e dependências efetivamente utilizadas devem ser consultadas nos arquivos de build do projeto.

## Arquitetura

A evolução do backend seguirá uma aplicação única, organizada em módulos por funcionalidade.

Cada módulo deverá manter limites claros entre:

- **API:** contratos de entrada e saída, validação dos dados recebidos e respostas HTTP.
- **Aplicação e domínio:** casos de uso, regras de negócio e autorização.
- **Persistência e integrações:** acesso ao banco e comunicação com serviços externos.

O objetivo é permitir entregas pequenas, com responsabilidades compreensíveis e regras de negócio que possam ser testadas de forma independente.

As alterações de banco deverão ser versionadas por migrations. Restrições de integridade, transações e controle de concorrência deverão acompanhar as regras que dependem delas.

### Documentação de arquitetura

A documentação será versionada junto ao código e atualizada nas entregas que introduzirem mudanças relevantes.

As visões deverão explicar os elementos, suas responsabilidades, relações e decisões associadas:

| Visão | O que deve esclarecer |
| --- | --- |
| Contexto | Usuários, objetivo do sistema e integrações externas |
| Módulos | Responsabilidades e dependências entre funcionalidades |
| Dados | Entidades, relacionamentos e restrições de integridade |
| Execução | Colaboração entre componentes nos fluxos relevantes |
| Estados | Transições de acesso, vínculos e moderação |
| Implantação | Ambiente de execução e dependências operacionais |

Decisões significativas serão registradas em ADRs, incluindo contexto, alternativas consideradas e consequências.

O guia de diagramas, formatos e locais de armazenamento está na [IALP-001](https://github.com/JonathanCabral/indica-ai-let-parque/issues/1).

A documentação seguirá a abordagem de *Documenting Software Architectures: Views and Beyond*, utilizando o [Farmacy Food Kata](https://github.com/JonathanCabral/farmacy-food-kata) como referência de organização.

## Qualidade e testes

Os testes deverão verificar comportamentos observáveis, regras de negócio e riscos relevantes de cada entrega.

A estratégia seguirá as práticas de Maurício Aniche, incluindo:

- Partições de equivalência para identificar grupos de entradas com comportamento semelhante.
- Valores de fronteira para limites de tamanho, notas e paginação.
- Tabelas de decisão para combinações de permissões e condições.
- Transições de estado para aprovação, vínculos e moderação.
- Invariantes e propriedades para integridade, autoria e cálculo de médias.
- Cenários de concorrência para operações que possam disputar os mesmos dados.

Os tipos de teste serão escolhidos conforme a responsabilidade:

| Tipo | Aplicação |
| --- | --- |
| Unitário | Regras de negócio e cálculos isolados |
| Integração | Persistência, migrations, consultas e transações com PostgreSQL |
| API | Contratos HTTP, Bean Validation, autenticação e autorização |
| Sistema | Fluxos completos relevantes para o uso da plataforma |
| Carga | Comportamento sob volume e concorrência representativos |

A orientação central está na [IALP-037](https://github.com/JonathanCabral/indica-ai-let-parque/issues/37). Os cenários específicos estão descritos nos cards de cada funcionalidade.

### Validação de entrada

Bean Validation será aplicado aos contratos de entrada da API, incluindo campos obrigatórios, tamanhos, formatos e limites.

Existência de registros, autorização, unicidade e demais regras de negócio serão verificadas nas camadas responsáveis por esses comportamentos.

As respostas de erro deverão identificar os campos inválidos e seus motivos, seguindo um contrato consistente.

Consulte a [IALP-035](https://github.com/JonathanCabral/indica-ai-let-parque/issues/35).

## Performance

Performance faz parte dos critérios de implementação e revisão.

- Aplicar paginação no banco em consultas com resultados potencialmente numerosos.
- Retornar apenas os dados necessários para cada operação.
- Verificar a quantidade de consultas SQL e evitar N+1.
- Definir índices a partir dos filtros e planos de execução.
- Manter transações curtas e limitar o alcance de locks.
- Evitar carregamento integral de coleções para filtrar ou paginar em memória.
- Medir latência, erros e consumo de recursos com dados representativos.

As metas iniciais registradas nos cards são hipóteses do projeto. Elas deverão ser validadas por medições em um ambiente conhecido.

## Desenvolvimento local

### Pré-requisitos

- Git.
- JDK compatível com a configuração do build.
- Acesso ao banco configurado para o ambiente de desenvolvimento.

Quando o Gradle Wrapper estiver disponível, utilize-o para executar o projeto com a versão de Gradle definida pelo repositório.

### Clonar o repositório

```bash
git clone https://github.com/JonathanCabral/indica-ai-let-parque.git
cd indica-ai-let-parque
```

### Configurar o ambiente

Confira os arquivos de configuração da aplicação e ajuste a conexão com o banco de desenvolvimento.

Credenciais e outros segredos devem ser fornecidos pela configuração local ou pelo ambiente de execução.

**O bootstrap atual possui um script que recria tabelas na inicialização. Utilize uma base descartável de desenvolvimento até que sua substituição por migrations esteja concluída.**

### Executar o backend

No diretório que contém o Gradle Wrapper:

```bash
./gradlew bootRun
```

No Windows:

```powershell
.\gradlew.bat bootRun
```

### Executar os testes

```bash
./gradlew test
```

### Executar o build

```bash
./gradlew build
```

Esses comandos executam a configuração existente no projeto. A ampliação dos testes, a integração contínua e o ambiente em containers fazem parte do backlog.

## Como contribuir

1. Escolha uma issue e confira suas dependências.
2. Alinhe o escopo antes de iniciar mudanças maiores.
3. Crie uma branch para a entrega.
4. Implemente o comportamento e os testes relevantes.
5. Atualize a documentação quando houver mudança de contrato ou arquitetura.
6. Abra um pull request explicando o problema resolvido e a validação realizada.

Quando o pull request concluir uma issue inteira, inclua sua referência na descrição:

```md
Closes #36
```

Sugestões de moradores e relatos sobre dificuldades de uso também são contribuições importantes e podem ser registrados nas issues.

## Autor

Desenvolvido por [Jonathan Cabral](https://github.com/JonathanCabral).

Dúvidas, sugestões e propostas de melhoria podem ser compartilhadas nas [issues do projeto](https://github.com/JonathanCabral/indica-ai-let-parque/issues).
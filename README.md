# 🏘️ Indica Aí – Let Parque

**Indica Aí – Let Parque** é uma plataforma colaborativa desenvolvida por Jonathan (o cara brabo) com apoio de amigos para os moradores do condomínio Let Parque.  
O objetivo é facilitar a recomendação e busca por prestadores de serviço de confiança entre vizinhos.

> Um sistema feito com carinho para a comunidade do Let Parque. 💚

---

## ✨ Funcionalidades

- ✅ Cadastro de profissionais recomendados pelos moradores
- 🔍 Filtros por categoria, nome, apartamento e tipo de serviço
- 👍 Confirmações positivas e negativas para cada indicação
- 💬 Campo de comentários para compartilhar experiências
- 🔐 Interface segura e simples para uso da comunidade
- 📦 Organizado por categorias úteis para a vida em condomínio

---

## 🚀 Tecnologias Utilizadas

- **Frontend**: React
- **Backend**: Kotlin
- **Banco de dados**: MySQL ou PostgreSQL
- **Deploy**: GitHub Actions + Docker + Kubernetes

---

## 🛠️ Como rodar localmente

### Pré-requisitos

- Node.js (para o frontend)
- Java 17+ e Gradle (para o backend em Kotlin)
- Docker e Docker Compose
- MySQL ou PostgreSQL local (ou configurado no Docker)

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/indica-ai-let-parque.git

# Acesse a pasta do projeto
cd indica-ai-let-parque
```

#### Rodando o Frontend

```bash
cd frontend
npm install
npm run dev
```

#### Rodando o Backend

```bash
cd backend
./gradlew bootRun
```

> Certifique-se de configurar as variáveis de ambiente para conexão com o banco de dados.

#### Rodando com Docker

```bash
docker-compose up --build
```

> *Em breve: setup com Kubernetes e deploy automatizado com GitHub Actions.*

---

## 🧠 Estrutura Inicial do Projeto

```
indica-ai-let-parque/
├── frontend/       # Interface web (React)
├── backend/        # API e lógica de negócio (Kotlin)
├── database/       # Modelos, scripts e migrations
├── docs/           # Documentação e design
└── README.md
```

---

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

## 💬 Contribuindo

Este projeto é aberto a sugestões! Se você é dev ou morador curioso e quer ajudar, sinta-se à vontade para abrir issues ou enviar PRs.

---

## 📫 Contato

Para dúvidas, ideias ou sugestões:
- Abra uma [issue aqui no GitHub](https://github.com/seu-usuario/indica-ai-let-parque/issues)

---

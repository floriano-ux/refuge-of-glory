# ⚔️ Refuge of Glory

RPG de Turnos com estética 16-bits desenvolvido em Java, unindo mecânicas de combate clássicas e sistemas de interação inspirados em Visual Novels. Temática baseada nas franquias **Dungeons & Dragons** e **The Witcher**.

> Projeto acadêmico desenvolvido para a disciplina de Projeto e Arquitetura de Sistemas — Universidade de Fortaleza (UNIFOR).

---

## 🛠️ Tecnologias

| Camada | Tecnologia |
|---|---|
| Back-end | Java 21 + Spring Boot 3 |
| Banco de Dados | MySQL |
| Front-end / Engine | LITIENGINE (Java 2D) |
| Arte | Aseprite (sprites 16-bits) |
| Mapas | Tiled (.tmx) |
| Autenticação | JWT (jjwt 0.12.3) |
| AOP | AspectJ + Spring AOP |

---

## 🏗️ Arquitetura

O sistema utiliza arquitetura de **Microserviços**, dividido em 3 serviços independentes que se comunicam via REST:

```
refuge-of-glory/
├── auth-service/        → porta 8081
├── character-service/   → porta 8082
├── combat-service/      → porta 8083
└── litiengine-client/   → frontend desktop
```

---

## 🎮 Microserviços

### 🔐 Auth Service (`porta 8081`)
Responsável pelo cadastro, login e validação de tokens JWT.

### 🧙 Character & Inventory Service (`porta 8082`)
Gerencia personagens, atributos, inventário, itens e inimigos.

**Bestiário:**
| Dificuldade | Monstro | Origem |
|---|---|---|
| Fácil | Carniçal | The Witcher |
| Fácil | Garra Rastejante | D&D |
| Intermediário | Pantera Deslocadora | D&D |
| Intermediário | Golem | The Witcher |
| Boss | Owlbear | D&D |
| Boss | Demônio | The Witcher |

### ⚔️ Combat Service (`porta 8083`)
Gerencia sessões de batalha, turnos, cálculo de dano e condições de vitória/derrota.

---

## 🧩 Design Patterns

### Criacionais
| Pattern | Classe | Descrição |
|---|---|---|
| Factory Method | `CharacterFactory` | Cria personagens e inimigos sem acoplar às classes concretas |
| Builder | `CharacterBuilder` | Constrói heróis com múltiplos atributos de forma fluida |
| Prototype | `EnemyPrototype` | Clona inimigos a partir de um modelo base |

### Estruturais
| Pattern | Classe | Descrição |
|---|---|---|
| Facade | `BattleSession` | Interface simplificada que esconde a complexidade do combate |
| Adapter | `CharacterClient` | Adapta a comunicação entre o Combat e o Character Service |
| Decorator | `ItemDecorator` | Adiciona enchantments aos itens em tempo de execução |

### Comportamentais
| Pattern | Classe | Descrição |
|---|---|---|
| Strategy | `DamageStrategy` | Define cada tipo de dano como uma estratégia intercambiável |
| State | `BattleState` | Gerencia os estados da batalha (turno, vitória, derrota) |
| Observer | `BattleObserver` | Notifica o front-end e o log sobre eventos da batalha |

---

## 🔄 AOP (Programação Orientada a Aspectos)

O `LoggingAspect` intercepta métodos críticos dos 3 serviços via `@Before` e `@AfterReturning`, registrando logs de auditoria sem acoplar essa responsabilidade à lógica de negócio.

---

## ▶️ Como rodar

### Pré-requisitos
- Java 21
- MySQL rodando localmente
- Maven 3.9+

### 1. Criar os bancos de dados
```sql
CREATE DATABASE rpg_auth;
CREATE DATABASE rpg_character;
CREATE DATABASE rpg_combat;
```

### 2. Configurar as senhas
Em cada `application.properties`, altere:
```properties
spring.datasource.password=sua_senha
```

### 3. Subir os serviços
```bash
# Auth Service
cd auth-service
mvn spring-boot:run

# Character Service
cd character-service
mvn spring-boot:run

# Combat Service
cd combat-service
mvn spring-boot:run
```

### 4. Rodar o cliente
```bash
cd litiengine-client
mvn exec:java
```

---

## 👥 Equipe

| Membro | Responsabilidade |
|---|---|
| João Pedro de Almeida Floriano | Arquitetura, diagramas UML e padrões criacionais |
| Victor Vasconcelos Viana | Padrões estruturais e camada de persistência |
| Ana Lívia Valentim Carneiro | História, diálogos e narrativa |
| Vinícius de Almeida | Arte, sprites e assets 16-bits |
| Takeshi | Ludologia e balanceamento de jogo |

---

## 📚 Referências

- WIZARDS OF THE COAST. *Dungeons & Dragons Player's Handbook*
- SAPKOWSKI, Andrzej. *The Witcher Series*
- Universidade de Fortaleza — UNIFOR
- Professor Estevão Simão
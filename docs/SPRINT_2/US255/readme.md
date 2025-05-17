# US 255

## 1. Context

The purpose of this task is to provide a context-free grammar to validate Shodrone's Show Proposal templates. 
This task is included in Sprint 2 and is being implemented for the first time.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 📌 Backlog

Test: 📌 Backlog


## 2. Requirements

**As** a CRM Manager,
<br>
**I want** to define a language to specify proposal templates,
<br>
**So that** the proposal templates are in accord to the CRM team desires.

**Acceptance Criteria:**

- **_US255.1_** The grammar must support fixed and variable components in the document.
- **_US255.2_** It must allow dynamic placeholders (e.g., [Company name], [Date]) that can be replaced with 
customer-specific values.
- **_US255.3_** It must support multiple versions of the same document to accommodate different languages and 
customer tones (e.g., formal/informal, Portuguese/English).
- **_US255.4_** The grammar must validate structured technical data, including GPS coordinates, time formats, dates, 
and currency values.
- **_US255.5_** The grammar must support lists of drones and figures, with flexible repetition structures.
- **_US255.6_** It must explicitly represent formatting requirements such as line breaks and page breaks.

**Input Templates**

The following proposal templates were used as the basis for the grammar definition:

- [Show Proposal model 01 v1.0.txt](files/Show_Proposal_Models/Show%20Proposal%20model%2001%20v1.0.txt)
- [Show Proposal model 02 v1.0.txt](files/Show_Proposal_Models/Show%20Proposal%20model%2002%20v1.0.txt)
- [Show Proposal model 03 v1.0.txt](files/Show_Proposal_Models/Show%20Proposal%20model%2003%20v1.0.txt)


## 3. Analysis

The proposal documents share a consistent high-level structure with optional variations in greeting, language, and body 
content. From the analysis of the sample templates, the document can be broken down into these segments:

* **Greeting section**: Offers three variants (formal PT, formal EN, and personalized EN) and includes customer and 
company identification data.
* **Reference section**: Displays a proposal number and date, with format rules enforced for each.
* **Body**: Includes the proposal objective, safety note, insurance value, and a video link. Each body version differs 
in tone and language but follows a similar flow.
* **Signature section**: Contains standardized closing messages and role titles, with language-dependent formatting.
* **Attachment**: A structured block with event details, drone models used, and figures to be displayed. It contains 
nested information such as coordinates, time formats, and quantities, all subject to syntactic rules.

Additionally, terminal categories such as `DIGIT`, `LETTER`, and `SYMBOL` were defined to validate the contents of 
fields like VAT numbers, proposal codes, and placeholders. Recursive definitions (e.g., for lists or variable-length fields) 
are terminated using `ε` to ensure the grammar produces finite results. Line breaks (`BREAKLINE`) were introduced explicitly 
to match document formatting requirements.


## 4. Design

This section presents the complete grammar developed to support **US255 – Configuration of Proposal Templates**. The 
grammar is written in context-free notation and captures the structure, flow, and variability of the proposal templates 
used by Shodrone.

The grammar was designed to:

* Support multiple versions of the template (e.g., Portuguese and English, formal and personalized).
* Represent dynamic content using placeholders.
* Enforce the correct structure and sequencing of all document sections.
* Model lists, formatting (e.g., line breaks and page breaks), and field validation (e.g., dates, times, GPS, currencies).

The grammar is modular, with each non-terminal symbol representing a logical part of the document, such as the greeting, 
reference, body, technical details, or figures. Repetition and optionality are handled through recursive rules and the 
empty string symbol (`ε`), and terminal categories like `DIGIT`, `LETTER`, `SYMBOL` are used to define the building 
blocks of variable fields.

The grammar is structured as follows:

- **High-level alternatives:** ``S``
- **Greeting section:** ``A1, A2, A3``
- **Header and reference:** ``B, C1, C2``
- **Body content:** ``E1, E2, E3``
- **Signature:** ``F, G``
- **Technical attachment:** ``I–O``

Below is the complete grammar:

```
S →  A1 B C1 D1 E1 F1 G1 H I1 J1 K1 L1 N1 O1 | A2 B C2 D2 E2 F2 G2 H I2 J2 K2 L2 N2 O2 | A3 B C2 D2 E3 F3 G3 H I2 J2 K2 L2 N2 O2

A1 → Exmos. Senhores BREAKLINE
A2 → Dear Sirs, BREAKLINE
A3 → Dear, BREAKLINE CUSTREPNAME BREAKLINE

B → COMPANYNAME BREAKLINE ADDRESS BREAKLINE VAT BREAKLINE BREAKLINE
    
    COMPANYNAME → NAME
    CUSTREPNAME → NAME
    ADDRESS → TEXT
    VAT → DIGIT VAT | ε

C1 → Referência PROPOSALDATE BREAKLINE
C2 → Reference PROPOSALDATE BREAKLINE
    
    PROPOSALDATE → PROPOSALNUMBER / DATE BREAKLINE
    PROPOSALNUMBER → DIGIT PROPOSALNUMBER | DIGIT | ε
    DATE → DAY SEPARATOR MONTH SEPARATOR YEAR
    DAY → 0 DIGITWITHOUT0 | 1 DIGIT | 2 DIGIT | 30 | 31
    MONTH → 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08 | 09 | 10 | 11 | 12
    YEAR → DIGITWITHOUT0 DIGIT DIGIT DIGIT
    SEPARATOR → / | - | .

D1 → Proposta de Show BREAKLINE BREAKLINE
D2 → Show Proposal BREAKLINE BREAKLINE

E1 → A Shodrone tem o prazer de submeter à V/ apreciação uma proposta para execução de um show aéreo com drones, 
conforme descrição abaixo. BREAKLINE 
A Shodrone é uma empresa que dá prioridade à segurança, pelo que usa a mais avançada tecnologia de IA para apoiar o 
desenvolvimento dos seus shows, sendo que todos os shows são prévia e cuidadosamente testados/simulados com a tecnologia 
AI-Test© antes de serem apresentados ao cliente. No link LINK encontra-se um vídeo com a simulação do show proposto. BREAKLINE BREAKLINE
Com a aplicação do AI-Test©, um exclusivo da Shodrone, temos a confiança de oferecer um seguro de responsabilidade 
civil no valor de INSURANCEAMOUNT para o show. Os dados detalhados do show são apresentados em anexo. BREAKLINE BREAKLINE

E2 → Shodrone is pleased to submit for your consideration a proposal for the execution of an aerial show with drones, 
as described below. BREAKLINE
Shodrone is a company that prioritizes safety, which is why it uses the most advanced AI technology to support the 
development of its shows, with all shows being previously and carefully tested/simulated with AI-Test© technology 
before being presented to the client. In the link LINK there is a video with a simulation of the 
proposed show. BREAKLINE BREAKLINE
With the application of AI-Test©, a Shodrone exclusive, we are confident in offering liability insurance in the amount 
of INSURANCEAMOUNT for the show. Detailed show data is presented in the attachment. BREAKLINE BREAKLINE

E3 → COMPANYNAME is a VIP client and Shodrone is pleased to submit for your consideration a proposal for the 
execution of an aerial show with drones, as described below. BREAKLINE
Shodrone is a company that prioritizes safety, which is why it uses the most advanced AI technology to support the 
development of its shows, with all shows being previously and carefully tested/simulated with AI-Test© technology before 
being presented to the client. In the link LINK there is a video with a simulation of the proposed show. BREAKLINE BREAKLINE
With the application of AI-Test©, a Shodrone exclusive, we are confident in offering liability insurance in the amount 
of INSURANCEAMOUNT for the show. Detailed show data is presented in the attachment. BREAKLINE BREAKLINE

    LINK → https://www.TEXT.com
    INSURANCEAMOUNT → VALUE € | VALUE $ | VALUE £
    VALUE → DIGIT VALUE | DIGIT | ε

F1 → Estando certos que seremos alvo da V/ preferência. BREAKLINE BREAKLINE
F2 → Being certain that we will be the target of your preference. BREAKLINE BREAKLINE
F3 → Looking forward to hearing from you soon. BREAKLINE BREAKLINE

G1 → Subscrevemo-nos ao dispor. BREAKLINE BREAKLINE
    Melhores cumprimentos, BREAKLINE BREAKLINE
    CRMMANAGERNAME BREAKLINE
    CRM Manager BREAKLINE
G2 → We subscribe at your disposal. BREAKLINE BREAKLINE
    Best regards, BREAKLINE BREAKLINE
    CRMMANAGERNAME BREAKLINE
    CRM Manager BREAKLINE
G3 → Best regards, BREAKLINE BREAKLINE
    CRMMANAGERNAME BREAKLINE
    CRM Manager BREAKLINE

H → [page break]

I1 → Anexo – Detalhes do Show PROPOSALNUMBER BREAKLINE
I2 → Attachment – Show Details PROPOSALNUMBER BREAKLINE

J1 → Local de realização – GPSCOORDINATES BREAKLINE
    Data – DATE BREAKLINE
    Hora – TIME BREAKLINE
    Duração – DURATION minutos BREAKLINE BREAKLINE
J2 → Location – GPSCOORDINATES BREAKLINE
    Date – DATE BREAKLINE
    Time – TIME BREAKLINE
    Duration – DURATION minutes BREAKLINE BREAKLINE

    GPSCOORDINATES → LAT , LON
    LAT → SIGN INTLAT . DECS
    LON → SIGN INTLON . DECS
    SIGN → - | ε
    INTLAT → DIGIT | 1 DIGIT | 2 DIGIT | 3 DIGIT | 4 DIGIT | 5 DIGIT | 6 DIGIT | 7 DIGIT | 8 DIGIT | 90
    INTLON → DIGIT | DIGIT DIGIT | 11 DIGIT | 12 DIGIT | 13 DIGIT | 14 DIGIT | 15 DIGIT | 16 DIGIT | 17 DIGIT | 180
    DECS → DIGIT DECS | DIGIT

    TIME → HOUR : MINUTE
    HOUR → 0 DIGIT | 1 DIGIT | 20 | 21 | 22 | 23
    MINUTE → 0 DIGIT | 1 DIGIT | 2 DIGIT | 3 DIGIT | 4 DIGIT | 5 DIGIT
    DURATION → DIGIT DURATION | DIGIT | ε

K1 → #Lista de drones utilizados BREAKLINE
K2 → #List of used drones BREAKLINE

L1 → M1 L1 | M1 | ε
L2 → M2 L2 | M2 | ε

    M1 → MODEL – QUANTITY unidades. BREAKLINE
    M2 → MODEL – QUANTITY units. BREAKLINE
    MODEL → TEXT
    QUANTITY → DIGIT QUANTITY | DIGIT | ε

N1 → BREAKLINE #Lista de figuras BREAKLINE
N2 → BREAKLINE #List of figures BREAKLINE

O1 → P1 O1 | P1 | ε
O2 → P2 O2 | P2 | ε

    P1 → POSITION – FIGURE BREAKLINE
    P2 → POSITION – FIGURE BREAKLINE
    POSITION → TEXT
    FIGURE → NAME

TEXT → CHAR TEXT | CHAR | ε
NAME → LETTER NAME | LETTER | ε
CHAR → LETTER | DIGIT | SYMBOL

LETTER → a | b | c | ... | z | A | B | C | ... | Z
DIGIT → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
DIGITWITHOUT0 → 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
SYMBOL → . | , | : | ; | ' ' | / | - | _ | ( | ) | ' | " | @ | # | $ | % | & | ? | ! | ε
BREAKLINE → \n
```

This formal specification serves as the foundation for implementing template parsing, validation, and generation tools, 
ensuring that proposal documents follow a standardized and verifiable format.

lexer grammar ProposalLexer;

// Keywords
BREAKLINE: '\n';
SEPARATOR: '/' | '-' | '.';

// Symbols and literals
SYMBOL: [.,:;'\-/()"@#$%&?!_];
DIGIT: [0-9];
DIGITWITHOUT0: [1-9];
LETTER: [a-zA-Z];

// Common tokens
CHAR: LETTER | DIGIT | SYMBOL;
TEXT: CHAR+;
NAME: LETTER+;
VALUE: DIGIT+;

// Date and time tokens
MINUTE: '0' DIGIT | '1' DIGIT | '2' DIGIT | '3' DIGIT | '4' DIGIT | '5' DIGIT;
HOUR: '0' DIGIT | '1' DIGIT | '2' [0-3];
DAY: '0' DIGITWITHOUT0 | '1' DIGIT | '2' DIGIT | '30' | '31';
MONTH: '0' DIGITWITHOUT0 | '1' [0-2];

M1: TEXT ' – ' DIGIT+ ' unidades.' BREAKLINE;
M2: TEXT ' – ' DIGIT+ ' units.' BREAKLINE;
P1: TEXT ' – ' NAME BREAKLINE;
P2: TEXT ' – ' NAME BREAKLINE;

// Reserved words
EXMOS_SENHORES: 'Exmos. Senhores';
DEAR_SIRS: 'Dear Sirs,';
DEAR: 'Dear,';
REFERENCIA: 'Referência';
REFERENCE: 'Reference';
PROPOSTA_PT: 'Proposta de Show';
PROPOSTA_EN: 'Show Proposal';
LINK: 'https://www.' TEXT '.com';
AI_INSURANCE: ('€' | '$' | '£');
INSURANCEAMOUNT: VALUE AI_INSURANCE;

SUBSCRIBE_PT: 'Subscrevemo-nos ao dispor.';
SUBSCRIBE_EN: 'We subscribe at your disposal.';
BEST_REGARDS: 'Melhores cumprimentos,' | 'Best regards,';
CRM_MANAGER: 'CRM Manager';

ESTANDO_CERTOS_PT: 'Estando certos que seremos alvo da V/ preferência.';
BEING_CERTAIN_EN: 'Being certain that we will be the target of your preference.';
LOOKING_FORWARD_EN: 'Looking forward to hearing from you soon.';

LISTA_DRONES_PT: '#Lista de drones utilizados';
LISTA_DRONES_EN: '#List of used drones';
LISTA_FIGURAS_PT: '#Lista de figuras';
LISTA_FIGURAS_EN: '#List of figures';

WORD_MINUTES: ' minutos';

ANEXO_PT: 'Anexo – Detalhes do Show';
ANEXO_EN: 'Attachment – Show Details';
LOCAL_PT: 'Local de realização –';
DATA_PT: 'Data –';
HORA_PT: 'Hora –';
DURACAO_PT: 'Duração –';

LOCATION_EN: 'Location –';
DATE_EN: 'Date –';
TIME_EN: 'Time –';
DURATION_EN: 'Duration –';

PAGE_BREAK: '[page break]';
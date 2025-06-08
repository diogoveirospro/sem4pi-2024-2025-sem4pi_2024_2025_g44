lexer grammar ProposalLexer;

// Whitespace and line breaks
BREAKLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
SPACE: [ \t]+;


// Symbols
SYMBOL: [.,:;'\-/()"@#$%&?!_];

// Keywords – Portuguese
EXMOS_SENHORES: 'Exmos. Senhores';
REFERENCIA: 'Referência' SPACE PROP VALUE SPACE SEPARATOR SPACE DAY SEPARATOR MONTH SEPARATOR YEAR;
PROPOSTA_PT: 'Proposta de Show';
ESTANDO_CERTOS_PT: 'Estando certos que seremos alvo da V/ preferência.';
SUBSCRIBE_PT: 'Subscrevemo-nos ao dispor.';
BEST_REGARDS_PT: 'Melhores cumprimentos,';
LISTA_DRONES_PT: '#Lista de drones utilizados';
LISTA_FIGURAS_PT: '#Lista de figuras';
ANEXO_PT: 'Anexo – Detalhes do Show' SPACE PROP VALUE;
LOCAL_PT: 'Local de realização –' SPACE SYMBOL? DOUBLE (SEPARATOR SPACE SYMBOL? DOUBLE)*;
DATA_PT: 'Data –' SPACE DAY SEPARATOR MONTH SEPARATOR YEAR;
HORA_PT: 'Hora –' SPACE HOUR SEPARATOR MINUTE;
DURACAO_PT: 'Duração –' SPACE VALUE SPACE WORD_MINUTES_PT;
WORD_MINUTES_PT: 'minutos';
WORD_UNITS_PT: 'unidades.';

// Keywords – English
DEAR: 'Dear,';
DEAR_SIRS: 'Dear Sirs,';
REFERENCE: 'Reference' SPACE PROP VALUE SPACE SEPARATOR SPACE DAY SEPARATOR MONTH SEPARATOR YEAR;
PROPOSTA_EN: 'Show Proposal';
BEING_CERTAIN_EN: 'Being certain that we will be the target of your preference.';
LOOKING_FORWARD: 'Looking forward to hearing from you soon.';
SUBSCRIBE_EN: 'We subscribe at your disposal.';
BEST_REGARDS_EN: 'Best regards,';
LISTA_DRONES_EN: '#List of used drones';
LISTA_FIGURAS_EN: '#List of figures';
ANEXO_EN: 'Attachment – Show Details' SPACE PROP VALUE;
LOCATION_EN: 'Location –' SPACE SYMBOL? DOUBLE (SEPARATOR SPACE SYMBOL? DOUBLE)*;
DATE_EN: 'Date –' SPACE DAY SEPARATOR MONTH SEPARATOR YEAR;
TIME_EN: 'Time –' SPACE HOUR SEPARATOR MINUTE;
DURATION_EN: 'Duration –' SPACE VALUE SPACE WORD_MINUTES_EN;
WORD_MINUTES_EN: 'minutes';
PROP: 'PROP-';
VAT: 'VAT:' SPACE VALUE;
WORD_UNITS_EN: 'units.';

DRONE_ENTRY_PT: NAME SPACE SEPARATOR SPACE VALUE SPACE WORD_UNITS_PT;
DRONE_ENTRY_EN: NAME SPACE SEPARATOR SPACE VALUE SPACE WORD_UNITS_EN;



FIGURE_ENTRY: VALUE SEPARATOR TEXT;

// Common
CRM_MANAGER: 'CRM Manager';
PAGE_BREAK: '[page break]';

// Basic tokens
DIGIT: [0-9];
LETTER: [a-zA-ZáàâãäéèêëíìîïóòôõöúùûüçÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ];
NAME: LETTER+ (SPACE LETTER+)*;
VALUE: DIGIT+;
DOUBLE: DIGIT+ ('.' DIGIT+)?;

MODEL: NAME (SPACE LETTER?(DOUBLE|VALUE))?;
// Separators
SEPARATOR: '/' | '-' | '.' | ':' | '–' | ',' ;

// Time components
HOUR: '0' DIGIT | '1' DIGIT | '2' [0-3];
MINUTE: [0-5] DIGIT;
DAY: '0' [1-9] | '1' DIGIT | '2' DIGIT | '3' [0-1];
MONTH: '0' [1-9] | '1' [0-2];
YEAR: DIGIT DIGIT DIGIT DIGIT;

// Currency
CURRENCY: '€' | '$' | '£';
INSURANCE_AMOUNT: VALUE CURRENCY;

// Text
TEXT: ~[\r\n]+;



parser grammar ProposalParser;

@header {
package gen;
}

options { tokenVocab=ProposalLexer; }

start
    : proposalPT
    | proposalEN
    | proposalEN_2
    ;

proposalPT
    : EXMOS_SENHORES BREAKLINE*
      companyBlock
      REFERENCIA BREAKLINE*
      PROPOSTA_PT BREAKLINE*
      body
      ESTANDO_CERTOS_PT BREAKLINE*
      SUBSCRIBE_PT BREAKLINE*
      BEST_REGARDS_PT BREAKLINE*
      NAME BREAKLINE CRM_MANAGER BREAKLINE*
      PAGE_BREAK BREAKLINE*
      ANEXO_PT BREAKLINE*
      locationInfoPT
      LISTA_DRONES_PT BREAKLINE (droneEntryPT)+
      BREAKLINE* LISTA_FIGURAS_PT (figureEntry)*
    ;

proposalEN
    : DEAR_SIRS BREAKLINE*
      companyBlock
      REFERENCE BREAKLINE*
      PROPOSTA_EN BREAKLINE*
      body
      BEING_CERTAIN_EN BREAKLINE*
      SUBSCRIBE_EN BREAKLINE*
      BEST_REGARDS_EN BREAKLINE*
      NAME BREAKLINE CRM_MANAGER BREAKLINE*
      PAGE_BREAK BREAKLINE*
      ANEXO_EN BREAKLINE*
      locationInfoEN
      LISTA_DRONES_EN BREAKLINE (droneEntryEN)+
      BREAKLINE* LISTA_FIGURAS_EN (figureEntry)*
    ;

proposalEN_2
    : DEAR BREAKLINE*
      NAME BREAKLINE*
      companyBlock
      REFERENCE BREAKLINE*
      PROPOSTA_EN BREAKLINE*
      body
      LOOKING_FORWARD BREAKLINE*
      BEST_REGARDS_EN BREAKLINE*
      NAME BREAKLINE CRM_MANAGER BREAKLINE*
      PAGE_BREAK BREAKLINE*
      ANEXO_EN BREAKLINE*
      locationInfoEN
      LISTA_DRONES_EN BREAKLINE (droneEntryEN)+
      BREAKLINE* LISTA_FIGURAS_EN (figureEntry)*
    ;

companyBlock
    : companyName BREAKLINE companyAddress BREAKLINE companyVat BREAKLINE*
    ;

companyName: NAME;
companyAddress: TEXT;
companyVat:
    | VAT
    | VALUE
    ;

body
    : TEXT BREAKLINE TEXT BREAKLINE* TEXT BREAKLINE*
    ;

proposalId
    : VALUE
    ;

// Location info for PT
locationInfoPT
    : LOCAL_PT BREAKLINE
      DATA_PT BREAKLINE
      HORA_PT BREAKLINE
      DURACAO_PT BREAKLINE*
    ;

// Location info for EN
locationInfoEN
    : LOCATION_EN BREAKLINE
      DATE_EN BREAKLINE
      TIME_EN BREAKLINE
      DURATION_EN BREAKLINE*
    ;

// Drone list
droneEntryPT
    : DRONE_ENTRY_PT BREAKLINE
    ;

droneEntryEN
    : DRONE_ENTRY_EN BREAKLINE
    ;

// Figure list (shared)
figureEntry
    : FIGURE_ENTRY BREAKLINE
    ;
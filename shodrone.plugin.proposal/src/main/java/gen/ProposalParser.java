package gen;
// Generated from ProposalParser.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ProposalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BREAKLINE=1, WS=2, SPACE=3, SYMBOL=4, EXMOS_SENHORES=5, REFERENCIA=6, 
		PROPOSTA_PT=7, ESTANDO_CERTOS_PT=8, SUBSCRIBE_PT=9, BEST_REGARDS_PT=10, 
		LISTA_DRONES_PT=11, LISTA_FIGURAS_PT=12, ANEXO_PT=13, LOCAL_PT=14, DATA_PT=15, 
		HORA_PT=16, DURACAO_PT=17, WORD_MINUTES_PT=18, WORD_UNITS_PT=19, DEAR=20, 
		DEAR_SIRS=21, REFERENCE=22, PROPOSTA_EN=23, BEING_CERTAIN_EN=24, LOOKING_FORWARD=25, 
		SUBSCRIBE_EN=26, BEST_REGARDS_EN=27, LISTA_DRONES_EN=28, LISTA_FIGURAS_EN=29, 
		ANEXO_EN=30, LOCATION_EN=31, DATE_EN=32, TIME_EN=33, DURATION_EN=34, WORD_MINUTES_EN=35, 
		PROP=36, VAT=37, WORD_UNITS_EN=38, DRONE_ENTRY_PT=39, DRONE_ENTRY_EN=40, 
		FIGURE_ENTRY=41, CRM_MANAGER=42, PAGE_BREAK=43, DIGIT=44, LETTER=45, NAME=46, 
		VALUE=47, DOUBLE=48, MODEL=49, SEPARATOR=50, HOUR=51, MINUTE=52, DAY=53, 
		MONTH=54, YEAR=55, CURRENCY=56, INSURANCE_AMOUNT=57, TEXT=58;
	public static final int
		RULE_start = 0, RULE_proposalPT = 1, RULE_proposalEN = 2, RULE_proposalEN_2 = 3, 
		RULE_companyBlock = 4, RULE_companyName = 5, RULE_companyAddress = 6, 
		RULE_companyVat = 7, RULE_body = 8, RULE_proposalId = 9, RULE_locationInfoPT = 10, 
		RULE_locationInfoEN = 11, RULE_droneEntryPT = 12, RULE_droneEntryEN = 13, 
		RULE_figureEntry = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "proposalPT", "proposalEN", "proposalEN_2", "companyBlock", 
			"companyName", "companyAddress", "companyVat", "body", "proposalId", 
			"locationInfoPT", "locationInfoEN", "droneEntryPT", "droneEntryEN", "figureEntry"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'Exmos. Senhores'", null, "'Proposta de Show'", 
			"'Estando certos que seremos alvo da V/ prefer\\u00EAncia.'", "'Subscrevemo-nos ao dispor.'", 
			"'Melhores cumprimentos,'", "'#Lista de drones utilizados'", "'#Lista de figuras'", 
			null, null, null, null, null, "'minutos'", "'unidades.'", "'Dear,'", 
			"'Dear Sirs,'", null, "'Show Proposal'", "'Being certain that we will be the target of your preference.'", 
			"'Looking forward to hearing from you soon.'", "'We subscribe at your disposal.'", 
			"'Best regards,'", "'#List of used drones'", "'#List of figures'", null, 
			null, null, null, null, "'minutes'", "'PROP-'", null, "'units.'", null, 
			null, null, "'CRM Manager'", "'[page break]'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BREAKLINE", "WS", "SPACE", "SYMBOL", "EXMOS_SENHORES", "REFERENCIA", 
			"PROPOSTA_PT", "ESTANDO_CERTOS_PT", "SUBSCRIBE_PT", "BEST_REGARDS_PT", 
			"LISTA_DRONES_PT", "LISTA_FIGURAS_PT", "ANEXO_PT", "LOCAL_PT", "DATA_PT", 
			"HORA_PT", "DURACAO_PT", "WORD_MINUTES_PT", "WORD_UNITS_PT", "DEAR", 
			"DEAR_SIRS", "REFERENCE", "PROPOSTA_EN", "BEING_CERTAIN_EN", "LOOKING_FORWARD", 
			"SUBSCRIBE_EN", "BEST_REGARDS_EN", "LISTA_DRONES_EN", "LISTA_FIGURAS_EN", 
			"ANEXO_EN", "LOCATION_EN", "DATE_EN", "TIME_EN", "DURATION_EN", "WORD_MINUTES_EN", 
			"PROP", "VAT", "WORD_UNITS_EN", "DRONE_ENTRY_PT", "DRONE_ENTRY_EN", "FIGURE_ENTRY", 
			"CRM_MANAGER", "PAGE_BREAK", "DIGIT", "LETTER", "NAME", "VALUE", "DOUBLE", 
			"MODEL", "SEPARATOR", "HOUR", "MINUTE", "DAY", "MONTH", "YEAR", "CURRENCY", 
			"INSURANCE_AMOUNT", "TEXT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ProposalParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ProposalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ProposalPTContext proposalPT() {
			return getRuleContext(ProposalPTContext.class,0);
		}
		public ProposalENContext proposalEN() {
			return getRuleContext(ProposalENContext.class,0);
		}
		public ProposalEN_2Context proposalEN_2() {
			return getRuleContext(ProposalEN_2Context.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			setState(33);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXMOS_SENHORES:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				proposalPT();
				}
				break;
			case DEAR_SIRS:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				proposalEN();
				}
				break;
			case DEAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(32);
				proposalEN_2();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProposalPTContext extends ParserRuleContext {
		public TerminalNode EXMOS_SENHORES() { return getToken(ProposalParser.EXMOS_SENHORES, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public CompanyBlockContext companyBlock() {
			return getRuleContext(CompanyBlockContext.class,0);
		}
		public TerminalNode REFERENCIA() { return getToken(ProposalParser.REFERENCIA, 0); }
		public TerminalNode PROPOSTA_PT() { return getToken(ProposalParser.PROPOSTA_PT, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode ESTANDO_CERTOS_PT() { return getToken(ProposalParser.ESTANDO_CERTOS_PT, 0); }
		public TerminalNode SUBSCRIBE_PT() { return getToken(ProposalParser.SUBSCRIBE_PT, 0); }
		public TerminalNode BEST_REGARDS_PT() { return getToken(ProposalParser.BEST_REGARDS_PT, 0); }
		public TerminalNode NAME() { return getToken(ProposalParser.NAME, 0); }
		public TerminalNode CRM_MANAGER() { return getToken(ProposalParser.CRM_MANAGER, 0); }
		public TerminalNode PAGE_BREAK() { return getToken(ProposalParser.PAGE_BREAK, 0); }
		public TerminalNode ANEXO_PT() { return getToken(ProposalParser.ANEXO_PT, 0); }
		public LocationInfoPTContext locationInfoPT() {
			return getRuleContext(LocationInfoPTContext.class,0);
		}
		public TerminalNode LISTA_DRONES_PT() { return getToken(ProposalParser.LISTA_DRONES_PT, 0); }
		public TerminalNode LISTA_FIGURAS_PT() { return getToken(ProposalParser.LISTA_FIGURAS_PT, 0); }
		public List<DroneEntryPTContext> droneEntryPT() {
			return getRuleContexts(DroneEntryPTContext.class);
		}
		public DroneEntryPTContext droneEntryPT(int i) {
			return getRuleContext(DroneEntryPTContext.class,i);
		}
		public List<FigureEntryContext> figureEntry() {
			return getRuleContexts(FigureEntryContext.class);
		}
		public FigureEntryContext figureEntry(int i) {
			return getRuleContext(FigureEntryContext.class,i);
		}
		public ProposalPTContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposalPT; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitProposalPT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProposalPTContext proposalPT() throws RecognitionException {
		ProposalPTContext _localctx = new ProposalPTContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_proposalPT);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(EXMOS_SENHORES);
			setState(36);
			match(BREAKLINE);
			setState(37);
			companyBlock();
			setState(38);
			match(REFERENCIA);
			setState(39);
			match(BREAKLINE);
			setState(40);
			match(PROPOSTA_PT);
			setState(41);
			match(BREAKLINE);
			setState(42);
			match(BREAKLINE);
			setState(43);
			body();
			setState(44);
			match(ESTANDO_CERTOS_PT);
			setState(45);
			match(BREAKLINE);
			setState(46);
			match(BREAKLINE);
			setState(47);
			match(SUBSCRIBE_PT);
			setState(48);
			match(BREAKLINE);
			setState(49);
			match(BREAKLINE);
			setState(50);
			match(BEST_REGARDS_PT);
			setState(51);
			match(BREAKLINE);
			setState(52);
			match(BREAKLINE);
			setState(53);
			match(NAME);
			setState(54);
			match(BREAKLINE);
			setState(55);
			match(CRM_MANAGER);
			setState(56);
			match(BREAKLINE);
			setState(57);
			match(BREAKLINE);
			setState(58);
			match(PAGE_BREAK);
			setState(59);
			match(BREAKLINE);
			setState(60);
			match(BREAKLINE);
			setState(61);
			match(ANEXO_PT);
			setState(62);
			match(BREAKLINE);
			setState(63);
			match(BREAKLINE);
			setState(64);
			locationInfoPT();
			setState(65);
			match(LISTA_DRONES_PT);
			setState(66);
			match(BREAKLINE);
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(67);
				droneEntryPT();
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DRONE_ENTRY_PT );
			setState(72);
			match(BREAKLINE);
			setState(73);
			match(BREAKLINE);
			setState(74);
			match(LISTA_FIGURAS_PT);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FIGURE_ENTRY) {
				{
				{
				setState(75);
				figureEntry();
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProposalENContext extends ParserRuleContext {
		public TerminalNode DEAR_SIRS() { return getToken(ProposalParser.DEAR_SIRS, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public CompanyBlockContext companyBlock() {
			return getRuleContext(CompanyBlockContext.class,0);
		}
		public TerminalNode REFERENCE() { return getToken(ProposalParser.REFERENCE, 0); }
		public TerminalNode PROPOSTA_EN() { return getToken(ProposalParser.PROPOSTA_EN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode BEING_CERTAIN_EN() { return getToken(ProposalParser.BEING_CERTAIN_EN, 0); }
		public TerminalNode SUBSCRIBE_EN() { return getToken(ProposalParser.SUBSCRIBE_EN, 0); }
		public TerminalNode BEST_REGARDS_EN() { return getToken(ProposalParser.BEST_REGARDS_EN, 0); }
		public TerminalNode NAME() { return getToken(ProposalParser.NAME, 0); }
		public TerminalNode CRM_MANAGER() { return getToken(ProposalParser.CRM_MANAGER, 0); }
		public TerminalNode PAGE_BREAK() { return getToken(ProposalParser.PAGE_BREAK, 0); }
		public TerminalNode ANEXO_EN() { return getToken(ProposalParser.ANEXO_EN, 0); }
		public LocationInfoENContext locationInfoEN() {
			return getRuleContext(LocationInfoENContext.class,0);
		}
		public TerminalNode LISTA_DRONES_EN() { return getToken(ProposalParser.LISTA_DRONES_EN, 0); }
		public TerminalNode LISTA_FIGURAS_EN() { return getToken(ProposalParser.LISTA_FIGURAS_EN, 0); }
		public List<DroneEntryENContext> droneEntryEN() {
			return getRuleContexts(DroneEntryENContext.class);
		}
		public DroneEntryENContext droneEntryEN(int i) {
			return getRuleContext(DroneEntryENContext.class,i);
		}
		public List<FigureEntryContext> figureEntry() {
			return getRuleContexts(FigureEntryContext.class);
		}
		public FigureEntryContext figureEntry(int i) {
			return getRuleContext(FigureEntryContext.class,i);
		}
		public ProposalENContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposalEN; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitProposalEN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProposalENContext proposalEN() throws RecognitionException {
		ProposalENContext _localctx = new ProposalENContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_proposalEN);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(DEAR_SIRS);
			setState(82);
			match(BREAKLINE);
			setState(83);
			companyBlock();
			setState(84);
			match(REFERENCE);
			setState(85);
			match(BREAKLINE);
			setState(86);
			match(PROPOSTA_EN);
			setState(87);
			match(BREAKLINE);
			setState(88);
			match(BREAKLINE);
			setState(89);
			body();
			setState(90);
			match(BEING_CERTAIN_EN);
			setState(91);
			match(BREAKLINE);
			setState(92);
			match(BREAKLINE);
			setState(93);
			match(SUBSCRIBE_EN);
			setState(94);
			match(BREAKLINE);
			setState(95);
			match(BREAKLINE);
			setState(96);
			match(BEST_REGARDS_EN);
			setState(97);
			match(BREAKLINE);
			setState(98);
			match(BREAKLINE);
			setState(99);
			match(NAME);
			setState(100);
			match(BREAKLINE);
			setState(101);
			match(CRM_MANAGER);
			setState(102);
			match(BREAKLINE);
			setState(103);
			match(BREAKLINE);
			setState(104);
			match(PAGE_BREAK);
			setState(105);
			match(BREAKLINE);
			setState(106);
			match(BREAKLINE);
			setState(107);
			match(ANEXO_EN);
			setState(108);
			match(BREAKLINE);
			setState(109);
			match(BREAKLINE);
			setState(110);
			locationInfoEN();
			setState(111);
			match(LISTA_DRONES_EN);
			setState(112);
			match(BREAKLINE);
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				droneEntryEN();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DRONE_ENTRY_EN );
			setState(118);
			match(BREAKLINE);
			setState(119);
			match(BREAKLINE);
			setState(120);
			match(LISTA_FIGURAS_EN);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FIGURE_ENTRY) {
				{
				{
				setState(121);
				figureEntry();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProposalEN_2Context extends ParserRuleContext {
		public TerminalNode DEAR() { return getToken(ProposalParser.DEAR, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public List<TerminalNode> NAME() { return getTokens(ProposalParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(ProposalParser.NAME, i);
		}
		public CompanyBlockContext companyBlock() {
			return getRuleContext(CompanyBlockContext.class,0);
		}
		public TerminalNode REFERENCE() { return getToken(ProposalParser.REFERENCE, 0); }
		public TerminalNode PROPOSTA_EN() { return getToken(ProposalParser.PROPOSTA_EN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode LOOKING_FORWARD() { return getToken(ProposalParser.LOOKING_FORWARD, 0); }
		public TerminalNode BEST_REGARDS_EN() { return getToken(ProposalParser.BEST_REGARDS_EN, 0); }
		public TerminalNode CRM_MANAGER() { return getToken(ProposalParser.CRM_MANAGER, 0); }
		public TerminalNode PAGE_BREAK() { return getToken(ProposalParser.PAGE_BREAK, 0); }
		public TerminalNode ANEXO_EN() { return getToken(ProposalParser.ANEXO_EN, 0); }
		public LocationInfoENContext locationInfoEN() {
			return getRuleContext(LocationInfoENContext.class,0);
		}
		public TerminalNode LISTA_DRONES_EN() { return getToken(ProposalParser.LISTA_DRONES_EN, 0); }
		public TerminalNode LISTA_FIGURAS_EN() { return getToken(ProposalParser.LISTA_FIGURAS_EN, 0); }
		public List<DroneEntryENContext> droneEntryEN() {
			return getRuleContexts(DroneEntryENContext.class);
		}
		public DroneEntryENContext droneEntryEN(int i) {
			return getRuleContext(DroneEntryENContext.class,i);
		}
		public List<FigureEntryContext> figureEntry() {
			return getRuleContexts(FigureEntryContext.class);
		}
		public FigureEntryContext figureEntry(int i) {
			return getRuleContext(FigureEntryContext.class,i);
		}
		public ProposalEN_2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposalEN_2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitProposalEN_2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProposalEN_2Context proposalEN_2() throws RecognitionException {
		ProposalEN_2Context _localctx = new ProposalEN_2Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_proposalEN_2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(DEAR);
			setState(128);
			match(BREAKLINE);
			setState(129);
			match(NAME);
			setState(130);
			match(BREAKLINE);
			setState(131);
			companyBlock();
			setState(132);
			match(REFERENCE);
			setState(133);
			match(BREAKLINE);
			setState(134);
			match(PROPOSTA_EN);
			setState(135);
			match(BREAKLINE);
			setState(136);
			match(BREAKLINE);
			setState(137);
			body();
			setState(138);
			match(LOOKING_FORWARD);
			setState(139);
			match(BREAKLINE);
			setState(140);
			match(BREAKLINE);
			setState(141);
			match(BEST_REGARDS_EN);
			setState(142);
			match(BREAKLINE);
			setState(143);
			match(BREAKLINE);
			setState(144);
			match(NAME);
			setState(145);
			match(BREAKLINE);
			setState(146);
			match(CRM_MANAGER);
			setState(147);
			match(BREAKLINE);
			setState(148);
			match(BREAKLINE);
			setState(149);
			match(PAGE_BREAK);
			setState(150);
			match(BREAKLINE);
			setState(151);
			match(BREAKLINE);
			setState(152);
			match(ANEXO_EN);
			setState(153);
			match(BREAKLINE);
			setState(154);
			match(BREAKLINE);
			setState(155);
			locationInfoEN();
			setState(156);
			match(LISTA_DRONES_EN);
			setState(157);
			match(BREAKLINE);
			setState(159); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(158);
				droneEntryEN();
				}
				}
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DRONE_ENTRY_EN );
			setState(163);
			match(BREAKLINE);
			setState(164);
			match(BREAKLINE);
			setState(165);
			match(LISTA_FIGURAS_EN);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FIGURE_ENTRY) {
				{
				{
				setState(166);
				figureEntry();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompanyBlockContext extends ParserRuleContext {
		public CompanyNameContext companyName() {
			return getRuleContext(CompanyNameContext.class,0);
		}
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public CompanyAddressContext companyAddress() {
			return getRuleContext(CompanyAddressContext.class,0);
		}
		public CompanyVatContext companyVat() {
			return getRuleContext(CompanyVatContext.class,0);
		}
		public CompanyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_companyBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitCompanyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompanyBlockContext companyBlock() throws RecognitionException {
		CompanyBlockContext _localctx = new CompanyBlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_companyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			companyName();
			setState(173);
			match(BREAKLINE);
			setState(174);
			companyAddress();
			setState(175);
			match(BREAKLINE);
			setState(176);
			companyVat();
			setState(177);
			match(BREAKLINE);
			setState(178);
			match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompanyNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(ProposalParser.NAME, 0); }
		public CompanyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_companyName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitCompanyName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompanyNameContext companyName() throws RecognitionException {
		CompanyNameContext _localctx = new CompanyNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_companyName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompanyAddressContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(ProposalParser.TEXT, 0); }
		public CompanyAddressContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_companyAddress; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitCompanyAddress(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompanyAddressContext companyAddress() throws RecognitionException {
		CompanyAddressContext _localctx = new CompanyAddressContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_companyAddress);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompanyVatContext extends ParserRuleContext {
		public TerminalNode VAT() { return getToken(ProposalParser.VAT, 0); }
		public TerminalNode VALUE() { return getToken(ProposalParser.VALUE, 0); }
		public CompanyVatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_companyVat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitCompanyVat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompanyVatContext companyVat() throws RecognitionException {
		CompanyVatContext _localctx = new CompanyVatContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_companyVat);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BREAKLINE:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case VAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				match(VAT);
				}
				break;
			case VALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
				match(VALUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(ProposalParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ProposalParser.TEXT, i);
		}
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(TEXT);
			setState(190);
			match(BREAKLINE);
			setState(191);
			match(TEXT);
			setState(192);
			match(BREAKLINE);
			setState(193);
			match(BREAKLINE);
			setState(194);
			match(TEXT);
			setState(195);
			match(BREAKLINE);
			setState(196);
			match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProposalIdContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(ProposalParser.VALUE, 0); }
		public ProposalIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposalId; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitProposalId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProposalIdContext proposalId() throws RecognitionException {
		ProposalIdContext _localctx = new ProposalIdContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_proposalId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocationInfoPTContext extends ParserRuleContext {
		public TerminalNode LOCAL_PT() { return getToken(ProposalParser.LOCAL_PT, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode DATA_PT() { return getToken(ProposalParser.DATA_PT, 0); }
		public TerminalNode HORA_PT() { return getToken(ProposalParser.HORA_PT, 0); }
		public TerminalNode DURACAO_PT() { return getToken(ProposalParser.DURACAO_PT, 0); }
		public LocationInfoPTContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationInfoPT; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitLocationInfoPT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationInfoPTContext locationInfoPT() throws RecognitionException {
		LocationInfoPTContext _localctx = new LocationInfoPTContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_locationInfoPT);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(LOCAL_PT);
			setState(201);
			match(BREAKLINE);
			setState(202);
			match(DATA_PT);
			setState(203);
			match(BREAKLINE);
			setState(204);
			match(HORA_PT);
			setState(205);
			match(BREAKLINE);
			setState(206);
			match(DURACAO_PT);
			setState(207);
			match(BREAKLINE);
			setState(208);
			match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocationInfoENContext extends ParserRuleContext {
		public TerminalNode LOCATION_EN() { return getToken(ProposalParser.LOCATION_EN, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode DATE_EN() { return getToken(ProposalParser.DATE_EN, 0); }
		public TerminalNode TIME_EN() { return getToken(ProposalParser.TIME_EN, 0); }
		public TerminalNode DURATION_EN() { return getToken(ProposalParser.DURATION_EN, 0); }
		public LocationInfoENContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationInfoEN; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitLocationInfoEN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationInfoENContext locationInfoEN() throws RecognitionException {
		LocationInfoENContext _localctx = new LocationInfoENContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_locationInfoEN);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(LOCATION_EN);
			setState(211);
			match(BREAKLINE);
			setState(212);
			match(DATE_EN);
			setState(213);
			match(BREAKLINE);
			setState(214);
			match(TIME_EN);
			setState(215);
			match(BREAKLINE);
			setState(216);
			match(DURATION_EN);
			setState(217);
			match(BREAKLINE);
			setState(218);
			match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DroneEntryPTContext extends ParserRuleContext {
		public TerminalNode DRONE_ENTRY_PT() { return getToken(ProposalParser.DRONE_ENTRY_PT, 0); }
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public DroneEntryPTContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_droneEntryPT; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitDroneEntryPT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DroneEntryPTContext droneEntryPT() throws RecognitionException {
		DroneEntryPTContext _localctx = new DroneEntryPTContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_droneEntryPT);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(DRONE_ENTRY_PT);
			setState(221);
			match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DroneEntryENContext extends ParserRuleContext {
		public TerminalNode DRONE_ENTRY_EN() { return getToken(ProposalParser.DRONE_ENTRY_EN, 0); }
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public DroneEntryENContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_droneEntryEN; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitDroneEntryEN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DroneEntryENContext droneEntryEN() throws RecognitionException {
		DroneEntryENContext _localctx = new DroneEntryENContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_droneEntryEN);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(DRONE_ENTRY_EN);
			setState(224);
			match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FigureEntryContext extends ParserRuleContext {
		public TerminalNode FIGURE_ENTRY() { return getToken(ProposalParser.FIGURE_ENTRY, 0); }
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public FigureEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_figureEntry; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitFigureEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FigureEntryContext figureEntry() throws RecognitionException {
		FigureEntryContext _localctx = new FigureEntryContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_figureEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(FIGURE_ENTRY);
			setState(227);
			match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001:\u00e6\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\"\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0004\u0001E\b\u0001\u000b\u0001\f\u0001F\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0005\u0001M\b\u0001\n\u0001\f\u0001P\t\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0004\u0002s\b\u0002\u000b\u0002\f\u0002"+
		"t\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002{\b\u0002"+
		"\n\u0002\f\u0002~\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003\u00a0\b\u0003"+
		"\u000b\u0003\f\u0003\u00a1\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0005\u0003\u00a8\b\u0003\n\u0003\f\u0003\u00ab\t\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00bc\b\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0000"+
		"\u0000\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u0000\u0000\u00e0\u0000!\u0001\u0000\u0000\u0000\u0002"+
		"#\u0001\u0000\u0000\u0000\u0004Q\u0001\u0000\u0000\u0000\u0006\u007f\u0001"+
		"\u0000\u0000\u0000\b\u00ac\u0001\u0000\u0000\u0000\n\u00b4\u0001\u0000"+
		"\u0000\u0000\f\u00b6\u0001\u0000\u0000\u0000\u000e\u00bb\u0001\u0000\u0000"+
		"\u0000\u0010\u00bd\u0001\u0000\u0000\u0000\u0012\u00c6\u0001\u0000\u0000"+
		"\u0000\u0014\u00c8\u0001\u0000\u0000\u0000\u0016\u00d2\u0001\u0000\u0000"+
		"\u0000\u0018\u00dc\u0001\u0000\u0000\u0000\u001a\u00df\u0001\u0000\u0000"+
		"\u0000\u001c\u00e2\u0001\u0000\u0000\u0000\u001e\"\u0003\u0002\u0001\u0000"+
		"\u001f\"\u0003\u0004\u0002\u0000 \"\u0003\u0006\u0003\u0000!\u001e\u0001"+
		"\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000! \u0001\u0000\u0000"+
		"\u0000\"\u0001\u0001\u0000\u0000\u0000#$\u0005\u0005\u0000\u0000$%\u0005"+
		"\u0001\u0000\u0000%&\u0003\b\u0004\u0000&\'\u0005\u0006\u0000\u0000\'"+
		"(\u0005\u0001\u0000\u0000()\u0005\u0007\u0000\u0000)*\u0005\u0001\u0000"+
		"\u0000*+\u0005\u0001\u0000\u0000+,\u0003\u0010\b\u0000,-\u0005\b\u0000"+
		"\u0000-.\u0005\u0001\u0000\u0000./\u0005\u0001\u0000\u0000/0\u0005\t\u0000"+
		"\u000001\u0005\u0001\u0000\u000012\u0005\u0001\u0000\u000023\u0005\n\u0000"+
		"\u000034\u0005\u0001\u0000\u000045\u0005\u0001\u0000\u000056\u0005.\u0000"+
		"\u000067\u0005\u0001\u0000\u000078\u0005*\u0000\u000089\u0005\u0001\u0000"+
		"\u00009:\u0005\u0001\u0000\u0000:;\u0005+\u0000\u0000;<\u0005\u0001\u0000"+
		"\u0000<=\u0005\u0001\u0000\u0000=>\u0005\r\u0000\u0000>?\u0005\u0001\u0000"+
		"\u0000?@\u0005\u0001\u0000\u0000@A\u0003\u0014\n\u0000AB\u0005\u000b\u0000"+
		"\u0000BD\u0005\u0001\u0000\u0000CE\u0003\u0018\f\u0000DC\u0001\u0000\u0000"+
		"\u0000EF\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000"+
		"\u0000\u0000GH\u0001\u0000\u0000\u0000HI\u0005\u0001\u0000\u0000IJ\u0005"+
		"\u0001\u0000\u0000JN\u0005\f\u0000\u0000KM\u0003\u001c\u000e\u0000LK\u0001"+
		"\u0000\u0000\u0000MP\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000"+
		"NO\u0001\u0000\u0000\u0000O\u0003\u0001\u0000\u0000\u0000PN\u0001\u0000"+
		"\u0000\u0000QR\u0005\u0015\u0000\u0000RS\u0005\u0001\u0000\u0000ST\u0003"+
		"\b\u0004\u0000TU\u0005\u0016\u0000\u0000UV\u0005\u0001\u0000\u0000VW\u0005"+
		"\u0017\u0000\u0000WX\u0005\u0001\u0000\u0000XY\u0005\u0001\u0000\u0000"+
		"YZ\u0003\u0010\b\u0000Z[\u0005\u0018\u0000\u0000[\\\u0005\u0001\u0000"+
		"\u0000\\]\u0005\u0001\u0000\u0000]^\u0005\u001a\u0000\u0000^_\u0005\u0001"+
		"\u0000\u0000_`\u0005\u0001\u0000\u0000`a\u0005\u001b\u0000\u0000ab\u0005"+
		"\u0001\u0000\u0000bc\u0005\u0001\u0000\u0000cd\u0005.\u0000\u0000de\u0005"+
		"\u0001\u0000\u0000ef\u0005*\u0000\u0000fg\u0005\u0001\u0000\u0000gh\u0005"+
		"\u0001\u0000\u0000hi\u0005+\u0000\u0000ij\u0005\u0001\u0000\u0000jk\u0005"+
		"\u0001\u0000\u0000kl\u0005\u001e\u0000\u0000lm\u0005\u0001\u0000\u0000"+
		"mn\u0005\u0001\u0000\u0000no\u0003\u0016\u000b\u0000op\u0005\u001c\u0000"+
		"\u0000pr\u0005\u0001\u0000\u0000qs\u0003\u001a\r\u0000rq\u0001\u0000\u0000"+
		"\u0000st\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0005\u0001\u0000\u0000wx\u0005"+
		"\u0001\u0000\u0000x|\u0005\u001d\u0000\u0000y{\u0003\u001c\u000e\u0000"+
		"zy\u0001\u0000\u0000\u0000{~\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000"+
		"\u0000|}\u0001\u0000\u0000\u0000}\u0005\u0001\u0000\u0000\u0000~|\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0005\u0014\u0000\u0000\u0080\u0081\u0005"+
		"\u0001\u0000\u0000\u0081\u0082\u0005.\u0000\u0000\u0082\u0083\u0005\u0001"+
		"\u0000\u0000\u0083\u0084\u0003\b\u0004\u0000\u0084\u0085\u0005\u0016\u0000"+
		"\u0000\u0085\u0086\u0005\u0001\u0000\u0000\u0086\u0087\u0005\u0017\u0000"+
		"\u0000\u0087\u0088\u0005\u0001\u0000\u0000\u0088\u0089\u0005\u0001\u0000"+
		"\u0000\u0089\u008a\u0003\u0010\b\u0000\u008a\u008b\u0005\u0019\u0000\u0000"+
		"\u008b\u008c\u0005\u0001\u0000\u0000\u008c\u008d\u0005\u0001\u0000\u0000"+
		"\u008d\u008e\u0005\u001b\u0000\u0000\u008e\u008f\u0005\u0001\u0000\u0000"+
		"\u008f\u0090\u0005\u0001\u0000\u0000\u0090\u0091\u0005.\u0000\u0000\u0091"+
		"\u0092\u0005\u0001\u0000\u0000\u0092\u0093\u0005*\u0000\u0000\u0093\u0094"+
		"\u0005\u0001\u0000\u0000\u0094\u0095\u0005\u0001\u0000\u0000\u0095\u0096"+
		"\u0005+\u0000\u0000\u0096\u0097\u0005\u0001\u0000\u0000\u0097\u0098\u0005"+
		"\u0001\u0000\u0000\u0098\u0099\u0005\u001e\u0000\u0000\u0099\u009a\u0005"+
		"\u0001\u0000\u0000\u009a\u009b\u0005\u0001\u0000\u0000\u009b\u009c\u0003"+
		"\u0016\u000b\u0000\u009c\u009d\u0005\u001c\u0000\u0000\u009d\u009f\u0005"+
		"\u0001\u0000\u0000\u009e\u00a0\u0003\u001a\r\u0000\u009f\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a4\u0005\u0001\u0000\u0000\u00a4\u00a5\u0005\u0001"+
		"\u0000\u0000\u00a5\u00a9\u0005\u001d\u0000\u0000\u00a6\u00a8\u0003\u001c"+
		"\u000e\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00ab\u0001\u0000"+
		"\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000"+
		"\u0000\u0000\u00aa\u0007\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0003\n\u0005\u0000\u00ad\u00ae\u0005\u0001\u0000"+
		"\u0000\u00ae\u00af\u0003\f\u0006\u0000\u00af\u00b0\u0005\u0001\u0000\u0000"+
		"\u00b0\u00b1\u0003\u000e\u0007\u0000\u00b1\u00b2\u0005\u0001\u0000\u0000"+
		"\u00b2\u00b3\u0005\u0001\u0000\u0000\u00b3\t\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005.\u0000\u0000\u00b5\u000b\u0001\u0000\u0000\u0000\u00b6\u00b7"+
		"\u0005:\u0000\u0000\u00b7\r\u0001\u0000\u0000\u0000\u00b8\u00bc\u0001"+
		"\u0000\u0000\u0000\u00b9\u00bc\u0005%\u0000\u0000\u00ba\u00bc\u0005/\u0000"+
		"\u0000\u00bb\u00b8\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bc\u000f\u0001\u0000\u0000"+
		"\u0000\u00bd\u00be\u0005:\u0000\u0000\u00be\u00bf\u0005\u0001\u0000\u0000"+
		"\u00bf\u00c0\u0005:\u0000\u0000\u00c0\u00c1\u0005\u0001\u0000\u0000\u00c1"+
		"\u00c2\u0005\u0001\u0000\u0000\u00c2\u00c3\u0005:\u0000\u0000\u00c3\u00c4"+
		"\u0005\u0001\u0000\u0000\u00c4\u00c5\u0005\u0001\u0000\u0000\u00c5\u0011"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005/\u0000\u0000\u00c7\u0013\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c9\u0005\u000e\u0000\u0000\u00c9\u00ca\u0005"+
		"\u0001\u0000\u0000\u00ca\u00cb\u0005\u000f\u0000\u0000\u00cb\u00cc\u0005"+
		"\u0001\u0000\u0000\u00cc\u00cd\u0005\u0010\u0000\u0000\u00cd\u00ce\u0005"+
		"\u0001\u0000\u0000\u00ce\u00cf\u0005\u0011\u0000\u0000\u00cf\u00d0\u0005"+
		"\u0001\u0000\u0000\u00d0\u00d1\u0005\u0001\u0000\u0000\u00d1\u0015\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d3\u0005\u001f\u0000\u0000\u00d3\u00d4\u0005"+
		"\u0001\u0000\u0000\u00d4\u00d5\u0005 \u0000\u0000\u00d5\u00d6\u0005\u0001"+
		"\u0000\u0000\u00d6\u00d7\u0005!\u0000\u0000\u00d7\u00d8\u0005\u0001\u0000"+
		"\u0000\u00d8\u00d9\u0005\"\u0000\u0000\u00d9\u00da\u0005\u0001\u0000\u0000"+
		"\u00da\u00db\u0005\u0001\u0000\u0000\u00db\u0017\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0005\'\u0000\u0000\u00dd\u00de\u0005\u0001\u0000\u0000\u00de"+
		"\u0019\u0001\u0000\u0000\u0000\u00df\u00e0\u0005(\u0000\u0000\u00e0\u00e1"+
		"\u0005\u0001\u0000\u0000\u00e1\u001b\u0001\u0000\u0000\u0000\u00e2\u00e3"+
		"\u0005)\u0000\u0000\u00e3\u00e4\u0005\u0001\u0000\u0000\u00e4\u001d\u0001"+
		"\u0000\u0000\u0000\b!FNt|\u00a1\u00a9\u00bb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
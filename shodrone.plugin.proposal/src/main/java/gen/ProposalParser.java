// Generated from ProposalParser.g4 by ANTLR 4.13.2

package gen;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ProposalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BREAKLINE=1, SEPARATOR=2, SYMBOL=3, DIGIT=4, DIGITWITHOUT0=5, LETTER=6, 
		CHAR=7, TEXT=8, NAME=9, VALUE=10, MINUTE=11, HOUR=12, DAY=13, MONTH=14, 
		M1=15, M2=16, P1=17, P2=18, EXMOS_SENHORES=19, DEAR_SIRS=20, DEAR=21, 
		REFERENCIA=22, REFERENCE=23, PROPOSTA_PT=24, PROPOSTA_EN=25, LINK=26, 
		AI_INSURANCE=27, INSURANCEAMOUNT=28, SUBSCRIBE_PT=29, SUBSCRIBE_EN=30, 
		BEST_REGARDS=31, CRM_MANAGER=32, ESTANDO_CERTOS_PT=33, BEING_CERTAIN_EN=34, 
		LOOKING_FORWARD_EN=35, LISTA_DRONES_PT=36, LISTA_DRONES_EN=37, LISTA_FIGURAS_PT=38, 
		LISTA_FIGURAS_EN=39, WORD_MINUTES=40, ANEXO_PT=41, ANEXO_EN=42, LOCAL_PT=43, 
		DATA_PT=44, HORA_PT=45, DURACAO_PT=46, LOCATION_EN=47, DATE_EN=48, TIME_EN=49, 
		DURATION_EN=50, PAGE_BREAK=51;
	public static final int
		RULE_start = 0, RULE_a1 = 1, RULE_a2 = 2, RULE_a3 = 3, RULE_b = 4, RULE_company = 5, 
		RULE_name = 6, RULE_address = 7, RULE_vat = 8, RULE_c1 = 9, RULE_c2 = 10, 
		RULE_proposaldate = 11, RULE_proposalnumber = 12, RULE_date = 13, RULE_day = 14, 
		RULE_month = 15, RULE_year = 16, RULE_d1 = 17, RULE_d2 = 18, RULE_e1 = 19, 
		RULE_e2 = 20, RULE_e3 = 21, RULE_f1 = 22, RULE_f2 = 23, RULE_f3 = 24, 
		RULE_g1 = 25, RULE_g2 = 26, RULE_g3 = 27, RULE_h = 28, RULE_i1 = 29, RULE_i2 = 30, 
		RULE_j1 = 31, RULE_j2 = 32, RULE_time = 33, RULE_hour = 34, RULE_minute = 35, 
		RULE_duration = 36, RULE_k1 = 37, RULE_k2 = 38, RULE_l1 = 39, RULE_l2 = 40, 
		RULE_m1 = 41, RULE_m2 = 42, RULE_n1 = 43, RULE_n2 = 44, RULE_o1 = 45, 
		RULE_o2 = 46, RULE_p1 = 47, RULE_p2 = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "a1", "a2", "a3", "b", "company", "name", "address", "vat", 
			"c1", "c2", "proposaldate", "proposalnumber", "date", "day", "month", 
			"year", "d1", "d2", "e1", "e2", "e3", "f1", "f2", "f3", "g1", "g2", "g3", 
			"h", "i1", "i2", "j1", "j2", "time", "hour", "minute", "duration", "k1", 
			"k2", "l1", "l2", "m1", "m2", "n1", "n2", "o1", "o2", "p1", "p2"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\\n'", null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "'Exmos. Senhores'", 
			"'Dear Sirs,'", "'Dear,'", "'Refer\\u00EAncia'", "'Reference'", "'Proposta de Show'", 
			"'Show Proposal'", null, null, null, "'Subscrevemo-nos ao dispor.'", 
			"'We subscribe at your disposal.'", null, "'CRM Manager'", "'Estando certos que seremos alvo da V/ prefer\\u00EAncia.'", 
			"'Being certain that we will be the target of your preference.'", "'Looking forward to hearing from you soon.'", 
			"'#Lista de drones utilizados'", "'#List of used drones'", "'#Lista de figuras'", 
			"'#List of figures'", "' minutos'", "'Anexo \\u2013 Detalhes do Show'", 
			"'Attachment \\u2013 Show Details'", "'Local de realiza\\u00E7\\u00E3o \\u2013'", 
			"'Data \\u2013'", "'Hora \\u2013'", "'Dura\\u00E7\\u00E3o \\u2013'", 
			"'Location \\u2013'", "'Date \\u2013'", "'Time \\u2013'", "'Duration \\u2013'", 
			"'[page break]'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BREAKLINE", "SEPARATOR", "SYMBOL", "DIGIT", "DIGITWITHOUT0", "LETTER", 
			"CHAR", "TEXT", "NAME", "VALUE", "MINUTE", "HOUR", "DAY", "MONTH", "M1", 
			"M2", "P1", "P2", "EXMOS_SENHORES", "DEAR_SIRS", "DEAR", "REFERENCIA", 
			"REFERENCE", "PROPOSTA_PT", "PROPOSTA_EN", "LINK", "AI_INSURANCE", "INSURANCEAMOUNT", 
			"SUBSCRIBE_PT", "SUBSCRIBE_EN", "BEST_REGARDS", "CRM_MANAGER", "ESTANDO_CERTOS_PT", 
			"BEING_CERTAIN_EN", "LOOKING_FORWARD_EN", "LISTA_DRONES_PT", "LISTA_DRONES_EN", 
			"LISTA_FIGURAS_PT", "LISTA_FIGURAS_EN", "WORD_MINUTES", "ANEXO_PT", "ANEXO_EN", 
			"LOCAL_PT", "DATA_PT", "HORA_PT", "DURACAO_PT", "LOCATION_EN", "DATE_EN", 
			"TIME_EN", "DURATION_EN", "PAGE_BREAK"
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
		public A1Context a1() {
			return getRuleContext(A1Context.class,0);
		}
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public C1Context c1() {
			return getRuleContext(C1Context.class,0);
		}
		public D1Context d1() {
			return getRuleContext(D1Context.class,0);
		}
		public E1Context e1() {
			return getRuleContext(E1Context.class,0);
		}
		public F1Context f1() {
			return getRuleContext(F1Context.class,0);
		}
		public G1Context g1() {
			return getRuleContext(G1Context.class,0);
		}
		public HContext h() {
			return getRuleContext(HContext.class,0);
		}
		public I1Context i1() {
			return getRuleContext(I1Context.class,0);
		}
		public J1Context j1() {
			return getRuleContext(J1Context.class,0);
		}
		public K1Context k1() {
			return getRuleContext(K1Context.class,0);
		}
		public L1Context l1() {
			return getRuleContext(L1Context.class,0);
		}
		public N1Context n1() {
			return getRuleContext(N1Context.class,0);
		}
		public O1Context o1() {
			return getRuleContext(O1Context.class,0);
		}
		public A2Context a2() {
			return getRuleContext(A2Context.class,0);
		}
		public C2Context c2() {
			return getRuleContext(C2Context.class,0);
		}
		public D2Context d2() {
			return getRuleContext(D2Context.class,0);
		}
		public E2Context e2() {
			return getRuleContext(E2Context.class,0);
		}
		public F2Context f2() {
			return getRuleContext(F2Context.class,0);
		}
		public G2Context g2() {
			return getRuleContext(G2Context.class,0);
		}
		public I2Context i2() {
			return getRuleContext(I2Context.class,0);
		}
		public J2Context j2() {
			return getRuleContext(J2Context.class,0);
		}
		public K2Context k2() {
			return getRuleContext(K2Context.class,0);
		}
		public L2Context l2() {
			return getRuleContext(L2Context.class,0);
		}
		public N2Context n2() {
			return getRuleContext(N2Context.class,0);
		}
		public O2Context o2() {
			return getRuleContext(O2Context.class,0);
		}
		public A3Context a3() {
			return getRuleContext(A3Context.class,0);
		}
		public E3Context e3() {
			return getRuleContext(E3Context.class,0);
		}
		public F3Context f3() {
			return getRuleContext(F3Context.class,0);
		}
		public G3Context g3() {
			return getRuleContext(G3Context.class,0);
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
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXMOS_SENHORES:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				a1();
				setState(99);
				b();
				setState(100);
				c1();
				setState(101);
				d1();
				setState(102);
				e1();
				setState(103);
				f1();
				setState(104);
				g1();
				setState(105);
				h();
				setState(106);
				i1();
				setState(107);
				j1();
				setState(108);
				k1();
				setState(109);
				l1();
				setState(110);
				n1();
				setState(111);
				o1();
				}
				break;
			case DEAR_SIRS:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				a2();
				setState(114);
				b();
				setState(115);
				c2();
				setState(116);
				d2();
				setState(117);
				e2();
				setState(118);
				f2();
				setState(119);
				g2();
				setState(120);
				h();
				setState(121);
				i2();
				setState(122);
				j2();
				setState(123);
				k2();
				setState(124);
				l2();
				setState(125);
				n2();
				setState(126);
				o2();
				}
				break;
			case DEAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				a3();
				setState(129);
				b();
				setState(130);
				c2();
				setState(131);
				d2();
				setState(132);
				e3();
				setState(133);
				f3();
				setState(134);
				g3();
				setState(135);
				h();
				setState(136);
				i2();
				setState(137);
				j2();
				setState(138);
				k2();
				setState(139);
				l2();
				setState(140);
				n2();
				setState(141);
				o2();
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
	public static class A1Context extends ParserRuleContext {
		public TerminalNode EXMOS_SENHORES() { return getToken(ProposalParser.EXMOS_SENHORES, 0); }
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public A1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitA1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final A1Context a1() throws RecognitionException {
		A1Context _localctx = new A1Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_a1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(EXMOS_SENHORES);
			setState(146);
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
	public static class A2Context extends ParserRuleContext {
		public TerminalNode DEAR_SIRS() { return getToken(ProposalParser.DEAR_SIRS, 0); }
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public A2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitA2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final A2Context a2() throws RecognitionException {
		A2Context _localctx = new A2Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_a2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(DEAR_SIRS);
			setState(149);
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
	public static class A3Context extends ParserRuleContext {
		public TerminalNode DEAR() { return getToken(ProposalParser.DEAR, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public A3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitA3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final A3Context a3() throws RecognitionException {
		A3Context _localctx = new A3Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_a3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(DEAR);
			setState(152);
			match(BREAKLINE);
			setState(153);
			name();
			setState(154);
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
	public static class BContext extends ParserRuleContext {
		public CompanyContext company() {
			return getRuleContext(CompanyContext.class,0);
		}
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public AddressContext address() {
			return getRuleContext(AddressContext.class,0);
		}
		public VatContext vat() {
			return getRuleContext(VatContext.class,0);
		}
		public BContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitB(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BContext b() throws RecognitionException {
		BContext _localctx = new BContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_b);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			company();
			setState(157);
			match(BREAKLINE);
			setState(158);
			address();
			setState(159);
			match(BREAKLINE);
			setState(160);
			vat();
			setState(161);
			match(BREAKLINE);
			setState(162);
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
	public static class CompanyContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(ProposalParser.NAME, 0); }
		public CompanyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_company; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitCompany(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompanyContext company() throws RecognitionException {
		CompanyContext _localctx = new CompanyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_company);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
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
	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(ProposalParser.NAME, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
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
	public static class AddressContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(ProposalParser.TEXT, 0); }
		public AddressContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_address; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitAddress(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddressContext address() throws RecognitionException {
		AddressContext _localctx = new AddressContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_address);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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
	public static class VatContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(ProposalParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(ProposalParser.DIGIT, i);
		}
		public VatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitVat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VatContext vat() throws RecognitionException {
		VatContext _localctx = new VatContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_vat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170);
				match(DIGIT);
				}
				}
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
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
	public static class C1Context extends ParserRuleContext {
		public TerminalNode REFERENCIA() { return getToken(ProposalParser.REFERENCIA, 0); }
		public ProposaldateContext proposaldate() {
			return getRuleContext(ProposaldateContext.class,0);
		}
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public C1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitC1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final C1Context c1() throws RecognitionException {
		C1Context _localctx = new C1Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_c1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(REFERENCIA);
			setState(176);
			proposaldate();
			setState(177);
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
	public static class C2Context extends ParserRuleContext {
		public TerminalNode REFERENCE() { return getToken(ProposalParser.REFERENCE, 0); }
		public ProposaldateContext proposaldate() {
			return getRuleContext(ProposaldateContext.class,0);
		}
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public C2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitC2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final C2Context c2() throws RecognitionException {
		C2Context _localctx = new C2Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_c2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(REFERENCE);
			setState(180);
			proposaldate();
			setState(181);
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
	public static class ProposaldateContext extends ParserRuleContext {
		public ProposalnumberContext proposalnumber() {
			return getRuleContext(ProposalnumberContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(ProposalParser.SEPARATOR, 0); }
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public ProposaldateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposaldate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitProposaldate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProposaldateContext proposaldate() throws RecognitionException {
		ProposaldateContext _localctx = new ProposaldateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_proposaldate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			proposalnumber();
			setState(184);
			match(SEPARATOR);
			setState(185);
			date();
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
	public static class ProposalnumberContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(ProposalParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(ProposalParser.DIGIT, i);
		}
		public ProposalnumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposalnumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitProposalnumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProposalnumberContext proposalnumber() throws RecognitionException {
		ProposalnumberContext _localctx = new ProposalnumberContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_proposalnumber);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(187);
				match(DIGIT);
				}
				}
				setState(190); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
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
	public static class DateContext extends ParserRuleContext {
		public DayContext day() {
			return getRuleContext(DayContext.class,0);
		}
		public List<TerminalNode> SEPARATOR() { return getTokens(ProposalParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ProposalParser.SEPARATOR, i);
		}
		public MonthContext month() {
			return getRuleContext(MonthContext.class,0);
		}
		public YearContext year() {
			return getRuleContext(YearContext.class,0);
		}
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			day();
			setState(193);
			match(SEPARATOR);
			setState(194);
			month();
			setState(195);
			match(SEPARATOR);
			setState(196);
			year();
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
	public static class DayContext extends ParserRuleContext {
		public TerminalNode DAY() { return getToken(ProposalParser.DAY, 0); }
		public DayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_day; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitDay(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DayContext day() throws RecognitionException {
		DayContext _localctx = new DayContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_day);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(DAY);
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
	public static class MonthContext extends ParserRuleContext {
		public TerminalNode MONTH() { return getToken(ProposalParser.MONTH, 0); }
		public MonthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_month; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitMonth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MonthContext month() throws RecognitionException {
		MonthContext _localctx = new MonthContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_month);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(MONTH);
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
	public static class YearContext extends ParserRuleContext {
		public TerminalNode DIGITWITHOUT0() { return getToken(ProposalParser.DIGITWITHOUT0, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(ProposalParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(ProposalParser.DIGIT, i);
		}
		public YearContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_year; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitYear(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YearContext year() throws RecognitionException {
		YearContext _localctx = new YearContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_year);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(DIGITWITHOUT0);
			setState(203);
			match(DIGIT);
			setState(204);
			match(DIGIT);
			setState(205);
			match(DIGIT);
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
	public static class D1Context extends ParserRuleContext {
		public TerminalNode PROPOSTA_PT() { return getToken(ProposalParser.PROPOSTA_PT, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public D1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitD1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final D1Context d1() throws RecognitionException {
		D1Context _localctx = new D1Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_d1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(PROPOSTA_PT);
			setState(208);
			match(BREAKLINE);
			setState(209);
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
	public static class D2Context extends ParserRuleContext {
		public TerminalNode PROPOSTA_EN() { return getToken(ProposalParser.PROPOSTA_EN, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public D2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitD2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final D2Context d2() throws RecognitionException {
		D2Context _localctx = new D2Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_d2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(PROPOSTA_EN);
			setState(212);
			match(BREAKLINE);
			setState(213);
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
	public static class E1Context extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(ProposalParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ProposalParser.TEXT, i);
		}
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public E1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_e1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitE1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final E1Context e1() throws RecognitionException {
		E1Context _localctx = new E1Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_e1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(TEXT);
			setState(216);
			match(BREAKLINE);
			setState(217);
			match(BREAKLINE);
			setState(218);
			match(TEXT);
			setState(219);
			match(BREAKLINE);
			setState(220);
			match(BREAKLINE);
			setState(221);
			match(TEXT);
			setState(222);
			match(BREAKLINE);
			setState(223);
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
	public static class E2Context extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(ProposalParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ProposalParser.TEXT, i);
		}
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public E2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_e2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitE2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final E2Context e2() throws RecognitionException {
		E2Context _localctx = new E2Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_e2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(TEXT);
			setState(226);
			match(BREAKLINE);
			setState(227);
			match(TEXT);
			setState(228);
			match(BREAKLINE);
			setState(229);
			match(BREAKLINE);
			setState(230);
			match(TEXT);
			setState(231);
			match(BREAKLINE);
			setState(232);
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
	public static class E3Context extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(ProposalParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ProposalParser.TEXT, i);
		}
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public E3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_e3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitE3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final E3Context e3() throws RecognitionException {
		E3Context _localctx = new E3Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_e3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(TEXT);
			setState(235);
			match(BREAKLINE);
			setState(236);
			match(TEXT);
			setState(237);
			match(BREAKLINE);
			setState(238);
			match(BREAKLINE);
			setState(239);
			match(TEXT);
			setState(240);
			match(BREAKLINE);
			setState(241);
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
	public static class F1Context extends ParserRuleContext {
		public TerminalNode ESTANDO_CERTOS_PT() { return getToken(ProposalParser.ESTANDO_CERTOS_PT, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public F1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitF1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final F1Context f1() throws RecognitionException {
		F1Context _localctx = new F1Context(_ctx, getState());
		enterRule(_localctx, 44, RULE_f1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(ESTANDO_CERTOS_PT);
			setState(244);
			match(BREAKLINE);
			setState(245);
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
	public static class F2Context extends ParserRuleContext {
		public TerminalNode BEING_CERTAIN_EN() { return getToken(ProposalParser.BEING_CERTAIN_EN, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public F2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitF2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final F2Context f2() throws RecognitionException {
		F2Context _localctx = new F2Context(_ctx, getState());
		enterRule(_localctx, 46, RULE_f2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(BEING_CERTAIN_EN);
			setState(248);
			match(BREAKLINE);
			setState(249);
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
	public static class F3Context extends ParserRuleContext {
		public TerminalNode LOOKING_FORWARD_EN() { return getToken(ProposalParser.LOOKING_FORWARD_EN, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public F3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitF3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final F3Context f3() throws RecognitionException {
		F3Context _localctx = new F3Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_f3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(LOOKING_FORWARD_EN);
			setState(252);
			match(BREAKLINE);
			setState(253);
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
	public static class G1Context extends ParserRuleContext {
		public TerminalNode SUBSCRIBE_PT() { return getToken(ProposalParser.SUBSCRIBE_PT, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode BEST_REGARDS() { return getToken(ProposalParser.BEST_REGARDS, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode CRM_MANAGER() { return getToken(ProposalParser.CRM_MANAGER, 0); }
		public G1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitG1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G1Context g1() throws RecognitionException {
		G1Context _localctx = new G1Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_g1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(SUBSCRIBE_PT);
			setState(256);
			match(BREAKLINE);
			setState(257);
			match(BREAKLINE);
			setState(258);
			match(BEST_REGARDS);
			setState(259);
			match(BREAKLINE);
			setState(260);
			match(BREAKLINE);
			setState(261);
			name();
			setState(262);
			match(BREAKLINE);
			setState(263);
			match(CRM_MANAGER);
			setState(264);
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
	public static class G2Context extends ParserRuleContext {
		public TerminalNode SUBSCRIBE_EN() { return getToken(ProposalParser.SUBSCRIBE_EN, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode BEST_REGARDS() { return getToken(ProposalParser.BEST_REGARDS, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode CRM_MANAGER() { return getToken(ProposalParser.CRM_MANAGER, 0); }
		public G2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitG2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G2Context g2() throws RecognitionException {
		G2Context _localctx = new G2Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_g2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(SUBSCRIBE_EN);
			setState(267);
			match(BREAKLINE);
			setState(268);
			match(BREAKLINE);
			setState(269);
			match(BEST_REGARDS);
			setState(270);
			match(BREAKLINE);
			setState(271);
			match(BREAKLINE);
			setState(272);
			name();
			setState(273);
			match(BREAKLINE);
			setState(274);
			match(CRM_MANAGER);
			setState(275);
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
	public static class G3Context extends ParserRuleContext {
		public TerminalNode BEST_REGARDS() { return getToken(ProposalParser.BEST_REGARDS, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode CRM_MANAGER() { return getToken(ProposalParser.CRM_MANAGER, 0); }
		public G3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitG3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G3Context g3() throws RecognitionException {
		G3Context _localctx = new G3Context(_ctx, getState());
		enterRule(_localctx, 54, RULE_g3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(BEST_REGARDS);
			setState(278);
			match(BREAKLINE);
			setState(279);
			match(BREAKLINE);
			setState(280);
			name();
			setState(281);
			match(BREAKLINE);
			setState(282);
			match(CRM_MANAGER);
			setState(283);
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
	public static class HContext extends ParserRuleContext {
		public TerminalNode PAGE_BREAK() { return getToken(ProposalParser.PAGE_BREAK, 0); }
		public HContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_h; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitH(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HContext h() throws RecognitionException {
		HContext _localctx = new HContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_h);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(PAGE_BREAK);
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
	public static class I1Context extends ParserRuleContext {
		public TerminalNode ANEXO_PT() { return getToken(ProposalParser.ANEXO_PT, 0); }
		public ProposalnumberContext proposalnumber() {
			return getRuleContext(ProposalnumberContext.class,0);
		}
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public I1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_i1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitI1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final I1Context i1() throws RecognitionException {
		I1Context _localctx = new I1Context(_ctx, getState());
		enterRule(_localctx, 58, RULE_i1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(ANEXO_PT);
			setState(288);
			proposalnumber();
			setState(289);
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
	public static class I2Context extends ParserRuleContext {
		public TerminalNode ANEXO_EN() { return getToken(ProposalParser.ANEXO_EN, 0); }
		public ProposalnumberContext proposalnumber() {
			return getRuleContext(ProposalnumberContext.class,0);
		}
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public I2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_i2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitI2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final I2Context i2() throws RecognitionException {
		I2Context _localctx = new I2Context(_ctx, getState());
		enterRule(_localctx, 60, RULE_i2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(ANEXO_EN);
			setState(292);
			proposalnumber();
			setState(293);
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
	public static class J1Context extends ParserRuleContext {
		public TerminalNode LOCAL_PT() { return getToken(ProposalParser.LOCAL_PT, 0); }
		public TerminalNode TEXT() { return getToken(ProposalParser.TEXT, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode DATA_PT() { return getToken(ProposalParser.DATA_PT, 0); }
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public TerminalNode HORA_PT() { return getToken(ProposalParser.HORA_PT, 0); }
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public TerminalNode DURACAO_PT() { return getToken(ProposalParser.DURACAO_PT, 0); }
		public DurationContext duration() {
			return getRuleContext(DurationContext.class,0);
		}
		public TerminalNode WORD_MINUTES() { return getToken(ProposalParser.WORD_MINUTES, 0); }
		public J1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_j1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitJ1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final J1Context j1() throws RecognitionException {
		J1Context _localctx = new J1Context(_ctx, getState());
		enterRule(_localctx, 62, RULE_j1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(LOCAL_PT);
			setState(296);
			match(TEXT);
			setState(297);
			match(BREAKLINE);
			setState(298);
			match(DATA_PT);
			setState(299);
			date();
			setState(300);
			match(BREAKLINE);
			setState(301);
			match(HORA_PT);
			setState(302);
			time();
			setState(303);
			match(BREAKLINE);
			setState(304);
			match(DURACAO_PT);
			setState(305);
			duration();
			setState(306);
			match(WORD_MINUTES);
			setState(307);
			match(BREAKLINE);
			setState(308);
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
	public static class J2Context extends ParserRuleContext {
		public TerminalNode LOCATION_EN() { return getToken(ProposalParser.LOCATION_EN, 0); }
		public TerminalNode TEXT() { return getToken(ProposalParser.TEXT, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode DATE_EN() { return getToken(ProposalParser.DATE_EN, 0); }
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public TerminalNode TIME_EN() { return getToken(ProposalParser.TIME_EN, 0); }
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public TerminalNode DURATION_EN() { return getToken(ProposalParser.DURATION_EN, 0); }
		public DurationContext duration() {
			return getRuleContext(DurationContext.class,0);
		}
		public TerminalNode WORD_MINUTES() { return getToken(ProposalParser.WORD_MINUTES, 0); }
		public J2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_j2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitJ2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final J2Context j2() throws RecognitionException {
		J2Context _localctx = new J2Context(_ctx, getState());
		enterRule(_localctx, 64, RULE_j2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(LOCATION_EN);
			setState(311);
			match(TEXT);
			setState(312);
			match(BREAKLINE);
			setState(313);
			match(DATE_EN);
			setState(314);
			date();
			setState(315);
			match(BREAKLINE);
			setState(316);
			match(TIME_EN);
			setState(317);
			time();
			setState(318);
			match(BREAKLINE);
			setState(319);
			match(DURATION_EN);
			setState(320);
			duration();
			setState(321);
			match(WORD_MINUTES);
			setState(322);
			match(BREAKLINE);
			setState(323);
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
	public static class TimeContext extends ParserRuleContext {
		public HourContext hour() {
			return getRuleContext(HourContext.class,0);
		}
		public MinuteContext minute() {
			return getRuleContext(MinuteContext.class,0);
		}
		public TimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimeContext time() throws RecognitionException {
		TimeContext _localctx = new TimeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			hour();
			setState(326);
			minute();
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
	public static class HourContext extends ParserRuleContext {
		public TerminalNode HOUR() { return getToken(ProposalParser.HOUR, 0); }
		public HourContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hour; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitHour(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HourContext hour() throws RecognitionException {
		HourContext _localctx = new HourContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_hour);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(HOUR);
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
	public static class MinuteContext extends ParserRuleContext {
		public TerminalNode MINUTE() { return getToken(ProposalParser.MINUTE, 0); }
		public MinuteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitMinute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MinuteContext minute() throws RecognitionException {
		MinuteContext _localctx = new MinuteContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_minute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(MINUTE);
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
	public static class DurationContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(ProposalParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(ProposalParser.DIGIT, i);
		}
		public DurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_duration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitDuration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DurationContext duration() throws RecognitionException {
		DurationContext _localctx = new DurationContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_duration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(332);
				match(DIGIT);
				}
				}
				setState(335); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
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
	public static class K1Context extends ParserRuleContext {
		public TerminalNode LISTA_DRONES_PT() { return getToken(ProposalParser.LISTA_DRONES_PT, 0); }
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public K1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_k1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitK1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final K1Context k1() throws RecognitionException {
		K1Context _localctx = new K1Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_k1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			match(LISTA_DRONES_PT);
			setState(338);
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
	public static class K2Context extends ParserRuleContext {
		public TerminalNode LISTA_DRONES_EN() { return getToken(ProposalParser.LISTA_DRONES_EN, 0); }
		public TerminalNode BREAKLINE() { return getToken(ProposalParser.BREAKLINE, 0); }
		public K2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_k2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitK2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final K2Context k2() throws RecognitionException {
		K2Context _localctx = new K2Context(_ctx, getState());
		enterRule(_localctx, 76, RULE_k2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			match(LISTA_DRONES_EN);
			setState(341);
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
	public static class L1Context extends ParserRuleContext {
		public List<M1Context> m1() {
			return getRuleContexts(M1Context.class);
		}
		public M1Context m1(int i) {
			return getRuleContext(M1Context.class,i);
		}
		public L1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_l1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitL1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final L1Context l1() throws RecognitionException {
		L1Context _localctx = new L1Context(_ctx, getState());
		enterRule(_localctx, 78, RULE_l1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(343);
				m1();
				}
				}
				setState(346); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==M1 );
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
	public static class L2Context extends ParserRuleContext {
		public List<M2Context> m2() {
			return getRuleContexts(M2Context.class);
		}
		public M2Context m2(int i) {
			return getRuleContext(M2Context.class,i);
		}
		public L2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_l2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitL2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final L2Context l2() throws RecognitionException {
		L2Context _localctx = new L2Context(_ctx, getState());
		enterRule(_localctx, 80, RULE_l2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(348);
				m2();
				}
				}
				setState(351); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==M2 );
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
	public static class M1Context extends ParserRuleContext {
		public TerminalNode M1() { return getToken(ProposalParser.M1, 0); }
		public M1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_m1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitM1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final M1Context m1() throws RecognitionException {
		M1Context _localctx = new M1Context(_ctx, getState());
		enterRule(_localctx, 82, RULE_m1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(M1);
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
	public static class M2Context extends ParserRuleContext {
		public TerminalNode M2() { return getToken(ProposalParser.M2, 0); }
		public M2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_m2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitM2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final M2Context m2() throws RecognitionException {
		M2Context _localctx = new M2Context(_ctx, getState());
		enterRule(_localctx, 84, RULE_m2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(M2);
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
	public static class N1Context extends ParserRuleContext {
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode LISTA_FIGURAS_PT() { return getToken(ProposalParser.LISTA_FIGURAS_PT, 0); }
		public N1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_n1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitN1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final N1Context n1() throws RecognitionException {
		N1Context _localctx = new N1Context(_ctx, getState());
		enterRule(_localctx, 86, RULE_n1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(BREAKLINE);
			setState(358);
			match(LISTA_FIGURAS_PT);
			setState(359);
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
	public static class N2Context extends ParserRuleContext {
		public List<TerminalNode> BREAKLINE() { return getTokens(ProposalParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ProposalParser.BREAKLINE, i);
		}
		public TerminalNode LISTA_FIGURAS_EN() { return getToken(ProposalParser.LISTA_FIGURAS_EN, 0); }
		public N2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_n2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitN2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final N2Context n2() throws RecognitionException {
		N2Context _localctx = new N2Context(_ctx, getState());
		enterRule(_localctx, 88, RULE_n2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(BREAKLINE);
			setState(362);
			match(LISTA_FIGURAS_EN);
			setState(363);
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
	public static class O1Context extends ParserRuleContext {
		public List<P1Context> p1() {
			return getRuleContexts(P1Context.class);
		}
		public P1Context p1(int i) {
			return getRuleContext(P1Context.class,i);
		}
		public O1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_o1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitO1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final O1Context o1() throws RecognitionException {
		O1Context _localctx = new O1Context(_ctx, getState());
		enterRule(_localctx, 90, RULE_o1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==P1) {
				{
				{
				setState(365);
				p1();
				}
				}
				setState(370);
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
	public static class O2Context extends ParserRuleContext {
		public List<P2Context> p2() {
			return getRuleContexts(P2Context.class);
		}
		public P2Context p2(int i) {
			return getRuleContext(P2Context.class,i);
		}
		public O2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_o2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitO2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final O2Context o2() throws RecognitionException {
		O2Context _localctx = new O2Context(_ctx, getState());
		enterRule(_localctx, 92, RULE_o2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==P2) {
				{
				{
				setState(371);
				p2();
				}
				}
				setState(376);
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
	public static class P1Context extends ParserRuleContext {
		public TerminalNode P1() { return getToken(ProposalParser.P1, 0); }
		public P1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitP1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P1Context p1() throws RecognitionException {
		P1Context _localctx = new P1Context(_ctx, getState());
		enterRule(_localctx, 94, RULE_p1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			match(P1);
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
	public static class P2Context extends ParserRuleContext {
		public TerminalNode P2() { return getToken(ProposalParser.P2, 0); }
		public P2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProposalParserVisitor ) return ((ProposalParserVisitor<? extends T>)visitor).visitP2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P2Context p2() throws RecognitionException {
		P2Context _localctx = new P2Context(_ctx, getState());
		enterRule(_localctx, 96, RULE_p2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(P2);
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
		"\u0004\u00013\u017e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\u0090\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0004\b\u00ac"+
		"\b\b\u000b\b\f\b\u00ad\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0004\f\u00bd\b\f\u000b\f\f\f\u00be\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"#\u0001#\u0001$\u0004$\u014e\b$\u000b$\f$\u014f\u0001%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001\'\u0004\'\u0159\b\'\u000b\'\f\'\u015a\u0001(\u0004"+
		"(\u015e\b(\u000b(\f(\u015f\u0001)\u0001)\u0001*\u0001*\u0001+\u0001+\u0001"+
		"+\u0001+\u0001,\u0001,\u0001,\u0001,\u0001-\u0005-\u016f\b-\n-\f-\u0172"+
		"\t-\u0001.\u0005.\u0175\b.\n.\f.\u0178\t.\u0001/\u0001/\u00010\u00010"+
		"\u00010\u0000\u00001\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`\u0000"+
		"\u0000\u0155\u0000\u008f\u0001\u0000\u0000\u0000\u0002\u0091\u0001\u0000"+
		"\u0000\u0000\u0004\u0094\u0001\u0000\u0000\u0000\u0006\u0097\u0001\u0000"+
		"\u0000\u0000\b\u009c\u0001\u0000\u0000\u0000\n\u00a4\u0001\u0000\u0000"+
		"\u0000\f\u00a6\u0001\u0000\u0000\u0000\u000e\u00a8\u0001\u0000\u0000\u0000"+
		"\u0010\u00ab\u0001\u0000\u0000\u0000\u0012\u00af\u0001\u0000\u0000\u0000"+
		"\u0014\u00b3\u0001\u0000\u0000\u0000\u0016\u00b7\u0001\u0000\u0000\u0000"+
		"\u0018\u00bc\u0001\u0000\u0000\u0000\u001a\u00c0\u0001\u0000\u0000\u0000"+
		"\u001c\u00c6\u0001\u0000\u0000\u0000\u001e\u00c8\u0001\u0000\u0000\u0000"+
		" \u00ca\u0001\u0000\u0000\u0000\"\u00cf\u0001\u0000\u0000\u0000$\u00d3"+
		"\u0001\u0000\u0000\u0000&\u00d7\u0001\u0000\u0000\u0000(\u00e1\u0001\u0000"+
		"\u0000\u0000*\u00ea\u0001\u0000\u0000\u0000,\u00f3\u0001\u0000\u0000\u0000"+
		".\u00f7\u0001\u0000\u0000\u00000\u00fb\u0001\u0000\u0000\u00002\u00ff"+
		"\u0001\u0000\u0000\u00004\u010a\u0001\u0000\u0000\u00006\u0115\u0001\u0000"+
		"\u0000\u00008\u011d\u0001\u0000\u0000\u0000:\u011f\u0001\u0000\u0000\u0000"+
		"<\u0123\u0001\u0000\u0000\u0000>\u0127\u0001\u0000\u0000\u0000@\u0136"+
		"\u0001\u0000\u0000\u0000B\u0145\u0001\u0000\u0000\u0000D\u0148\u0001\u0000"+
		"\u0000\u0000F\u014a\u0001\u0000\u0000\u0000H\u014d\u0001\u0000\u0000\u0000"+
		"J\u0151\u0001\u0000\u0000\u0000L\u0154\u0001\u0000\u0000\u0000N\u0158"+
		"\u0001\u0000\u0000\u0000P\u015d\u0001\u0000\u0000\u0000R\u0161\u0001\u0000"+
		"\u0000\u0000T\u0163\u0001\u0000\u0000\u0000V\u0165\u0001\u0000\u0000\u0000"+
		"X\u0169\u0001\u0000\u0000\u0000Z\u0170\u0001\u0000\u0000\u0000\\\u0176"+
		"\u0001\u0000\u0000\u0000^\u0179\u0001\u0000\u0000\u0000`\u017b\u0001\u0000"+
		"\u0000\u0000bc\u0003\u0002\u0001\u0000cd\u0003\b\u0004\u0000de\u0003\u0012"+
		"\t\u0000ef\u0003\"\u0011\u0000fg\u0003&\u0013\u0000gh\u0003,\u0016\u0000"+
		"hi\u00032\u0019\u0000ij\u00038\u001c\u0000jk\u0003:\u001d\u0000kl\u0003"+
		">\u001f\u0000lm\u0003J%\u0000mn\u0003N\'\u0000no\u0003V+\u0000op\u0003"+
		"Z-\u0000p\u0090\u0001\u0000\u0000\u0000qr\u0003\u0004\u0002\u0000rs\u0003"+
		"\b\u0004\u0000st\u0003\u0014\n\u0000tu\u0003$\u0012\u0000uv\u0003(\u0014"+
		"\u0000vw\u0003.\u0017\u0000wx\u00034\u001a\u0000xy\u00038\u001c\u0000"+
		"yz\u0003<\u001e\u0000z{\u0003@ \u0000{|\u0003L&\u0000|}\u0003P(\u0000"+
		"}~\u0003X,\u0000~\u007f\u0003\\.\u0000\u007f\u0090\u0001\u0000\u0000\u0000"+
		"\u0080\u0081\u0003\u0006\u0003\u0000\u0081\u0082\u0003\b\u0004\u0000\u0082"+
		"\u0083\u0003\u0014\n\u0000\u0083\u0084\u0003$\u0012\u0000\u0084\u0085"+
		"\u0003*\u0015\u0000\u0085\u0086\u00030\u0018\u0000\u0086\u0087\u00036"+
		"\u001b\u0000\u0087\u0088\u00038\u001c\u0000\u0088\u0089\u0003<\u001e\u0000"+
		"\u0089\u008a\u0003@ \u0000\u008a\u008b\u0003L&\u0000\u008b\u008c\u0003"+
		"P(\u0000\u008c\u008d\u0003X,\u0000\u008d\u008e\u0003\\.\u0000\u008e\u0090"+
		"\u0001\u0000\u0000\u0000\u008fb\u0001\u0000\u0000\u0000\u008fq\u0001\u0000"+
		"\u0000\u0000\u008f\u0080\u0001\u0000\u0000\u0000\u0090\u0001\u0001\u0000"+
		"\u0000\u0000\u0091\u0092\u0005\u0013\u0000\u0000\u0092\u0093\u0005\u0001"+
		"\u0000\u0000\u0093\u0003\u0001\u0000\u0000\u0000\u0094\u0095\u0005\u0014"+
		"\u0000\u0000\u0095\u0096\u0005\u0001\u0000\u0000\u0096\u0005\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0005\u0015\u0000\u0000\u0098\u0099\u0005\u0001"+
		"\u0000\u0000\u0099\u009a\u0003\f\u0006\u0000\u009a\u009b\u0005\u0001\u0000"+
		"\u0000\u009b\u0007\u0001\u0000\u0000\u0000\u009c\u009d\u0003\n\u0005\u0000"+
		"\u009d\u009e\u0005\u0001\u0000\u0000\u009e\u009f\u0003\u000e\u0007\u0000"+
		"\u009f\u00a0\u0005\u0001\u0000\u0000\u00a0\u00a1\u0003\u0010\b\u0000\u00a1"+
		"\u00a2\u0005\u0001\u0000\u0000\u00a2\u00a3\u0005\u0001\u0000\u0000\u00a3"+
		"\t\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\t\u0000\u0000\u00a5\u000b"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005\t\u0000\u0000\u00a7\r\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0005\b\u0000\u0000\u00a9\u000f\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ac\u0005\u0004\u0000\u0000\u00ab\u00aa\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u0011\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0005\u0016\u0000\u0000\u00b0\u00b1\u0003\u0016"+
		"\u000b\u0000\u00b1\u00b2\u0005\u0001\u0000\u0000\u00b2\u0013\u0001\u0000"+
		"\u0000\u0000\u00b3\u00b4\u0005\u0017\u0000\u0000\u00b4\u00b5\u0003\u0016"+
		"\u000b\u0000\u00b5\u00b6\u0005\u0001\u0000\u0000\u00b6\u0015\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b8\u0003\u0018\f\u0000\u00b8\u00b9\u0005\u0002\u0000"+
		"\u0000\u00b9\u00ba\u0003\u001a\r\u0000\u00ba\u0017\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bd\u0005\u0004\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u0019\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c1\u0003\u001c\u000e\u0000\u00c1\u00c2\u0005\u0002\u0000\u0000"+
		"\u00c2\u00c3\u0003\u001e\u000f\u0000\u00c3\u00c4\u0005\u0002\u0000\u0000"+
		"\u00c4\u00c5\u0003 \u0010\u0000\u00c5\u001b\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c7\u0005\r\u0000\u0000\u00c7\u001d\u0001\u0000\u0000\u0000\u00c8\u00c9"+
		"\u0005\u000e\u0000\u0000\u00c9\u001f\u0001\u0000\u0000\u0000\u00ca\u00cb"+
		"\u0005\u0005\u0000\u0000\u00cb\u00cc\u0005\u0004\u0000\u0000\u00cc\u00cd"+
		"\u0005\u0004\u0000\u0000\u00cd\u00ce\u0005\u0004\u0000\u0000\u00ce!\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d0\u0005\u0018\u0000\u0000\u00d0\u00d1\u0005"+
		"\u0001\u0000\u0000\u00d1\u00d2\u0005\u0001\u0000\u0000\u00d2#\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0005\u0019\u0000\u0000\u00d4\u00d5\u0005\u0001"+
		"\u0000\u0000\u00d5\u00d6\u0005\u0001\u0000\u0000\u00d6%\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d8\u0005\b\u0000\u0000\u00d8\u00d9\u0005\u0001\u0000\u0000"+
		"\u00d9\u00da\u0005\u0001\u0000\u0000\u00da\u00db\u0005\b\u0000\u0000\u00db"+
		"\u00dc\u0005\u0001\u0000\u0000\u00dc\u00dd\u0005\u0001\u0000\u0000\u00dd"+
		"\u00de\u0005\b\u0000\u0000\u00de\u00df\u0005\u0001\u0000\u0000\u00df\u00e0"+
		"\u0005\u0001\u0000\u0000\u00e0\'\u0001\u0000\u0000\u0000\u00e1\u00e2\u0005"+
		"\b\u0000\u0000\u00e2\u00e3\u0005\u0001\u0000\u0000\u00e3\u00e4\u0005\b"+
		"\u0000\u0000\u00e4\u00e5\u0005\u0001\u0000\u0000\u00e5\u00e6\u0005\u0001"+
		"\u0000\u0000\u00e6\u00e7\u0005\b\u0000\u0000\u00e7\u00e8\u0005\u0001\u0000"+
		"\u0000\u00e8\u00e9\u0005\u0001\u0000\u0000\u00e9)\u0001\u0000\u0000\u0000"+
		"\u00ea\u00eb\u0005\b\u0000\u0000\u00eb\u00ec\u0005\u0001\u0000\u0000\u00ec"+
		"\u00ed\u0005\b\u0000\u0000\u00ed\u00ee\u0005\u0001\u0000\u0000\u00ee\u00ef"+
		"\u0005\u0001\u0000\u0000\u00ef\u00f0\u0005\b\u0000\u0000\u00f0\u00f1\u0005"+
		"\u0001\u0000\u0000\u00f1\u00f2\u0005\u0001\u0000\u0000\u00f2+\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f4\u0005!\u0000\u0000\u00f4\u00f5\u0005\u0001\u0000"+
		"\u0000\u00f5\u00f6\u0005\u0001\u0000\u0000\u00f6-\u0001\u0000\u0000\u0000"+
		"\u00f7\u00f8\u0005\"\u0000\u0000\u00f8\u00f9\u0005\u0001\u0000\u0000\u00f9"+
		"\u00fa\u0005\u0001\u0000\u0000\u00fa/\u0001\u0000\u0000\u0000\u00fb\u00fc"+
		"\u0005#\u0000\u0000\u00fc\u00fd\u0005\u0001\u0000\u0000\u00fd\u00fe\u0005"+
		"\u0001\u0000\u0000\u00fe1\u0001\u0000\u0000\u0000\u00ff\u0100\u0005\u001d"+
		"\u0000\u0000\u0100\u0101\u0005\u0001\u0000\u0000\u0101\u0102\u0005\u0001"+
		"\u0000\u0000\u0102\u0103\u0005\u001f\u0000\u0000\u0103\u0104\u0005\u0001"+
		"\u0000\u0000\u0104\u0105\u0005\u0001\u0000\u0000\u0105\u0106\u0003\f\u0006"+
		"\u0000\u0106\u0107\u0005\u0001\u0000\u0000\u0107\u0108\u0005 \u0000\u0000"+
		"\u0108\u0109\u0005\u0001\u0000\u0000\u01093\u0001\u0000\u0000\u0000\u010a"+
		"\u010b\u0005\u001e\u0000\u0000\u010b\u010c\u0005\u0001\u0000\u0000\u010c"+
		"\u010d\u0005\u0001\u0000\u0000\u010d\u010e\u0005\u001f\u0000\u0000\u010e"+
		"\u010f\u0005\u0001\u0000\u0000\u010f\u0110\u0005\u0001\u0000\u0000\u0110"+
		"\u0111\u0003\f\u0006\u0000\u0111\u0112\u0005\u0001\u0000\u0000\u0112\u0113"+
		"\u0005 \u0000\u0000\u0113\u0114\u0005\u0001\u0000\u0000\u01145\u0001\u0000"+
		"\u0000\u0000\u0115\u0116\u0005\u001f\u0000\u0000\u0116\u0117\u0005\u0001"+
		"\u0000\u0000\u0117\u0118\u0005\u0001\u0000\u0000\u0118\u0119\u0003\f\u0006"+
		"\u0000\u0119\u011a\u0005\u0001\u0000\u0000\u011a\u011b\u0005 \u0000\u0000"+
		"\u011b\u011c\u0005\u0001\u0000\u0000\u011c7\u0001\u0000\u0000\u0000\u011d"+
		"\u011e\u00053\u0000\u0000\u011e9\u0001\u0000\u0000\u0000\u011f\u0120\u0005"+
		")\u0000\u0000\u0120\u0121\u0003\u0018\f\u0000\u0121\u0122\u0005\u0001"+
		"\u0000\u0000\u0122;\u0001\u0000\u0000\u0000\u0123\u0124\u0005*\u0000\u0000"+
		"\u0124\u0125\u0003\u0018\f\u0000\u0125\u0126\u0005\u0001\u0000\u0000\u0126"+
		"=\u0001\u0000\u0000\u0000\u0127\u0128\u0005+\u0000\u0000\u0128\u0129\u0005"+
		"\b\u0000\u0000\u0129\u012a\u0005\u0001\u0000\u0000\u012a\u012b\u0005,"+
		"\u0000\u0000\u012b\u012c\u0003\u001a\r\u0000\u012c\u012d\u0005\u0001\u0000"+
		"\u0000\u012d\u012e\u0005-\u0000\u0000\u012e\u012f\u0003B!\u0000\u012f"+
		"\u0130\u0005\u0001\u0000\u0000\u0130\u0131\u0005.\u0000\u0000\u0131\u0132"+
		"\u0003H$\u0000\u0132\u0133\u0005(\u0000\u0000\u0133\u0134\u0005\u0001"+
		"\u0000\u0000\u0134\u0135\u0005\u0001\u0000\u0000\u0135?\u0001\u0000\u0000"+
		"\u0000\u0136\u0137\u0005/\u0000\u0000\u0137\u0138\u0005\b\u0000\u0000"+
		"\u0138\u0139\u0005\u0001\u0000\u0000\u0139\u013a\u00050\u0000\u0000\u013a"+
		"\u013b\u0003\u001a\r\u0000\u013b\u013c\u0005\u0001\u0000\u0000\u013c\u013d"+
		"\u00051\u0000\u0000\u013d\u013e\u0003B!\u0000\u013e\u013f\u0005\u0001"+
		"\u0000\u0000\u013f\u0140\u00052\u0000\u0000\u0140\u0141\u0003H$\u0000"+
		"\u0141\u0142\u0005(\u0000\u0000\u0142\u0143\u0005\u0001\u0000\u0000\u0143"+
		"\u0144\u0005\u0001\u0000\u0000\u0144A\u0001\u0000\u0000\u0000\u0145\u0146"+
		"\u0003D\"\u0000\u0146\u0147\u0003F#\u0000\u0147C\u0001\u0000\u0000\u0000"+
		"\u0148\u0149\u0005\f\u0000\u0000\u0149E\u0001\u0000\u0000\u0000\u014a"+
		"\u014b\u0005\u000b\u0000\u0000\u014bG\u0001\u0000\u0000\u0000\u014c\u014e"+
		"\u0005\u0004\u0000\u0000\u014d\u014c\u0001\u0000\u0000\u0000\u014e\u014f"+
		"\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150"+
		"\u0001\u0000\u0000\u0000\u0150I\u0001\u0000\u0000\u0000\u0151\u0152\u0005"+
		"$\u0000\u0000\u0152\u0153\u0005\u0001\u0000\u0000\u0153K\u0001\u0000\u0000"+
		"\u0000\u0154\u0155\u0005%\u0000\u0000\u0155\u0156\u0005\u0001\u0000\u0000"+
		"\u0156M\u0001\u0000\u0000\u0000\u0157\u0159\u0003R)\u0000\u0158\u0157"+
		"\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u0158"+
		"\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000\u015bO\u0001"+
		"\u0000\u0000\u0000\u015c\u015e\u0003T*\u0000\u015d\u015c\u0001\u0000\u0000"+
		"\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000"+
		"\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160Q\u0001\u0000\u0000\u0000"+
		"\u0161\u0162\u0005\u000f\u0000\u0000\u0162S\u0001\u0000\u0000\u0000\u0163"+
		"\u0164\u0005\u0010\u0000\u0000\u0164U\u0001\u0000\u0000\u0000\u0165\u0166"+
		"\u0005\u0001\u0000\u0000\u0166\u0167\u0005&\u0000\u0000\u0167\u0168\u0005"+
		"\u0001\u0000\u0000\u0168W\u0001\u0000\u0000\u0000\u0169\u016a\u0005\u0001"+
		"\u0000\u0000\u016a\u016b\u0005\'\u0000\u0000\u016b\u016c\u0005\u0001\u0000"+
		"\u0000\u016cY\u0001\u0000\u0000\u0000\u016d\u016f\u0003^/\u0000\u016e"+
		"\u016d\u0001\u0000\u0000\u0000\u016f\u0172\u0001\u0000\u0000\u0000\u0170"+
		"\u016e\u0001\u0000\u0000\u0000\u0170\u0171\u0001\u0000\u0000\u0000\u0171"+
		"[\u0001\u0000\u0000\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0173\u0175"+
		"\u0003`0\u0000\u0174\u0173\u0001\u0000\u0000\u0000\u0175\u0178\u0001\u0000"+
		"\u0000\u0000\u0176\u0174\u0001\u0000\u0000\u0000\u0176\u0177\u0001\u0000"+
		"\u0000\u0000\u0177]\u0001\u0000\u0000\u0000\u0178\u0176\u0001\u0000\u0000"+
		"\u0000\u0179\u017a\u0005\u0011\u0000\u0000\u017a_\u0001\u0000\u0000\u0000"+
		"\u017b\u017c\u0005\u0012\u0000\u0000\u017ca\u0001\u0000\u0000\u0000\b"+
		"\u008f\u00ad\u00be\u014f\u015a\u015f\u0170\u0176";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
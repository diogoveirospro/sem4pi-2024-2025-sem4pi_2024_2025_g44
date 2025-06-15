// Generated from DroneParser.g4 by ANTLR 4.13.2

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
public class DroneParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PI=1, TYPES=2, VARIABLES=3, INSTRUCTIONS=4, PROGRAMMING=5, LANGUAGE=6, 
		VERSION=7, TAKEOFF=8, LAND=9, MOVE=10, MOVEPATH=11, MOVECIRCLE=12, HOOVER=13, 
		LIGHTSON=14, LIGHTSOFF=15, BLINK=16, DOT=17, COMMA=18, SEMICOLON=19, LPAREN=20, 
		RPAREN=21, ASSIGN=22, ADD=23, DASH=24, MUL=25, DIV=26, LT=27, GT=28, TYPE_NAME=29, 
		IDENTIFIER=30, FLOAT=31, INTEGER=32, WS=33, NEWLINE=34;
	public static final int
		RULE_start = 0, RULE_version_header = 1, RULE_type_description = 2, RULE_description = 3, 
		RULE_variable_declaration = 4, RULE_instruction = 5, RULE_expression = 6, 
		RULE_arithmetic = 7, RULE_term = 8, RULE_factor = 9, RULE_point = 10, 
		RULE_vector = 11, RULE_array_of_positions = 12, RULE_number = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "version_header", "type_description", "description", "variable_declaration", 
			"instruction", "expression", "arithmetic", "term", "factor", "point", 
			"vector", "array_of_positions", "number"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PI'", "'Types'", "'Variables'", "'Instructions'", "'programming'", 
			"'language'", "'version'", "'takeOff'", "'land'", "'move'", "'movePath'", 
			"'moveCircle'", "'hoover'", "'lightsOn'", "'lightsOff'", "'blink'", "'.'", 
			"','", "';'", "'('", "')'", "'='", "'+'", "'-'", "'*'", "'/'", "'<'", 
			"'>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PI", "TYPES", "VARIABLES", "INSTRUCTIONS", "PROGRAMMING", "LANGUAGE", 
			"VERSION", "TAKEOFF", "LAND", "MOVE", "MOVEPATH", "MOVECIRCLE", "HOOVER", 
			"LIGHTSON", "LIGHTSOFF", "BLINK", "DOT", "COMMA", "SEMICOLON", "LPAREN", 
			"RPAREN", "ASSIGN", "ADD", "DASH", "MUL", "DIV", "LT", "GT", "TYPE_NAME", 
			"IDENTIFIER", "FLOAT", "INTEGER", "WS", "NEWLINE"
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
	public String getGrammarFileName() { return "DroneParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DroneParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public Version_headerContext version_header() {
			return getRuleContext(Version_headerContext.class,0);
		}
		public TerminalNode TYPES() { return getToken(DroneParser.TYPES, 0); }
		public TerminalNode VARIABLES() { return getToken(DroneParser.VARIABLES, 0); }
		public TerminalNode INSTRUCTIONS() { return getToken(DroneParser.INSTRUCTIONS, 0); }
		public TerminalNode EOF() { return getToken(DroneParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DroneParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DroneParser.NEWLINE, i);
		}
		public List<Type_descriptionContext> type_description() {
			return getRuleContexts(Type_descriptionContext.class);
		}
		public Type_descriptionContext type_description(int i) {
			return getRuleContext(Type_descriptionContext.class,i);
		}
		public List<Variable_declarationContext> variable_declaration() {
			return getRuleContexts(Variable_declarationContext.class);
		}
		public Variable_declarationContext variable_declaration(int i) {
			return getRuleContext(Variable_declarationContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			version_header();
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(29);
				match(NEWLINE);
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(35);
			match(TYPES);
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(36);
				match(NEWLINE);
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				type_description();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TYPE_NAME );
			setState(47);
			match(VARIABLES);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(48);
				match(NEWLINE);
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				variable_declaration();
				}
				}
				setState(57); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LT || _la==TYPE_NAME );
			setState(59);
			match(INSTRUCTIONS);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(60);
				match(NEWLINE);
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(66);
				instruction();
				}
				}
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 130816L) != 0) );
			setState(71);
			match(EOF);
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
	public static class Version_headerContext extends ParserRuleContext {
		public TerminalNode PROGRAMMING() { return getToken(DroneParser.PROGRAMMING, 0); }
		public TerminalNode LANGUAGE() { return getToken(DroneParser.LANGUAGE, 0); }
		public TerminalNode VERSION() { return getToken(DroneParser.VERSION, 0); }
		public TerminalNode FLOAT() { return getToken(DroneParser.FLOAT, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(DroneParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(DroneParser.IDENTIFIER, i);
		}
		public Version_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version_header; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitVersion_header(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Version_headerContext version_header() throws RecognitionException {
		Version_headerContext _localctx = new Version_headerContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_version_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(73);
				match(IDENTIFIER);
				}
				}
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(78);
			match(PROGRAMMING);
			setState(79);
			match(LANGUAGE);
			setState(80);
			match(VERSION);
			setState(81);
			match(FLOAT);
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
	public static class Type_descriptionContext extends ParserRuleContext {
		public TerminalNode TYPE_NAME() { return getToken(DroneParser.TYPE_NAME, 0); }
		public TerminalNode DASH() { return getToken(DroneParser.DASH, 0); }
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(DroneParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DroneParser.NEWLINE, i);
		}
		public Type_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_description; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitType_description(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_descriptionContext type_description() throws RecognitionException {
		Type_descriptionContext _localctx = new Type_descriptionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_description);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(TYPE_NAME);
			setState(84);
			match(DASH);
			setState(85);
			description();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(86);
				match(NEWLINE);
				}
				}
				setState(91);
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
	public static class DescriptionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(DroneParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DroneParser.NEWLINE, i);
		}
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_description);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(92);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(95); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
	public static class Variable_declarationContext extends ParserRuleContext {
		public TerminalNode TYPE_NAME() { return getToken(DroneParser.TYPE_NAME, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DroneParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(DroneParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(DroneParser.SEMICOLON, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DroneParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DroneParser.NEWLINE, i);
		}
		public List<TerminalNode> LT() { return getTokens(DroneParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(DroneParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(DroneParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(DroneParser.GT, i);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitVariable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable_declaration);
		int _la;
		try {
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(TYPE_NAME);
				setState(98);
				match(IDENTIFIER);
				setState(99);
				match(ASSIGN);
				setState(100);
				expression();
				setState(101);
				match(SEMICOLON);
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(102);
					match(NEWLINE);
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(LT);
				setState(109);
				match(TYPE_NAME);
				setState(110);
				match(GT);
				setState(111);
				match(LT);
				setState(112);
				match(IDENTIFIER);
				setState(113);
				match(GT);
				setState(114);
				match(ASSIGN);
				setState(115);
				expression();
				setState(116);
				match(SEMICOLON);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(117);
					match(NEWLINE);
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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
	public static class InstructionContext extends ParserRuleContext {
		public TerminalNode TAKEOFF() { return getToken(DroneParser.TAKEOFF, 0); }
		public TerminalNode LPAREN() { return getToken(DroneParser.LPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DroneParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DroneParser.COMMA, i);
		}
		public TerminalNode RPAREN() { return getToken(DroneParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(DroneParser.SEMICOLON, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(DroneParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DroneParser.NEWLINE, i);
		}
		public TerminalNode LAND() { return getToken(DroneParser.LAND, 0); }
		public TerminalNode MOVE() { return getToken(DroneParser.MOVE, 0); }
		public TerminalNode MOVEPATH() { return getToken(DroneParser.MOVEPATH, 0); }
		public Array_of_positionsContext array_of_positions() {
			return getRuleContext(Array_of_positionsContext.class,0);
		}
		public TerminalNode MOVECIRCLE() { return getToken(DroneParser.MOVECIRCLE, 0); }
		public TerminalNode HOOVER() { return getToken(DroneParser.HOOVER, 0); }
		public TerminalNode LIGHTSON() { return getToken(DroneParser.LIGHTSON, 0); }
		public TerminalNode LIGHTSOFF() { return getToken(DroneParser.LIGHTSOFF, 0); }
		public TerminalNode BLINK() { return getToken(DroneParser.BLINK, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_instruction);
		int _la;
		try {
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				match(TAKEOFF);
				setState(126);
				match(LPAREN);
				setState(127);
				expression();
				setState(128);
				match(COMMA);
				setState(129);
				expression();
				setState(130);
				match(RPAREN);
				setState(131);
				match(SEMICOLON);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(132);
					match(NEWLINE);
					}
					}
					setState(137);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				match(LAND);
				setState(139);
				match(LPAREN);
				setState(140);
				expression();
				setState(141);
				match(RPAREN);
				setState(142);
				match(SEMICOLON);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(143);
					match(NEWLINE);
					}
					}
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(149);
				match(MOVE);
				setState(150);
				match(LPAREN);
				setState(151);
				expression();
				setState(152);
				match(COMMA);
				setState(153);
				expression();
				setState(154);
				match(RPAREN);
				setState(155);
				match(SEMICOLON);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(156);
					match(NEWLINE);
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(162);
				match(MOVE);
				setState(163);
				match(LPAREN);
				setState(164);
				expression();
				setState(165);
				match(COMMA);
				setState(166);
				expression();
				setState(167);
				match(COMMA);
				setState(168);
				expression();
				setState(169);
				match(RPAREN);
				setState(170);
				match(SEMICOLON);
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(171);
					match(NEWLINE);
					}
					}
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(177);
				match(MOVEPATH);
				setState(178);
				match(LPAREN);
				setState(179);
				array_of_positions();
				setState(180);
				match(COMMA);
				setState(181);
				expression();
				setState(182);
				match(RPAREN);
				setState(183);
				match(SEMICOLON);
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(184);
					match(NEWLINE);
					}
					}
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(190);
				match(MOVECIRCLE);
				setState(191);
				match(LPAREN);
				setState(192);
				expression();
				setState(193);
				match(COMMA);
				setState(194);
				expression();
				setState(195);
				match(COMMA);
				setState(196);
				expression();
				setState(197);
				match(RPAREN);
				setState(198);
				match(SEMICOLON);
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(199);
					match(NEWLINE);
					}
					}
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(205);
				match(HOOVER);
				setState(206);
				match(LPAREN);
				setState(207);
				expression();
				setState(208);
				match(RPAREN);
				setState(209);
				match(SEMICOLON);
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(210);
					match(NEWLINE);
					}
					}
					setState(215);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(216);
				match(LIGHTSON);
				setState(217);
				match(LPAREN);
				setState(218);
				match(RPAREN);
				setState(219);
				match(SEMICOLON);
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(220);
					match(NEWLINE);
					}
					}
					setState(225);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(226);
				match(LIGHTSON);
				setState(227);
				match(LPAREN);
				setState(228);
				expression();
				setState(229);
				match(RPAREN);
				setState(230);
				match(SEMICOLON);
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(231);
					match(NEWLINE);
					}
					}
					setState(236);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(237);
				match(LIGHTSOFF);
				setState(238);
				match(LPAREN);
				setState(239);
				match(RPAREN);
				setState(240);
				match(SEMICOLON);
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(241);
					match(NEWLINE);
					}
					}
					setState(246);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(247);
				match(BLINK);
				setState(248);
				match(LPAREN);
				setState(249);
				expression();
				setState(250);
				match(RPAREN);
				setState(251);
				match(SEMICOLON);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(252);
					match(NEWLINE);
					}
					}
					setState(257);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
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
	public static class ExpressionContext extends ParserRuleContext {
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			arithmetic();
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
	public static class ArithmeticContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(DroneParser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(DroneParser.ADD, i);
		}
		public List<TerminalNode> DASH() { return getTokens(DroneParser.DASH); }
		public TerminalNode DASH(int i) {
			return getToken(DroneParser.DASH, i);
		}
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			term();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==DASH) {
				{
				{
				setState(263);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==DASH) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(264);
				term();
				}
				}
				setState(269);
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
	public static class TermContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(DroneParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(DroneParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(DroneParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(DroneParser.DIV, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			factor();
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL || _la==DIV) {
				{
				{
				setState(271);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(272);
				factor();
				}
				}
				setState(277);
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
	public static class FactorContext extends ParserRuleContext {
		public Token id;
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode PI() { return getToken(DroneParser.PI, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DroneParser.IDENTIFIER, 0); }
		public VectorContext vector() {
			return getRuleContext(VectorContext.class,0);
		}
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public Array_of_positionsContext array_of_positions() {
			return getRuleContext(Array_of_positionsContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DroneParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DroneParser.RPAREN, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_factor);
		try {
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				match(PI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(280);
				((FactorContext)_localctx).id = match(IDENTIFIER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(281);
				vector();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(282);
				point();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(283);
				array_of_positions();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(284);
				match(LPAREN);
				setState(285);
				expression();
				setState(286);
				match(RPAREN);
				}
				break;
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
	public static class PointContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DroneParser.LPAREN, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(DroneParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(DroneParser.FLOAT, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DroneParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DroneParser.COMMA, i);
		}
		public TerminalNode RPAREN() { return getToken(DroneParser.RPAREN, 0); }
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(LPAREN);
			setState(291);
			match(FLOAT);
			setState(292);
			match(COMMA);
			setState(293);
			match(FLOAT);
			setState(294);
			match(COMMA);
			setState(295);
			match(FLOAT);
			setState(296);
			match(RPAREN);
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
	public static class VectorContext extends ParserRuleContext {
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public VectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitVector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VectorContext vector() throws RecognitionException {
		VectorContext _localctx = new VectorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_vector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			point();
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
	public static class Array_of_positionsContext extends ParserRuleContext {
		public List<TerminalNode> LPAREN() { return getTokens(DroneParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(DroneParser.LPAREN, i);
		}
		public List<PointContext> point() {
			return getRuleContexts(PointContext.class);
		}
		public PointContext point(int i) {
			return getRuleContext(PointContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(DroneParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(DroneParser.RPAREN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DroneParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DroneParser.COMMA, i);
		}
		public Array_of_positionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_of_positions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitArray_of_positions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_of_positionsContext array_of_positions() throws RecognitionException {
		Array_of_positionsContext _localctx = new Array_of_positionsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_array_of_positions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(LPAREN);
			setState(301);
			match(LPAREN);
			setState(302);
			point();
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(303);
				match(COMMA);
				setState(304);
				point();
				}
				}
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(310);
			match(RPAREN);
			setState(311);
			match(RPAREN);
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
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(DroneParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(DroneParser.INTEGER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DroneParserVisitor ) return ((DroneParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_la = _input.LA(1);
			if ( !(_la==FLOAT || _la==INTEGER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\u0004\u0001\"\u013c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0005\u0000\u001f\b"+
		"\u0000\n\u0000\f\u0000\"\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000&"+
		"\b\u0000\n\u0000\f\u0000)\t\u0000\u0001\u0000\u0004\u0000,\b\u0000\u000b"+
		"\u0000\f\u0000-\u0001\u0000\u0001\u0000\u0005\u00002\b\u0000\n\u0000\f"+
		"\u00005\t\u0000\u0001\u0000\u0004\u00008\b\u0000\u000b\u0000\f\u00009"+
		"\u0001\u0000\u0001\u0000\u0005\u0000>\b\u0000\n\u0000\f\u0000A\t\u0000"+
		"\u0001\u0000\u0004\u0000D\b\u0000\u000b\u0000\f\u0000E\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0004\u0001K\b\u0001\u000b\u0001\f\u0001L\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002X\b\u0002\n\u0002\f\u0002[\t\u0002"+
		"\u0001\u0003\u0004\u0003^\b\u0003\u000b\u0003\f\u0003_\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004h\b"+
		"\u0004\n\u0004\f\u0004k\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004w\b\u0004\n\u0004\f\u0004z\t\u0004\u0003\u0004|\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005\u0086\b\u0005\n\u0005\f\u0005\u0089"+
		"\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005\u0091\b\u0005\n\u0005\f\u0005\u0094\t\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u009e\b\u0005\n\u0005\f\u0005\u00a1\t\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00ad\b\u0005\n"+
		"\u0005\f\u0005\u00b0\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00ba"+
		"\b\u0005\n\u0005\f\u0005\u00bd\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u00c9\b\u0005\n\u0005\f\u0005\u00cc\t\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005\u00d4\b\u0005\n\u0005\f\u0005\u00d7\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00de\b\u0005\n\u0005"+
		"\f\u0005\u00e1\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005\u00e9\b\u0005\n\u0005\f\u0005\u00ec"+
		"\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005\u00f3\b\u0005\n\u0005\f\u0005\u00f6\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00fe\b\u0005"+
		"\n\u0005\f\u0005\u0101\t\u0005\u0003\u0005\u0103\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u010a\b\u0007\n"+
		"\u0007\f\u0007\u010d\t\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u0112\b\b"+
		"\n\b\f\b\u0115\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u0121\b\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f\u0132\b\f\n\f\f\f\u0135\t\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0000\u0000\u000e\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0004"+
		"\u0001\u0000\"\"\u0001\u0000\u0017\u0018\u0001\u0000\u0019\u001a\u0001"+
		"\u0000\u001f \u0158\u0000\u001c\u0001\u0000\u0000\u0000\u0002J\u0001\u0000"+
		"\u0000\u0000\u0004S\u0001\u0000\u0000\u0000\u0006]\u0001\u0000\u0000\u0000"+
		"\b{\u0001\u0000\u0000\u0000\n\u0102\u0001\u0000\u0000\u0000\f\u0104\u0001"+
		"\u0000\u0000\u0000\u000e\u0106\u0001\u0000\u0000\u0000\u0010\u010e\u0001"+
		"\u0000\u0000\u0000\u0012\u0120\u0001\u0000\u0000\u0000\u0014\u0122\u0001"+
		"\u0000\u0000\u0000\u0016\u012a\u0001\u0000\u0000\u0000\u0018\u012c\u0001"+
		"\u0000\u0000\u0000\u001a\u0139\u0001\u0000\u0000\u0000\u001c \u0003\u0002"+
		"\u0001\u0000\u001d\u001f\u0005\"\u0000\u0000\u001e\u001d\u0001\u0000\u0000"+
		"\u0000\u001f\"\u0001\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000"+
		" !\u0001\u0000\u0000\u0000!#\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000"+
		"\u0000#\'\u0005\u0002\u0000\u0000$&\u0005\"\u0000\u0000%$\u0001\u0000"+
		"\u0000\u0000&)\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001"+
		"\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000"+
		"*,\u0003\u0004\u0002\u0000+*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000"+
		"\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000./\u0001\u0000"+
		"\u0000\u0000/3\u0005\u0003\u0000\u000002\u0005\"\u0000\u000010\u0001\u0000"+
		"\u0000\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001"+
		"\u0000\u0000\u000047\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u0000"+
		"68\u0003\b\u0004\u000076\u0001\u0000\u0000\u000089\u0001\u0000\u0000\u0000"+
		"97\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000"+
		"\u0000;?\u0005\u0004\u0000\u0000<>\u0005\"\u0000\u0000=<\u0001\u0000\u0000"+
		"\u0000>A\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000"+
		"\u0000\u0000@C\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BD\u0003"+
		"\n\u0005\u0000CB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EC\u0001"+
		"\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000"+
		"GH\u0005\u0000\u0000\u0001H\u0001\u0001\u0000\u0000\u0000IK\u0005\u001e"+
		"\u0000\u0000JI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LJ\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"NO\u0005\u0005\u0000\u0000OP\u0005\u0006\u0000\u0000PQ\u0005\u0007\u0000"+
		"\u0000QR\u0005\u001f\u0000\u0000R\u0003\u0001\u0000\u0000\u0000ST\u0005"+
		"\u001d\u0000\u0000TU\u0005\u0018\u0000\u0000UY\u0003\u0006\u0003\u0000"+
		"VX\u0005\"\u0000\u0000WV\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000"+
		"YW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\u0005\u0001\u0000"+
		"\u0000\u0000[Y\u0001\u0000\u0000\u0000\\^\b\u0000\u0000\u0000]\\\u0001"+
		"\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000"+
		"_`\u0001\u0000\u0000\u0000`\u0007\u0001\u0000\u0000\u0000ab\u0005\u001d"+
		"\u0000\u0000bc\u0005\u001e\u0000\u0000cd\u0005\u0016\u0000\u0000de\u0003"+
		"\f\u0006\u0000ei\u0005\u0013\u0000\u0000fh\u0005\"\u0000\u0000gf\u0001"+
		"\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000"+
		"ij\u0001\u0000\u0000\u0000j|\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000"+
		"\u0000lm\u0005\u001b\u0000\u0000mn\u0005\u001d\u0000\u0000no\u0005\u001c"+
		"\u0000\u0000op\u0005\u001b\u0000\u0000pq\u0005\u001e\u0000\u0000qr\u0005"+
		"\u001c\u0000\u0000rs\u0005\u0016\u0000\u0000st\u0003\f\u0006\u0000tx\u0005"+
		"\u0013\u0000\u0000uw\u0005\"\u0000\u0000vu\u0001\u0000\u0000\u0000wz\u0001"+
		"\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000"+
		"y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000{a\u0001\u0000\u0000"+
		"\u0000{l\u0001\u0000\u0000\u0000|\t\u0001\u0000\u0000\u0000}~\u0005\b"+
		"\u0000\u0000~\u007f\u0005\u0014\u0000\u0000\u007f\u0080\u0003\f\u0006"+
		"\u0000\u0080\u0081\u0005\u0012\u0000\u0000\u0081\u0082\u0003\f\u0006\u0000"+
		"\u0082\u0083\u0005\u0015\u0000\u0000\u0083\u0087\u0005\u0013\u0000\u0000"+
		"\u0084\u0086\u0005\"\u0000\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0086"+
		"\u0089\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087"+
		"\u0088\u0001\u0000\u0000\u0000\u0088\u0103\u0001\u0000\u0000\u0000\u0089"+
		"\u0087\u0001\u0000\u0000\u0000\u008a\u008b\u0005\t\u0000\u0000\u008b\u008c"+
		"\u0005\u0014\u0000\u0000\u008c\u008d\u0003\f\u0006\u0000\u008d\u008e\u0005"+
		"\u0015\u0000\u0000\u008e\u0092\u0005\u0013\u0000\u0000\u008f\u0091\u0005"+
		"\"\u0000\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0094\u0001\u0000"+
		"\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000"+
		"\u0000\u0000\u0093\u0103\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0005\n\u0000\u0000\u0096\u0097\u0005\u0014\u0000"+
		"\u0000\u0097\u0098\u0003\f\u0006\u0000\u0098\u0099\u0005\u0012\u0000\u0000"+
		"\u0099\u009a\u0003\f\u0006\u0000\u009a\u009b\u0005\u0015\u0000\u0000\u009b"+
		"\u009f\u0005\u0013\u0000\u0000\u009c\u009e\u0005\"\u0000\u0000\u009d\u009c"+
		"\u0001\u0000\u0000\u0000\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u009d"+
		"\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u0103"+
		"\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0005\n\u0000\u0000\u00a3\u00a4\u0005\u0014\u0000\u0000\u00a4\u00a5\u0003"+
		"\f\u0006\u0000\u00a5\u00a6\u0005\u0012\u0000\u0000\u00a6\u00a7\u0003\f"+
		"\u0006\u0000\u00a7\u00a8\u0005\u0012\u0000\u0000\u00a8\u00a9\u0003\f\u0006"+
		"\u0000\u00a9\u00aa\u0005\u0015\u0000\u0000\u00aa\u00ae\u0005\u0013\u0000"+
		"\u0000\u00ab\u00ad\u0005\"\u0000\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ad\u00b0\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u0103\u0001\u0000\u0000\u0000"+
		"\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005\u000b\u0000\u0000"+
		"\u00b2\u00b3\u0005\u0014\u0000\u0000\u00b3\u00b4\u0003\u0018\f\u0000\u00b4"+
		"\u00b5\u0005\u0012\u0000\u0000\u00b5\u00b6\u0003\f\u0006\u0000\u00b6\u00b7"+
		"\u0005\u0015\u0000\u0000\u00b7\u00bb\u0005\u0013\u0000\u0000\u00b8\u00ba"+
		"\u0005\"\u0000\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bd\u0001"+
		"\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001"+
		"\u0000\u0000\u0000\u00bc\u0103\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0005\f\u0000\u0000\u00bf\u00c0\u0005\u0014"+
		"\u0000\u0000\u00c0\u00c1\u0003\f\u0006\u0000\u00c1\u00c2\u0005\u0012\u0000"+
		"\u0000\u00c2\u00c3\u0003\f\u0006\u0000\u00c3\u00c4\u0005\u0012\u0000\u0000"+
		"\u00c4\u00c5\u0003\f\u0006\u0000\u00c5\u00c6\u0005\u0015\u0000\u0000\u00c6"+
		"\u00ca\u0005\u0013\u0000\u0000\u00c7\u00c9\u0005\"\u0000\u0000\u00c8\u00c7"+
		"\u0001\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00c8"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u0103"+
		"\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd\u00ce"+
		"\u0005\r\u0000\u0000\u00ce\u00cf\u0005\u0014\u0000\u0000\u00cf\u00d0\u0003"+
		"\f\u0006\u0000\u00d0\u00d1\u0005\u0015\u0000\u0000\u00d1\u00d5\u0005\u0013"+
		"\u0000\u0000\u00d2\u00d4\u0005\"\u0000\u0000\u00d3\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d7\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u0103\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005\u000e\u0000"+
		"\u0000\u00d9\u00da\u0005\u0014\u0000\u0000\u00da\u00db\u0005\u0015\u0000"+
		"\u0000\u00db\u00df\u0005\u0013\u0000\u0000\u00dc\u00de\u0005\"\u0000\u0000"+
		"\u00dd\u00dc\u0001\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000\u0000"+
		"\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000"+
		"\u00e0\u0103\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e3\u0005\u000e\u0000\u0000\u00e3\u00e4\u0005\u0014\u0000\u0000"+
		"\u00e4\u00e5\u0003\f\u0006\u0000\u00e5\u00e6\u0005\u0015\u0000\u0000\u00e6"+
		"\u00ea\u0005\u0013\u0000\u0000\u00e7\u00e9\u0005\"\u0000\u0000\u00e8\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e9\u00ec\u0001\u0000\u0000\u0000\u00ea\u00e8"+
		"\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb\u0103"+
		"\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ed\u00ee"+
		"\u0005\u000f\u0000\u0000\u00ee\u00ef\u0005\u0014\u0000\u0000\u00ef\u00f0"+
		"\u0005\u0015\u0000\u0000\u00f0\u00f4\u0005\u0013\u0000\u0000\u00f1\u00f3"+
		"\u0005\"\u0000\u0000\u00f2\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001"+
		"\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001"+
		"\u0000\u0000\u0000\u00f5\u0103\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001"+
		"\u0000\u0000\u0000\u00f7\u00f8\u0005\u0010\u0000\u0000\u00f8\u00f9\u0005"+
		"\u0014\u0000\u0000\u00f9\u00fa\u0003\f\u0006\u0000\u00fa\u00fb\u0005\u0015"+
		"\u0000\u0000\u00fb\u00ff\u0005\u0013\u0000\u0000\u00fc\u00fe\u0005\"\u0000"+
		"\u0000\u00fd\u00fc\u0001\u0000\u0000\u0000\u00fe\u0101\u0001\u0000\u0000"+
		"\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000"+
		"\u0000\u0100\u0103\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000"+
		"\u0000\u0102}\u0001\u0000\u0000\u0000\u0102\u008a\u0001\u0000\u0000\u0000"+
		"\u0102\u0095\u0001\u0000\u0000\u0000\u0102\u00a2\u0001\u0000\u0000\u0000"+
		"\u0102\u00b1\u0001\u0000\u0000\u0000\u0102\u00be\u0001\u0000\u0000\u0000"+
		"\u0102\u00cd\u0001\u0000\u0000\u0000\u0102\u00d8\u0001\u0000\u0000\u0000"+
		"\u0102\u00e2\u0001\u0000\u0000\u0000\u0102\u00ed\u0001\u0000\u0000\u0000"+
		"\u0102\u00f7\u0001\u0000\u0000\u0000\u0103\u000b\u0001\u0000\u0000\u0000"+
		"\u0104\u0105\u0003\u000e\u0007\u0000\u0105\r\u0001\u0000\u0000\u0000\u0106"+
		"\u010b\u0003\u0010\b\u0000\u0107\u0108\u0007\u0001\u0000\u0000\u0108\u010a"+
		"\u0003\u0010\b\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u010a\u010d\u0001"+
		"\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010b\u010c\u0001"+
		"\u0000\u0000\u0000\u010c\u000f\u0001\u0000\u0000\u0000\u010d\u010b\u0001"+
		"\u0000\u0000\u0000\u010e\u0113\u0003\u0012\t\u0000\u010f\u0110\u0007\u0002"+
		"\u0000\u0000\u0110\u0112\u0003\u0012\t\u0000\u0111\u010f\u0001\u0000\u0000"+
		"\u0000\u0112\u0115\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000"+
		"\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0011\u0001\u0000\u0000"+
		"\u0000\u0115\u0113\u0001\u0000\u0000\u0000\u0116\u0121\u0003\u001a\r\u0000"+
		"\u0117\u0121\u0005\u0001\u0000\u0000\u0118\u0121\u0005\u001e\u0000\u0000"+
		"\u0119\u0121\u0003\u0016\u000b\u0000\u011a\u0121\u0003\u0014\n\u0000\u011b"+
		"\u0121\u0003\u0018\f\u0000\u011c\u011d\u0005\u0014\u0000\u0000\u011d\u011e"+
		"\u0003\f\u0006\u0000\u011e\u011f\u0005\u0015\u0000\u0000\u011f\u0121\u0001"+
		"\u0000\u0000\u0000\u0120\u0116\u0001\u0000\u0000\u0000\u0120\u0117\u0001"+
		"\u0000\u0000\u0000\u0120\u0118\u0001\u0000\u0000\u0000\u0120\u0119\u0001"+
		"\u0000\u0000\u0000\u0120\u011a\u0001\u0000\u0000\u0000\u0120\u011b\u0001"+
		"\u0000\u0000\u0000\u0120\u011c\u0001\u0000\u0000\u0000\u0121\u0013\u0001"+
		"\u0000\u0000\u0000\u0122\u0123\u0005\u0014\u0000\u0000\u0123\u0124\u0005"+
		"\u001f\u0000\u0000\u0124\u0125\u0005\u0012\u0000\u0000\u0125\u0126\u0005"+
		"\u001f\u0000\u0000\u0126\u0127\u0005\u0012\u0000\u0000\u0127\u0128\u0005"+
		"\u001f\u0000\u0000\u0128\u0129\u0005\u0015\u0000\u0000\u0129\u0015\u0001"+
		"\u0000\u0000\u0000\u012a\u012b\u0003\u0014\n\u0000\u012b\u0017\u0001\u0000"+
		"\u0000\u0000\u012c\u012d\u0005\u0014\u0000\u0000\u012d\u012e\u0005\u0014"+
		"\u0000\u0000\u012e\u0133\u0003\u0014\n\u0000\u012f\u0130\u0005\u0012\u0000"+
		"\u0000\u0130\u0132\u0003\u0014\n\u0000\u0131\u012f\u0001\u0000\u0000\u0000"+
		"\u0132\u0135\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000"+
		"\u0133\u0134\u0001\u0000\u0000\u0000\u0134\u0136\u0001\u0000\u0000\u0000"+
		"\u0135\u0133\u0001\u0000\u0000\u0000\u0136\u0137\u0005\u0015\u0000\u0000"+
		"\u0137\u0138\u0005\u0015\u0000\u0000\u0138\u0019\u0001\u0000\u0000\u0000"+
		"\u0139\u013a\u0007\u0003\u0000\u0000\u013a\u001b\u0001\u0000\u0000\u0000"+
		"\u001d \'-39?ELY_ix{\u0087\u0092\u009f\u00ae\u00bb\u00ca\u00d5\u00df\u00ea"+
		"\u00f4\u00ff\u0102\u010b\u0113\u0120\u0133";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
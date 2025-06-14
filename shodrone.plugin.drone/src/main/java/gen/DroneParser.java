// Generated from DroneParser.g4 by ANTLR 4.7.2

package gen;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DroneParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitStart(this);
		}
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TAKEOFF) | (1L << LAND) | (1L << MOVE) | (1L << MOVEPATH) | (1L << MOVECIRCLE) | (1L << HOOVER) | (1L << LIGHTSON) | (1L << LIGHTSOFF) | (1L << BLINK))) != 0) );
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterVersion_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitVersion_header(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterType_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitType_description(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitDescription(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitVariable_declaration(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitInstruction(this);
		}
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

	public static class ExpressionContext extends ParserRuleContext {
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitExpression(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitArithmetic(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitTerm(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitFactor(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterPoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitPoint(this);
		}
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

	public static class VectorContext extends ParserRuleContext {
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public VectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterVector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitVector(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterArray_of_positions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitArray_of_positions(this);
		}
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(DroneParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(DroneParser.INTEGER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DroneParserListener ) ((DroneParserListener)listener).exitNumber(this);
		}
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u013e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\7\2!\n\2\f\2\16\2$\13"+
		"\2\3\2\3\2\7\2(\n\2\f\2\16\2+\13\2\3\2\6\2.\n\2\r\2\16\2/\3\2\3\2\7\2"+
		"\64\n\2\f\2\16\2\67\13\2\3\2\6\2:\n\2\r\2\16\2;\3\2\3\2\7\2@\n\2\f\2\16"+
		"\2C\13\2\3\2\6\2F\n\2\r\2\16\2G\3\2\3\2\3\3\6\3M\n\3\r\3\16\3N\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\5\6\5`\n\5\r\5"+
		"\16\5a\3\6\3\6\3\6\3\6\3\6\3\6\7\6j\n\6\f\6\16\6m\13\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\7\6y\n\6\f\6\16\6|\13\6\5\6~\n\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\7\7\u0088\n\7\f\7\16\7\u008b\13\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\7\7\u0093\n\7\f\7\16\7\u0096\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\7\7\u00a0\n\7\f\7\16\7\u00a3\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\7\7\u00af\n\7\f\7\16\7\u00b2\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\7\7\u00bc\n\7\f\7\16\7\u00bf\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\7\7\u00cb\n\7\f\7\16\7\u00ce\13\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7"+
		"\u00d6\n\7\f\7\16\7\u00d9\13\7\3\7\3\7\3\7\3\7\3\7\7\7\u00e0\n\7\f\7\16"+
		"\7\u00e3\13\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00eb\n\7\f\7\16\7\u00ee\13"+
		"\7\3\7\3\7\3\7\3\7\3\7\7\7\u00f5\n\7\f\7\16\7\u00f8\13\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\7\7\u0100\n\7\f\7\16\7\u0103\13\7\5\7\u0105\n\7\3\b\3\b\3\t"+
		"\3\t\3\t\7\t\u010c\n\t\f\t\16\t\u010f\13\t\3\n\3\n\3\n\7\n\u0114\n\n\f"+
		"\n\16\n\u0117\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5"+
		"\13\u0123\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\7\16\u0134\n\16\f\16\16\16\u0137\13\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\6\3\2$$\3\2\31"+
		"\32\3\2\33\34\3\2!\"\2\u015a\2\36\3\2\2\2\4L\3\2\2\2\6U\3\2\2\2\b_\3\2"+
		"\2\2\n}\3\2\2\2\f\u0104\3\2\2\2\16\u0106\3\2\2\2\20\u0108\3\2\2\2\22\u0110"+
		"\3\2\2\2\24\u0122\3\2\2\2\26\u0124\3\2\2\2\30\u012c\3\2\2\2\32\u012e\3"+
		"\2\2\2\34\u013b\3\2\2\2\36\"\5\4\3\2\37!\7$\2\2 \37\3\2\2\2!$\3\2\2\2"+
		"\" \3\2\2\2\"#\3\2\2\2#%\3\2\2\2$\"\3\2\2\2%)\7\4\2\2&(\7$\2\2\'&\3\2"+
		"\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*-\3\2\2\2+)\3\2\2\2,.\5\6\4\2-,\3"+
		"\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\65\7\5\2\2\62"+
		"\64\7$\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66"+
		"9\3\2\2\2\67\65\3\2\2\28:\5\n\6\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2"+
		"\2\2<=\3\2\2\2=A\7\6\2\2>@\7$\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2"+
		"\2\2BE\3\2\2\2CA\3\2\2\2DF\5\f\7\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2"+
		"\2\2HI\3\2\2\2IJ\7\2\2\3J\3\3\2\2\2KM\7 \2\2LK\3\2\2\2MN\3\2\2\2NL\3\2"+
		"\2\2NO\3\2\2\2OP\3\2\2\2PQ\7\7\2\2QR\7\b\2\2RS\7\t\2\2ST\7!\2\2T\5\3\2"+
		"\2\2UV\7\37\2\2VW\7\32\2\2W[\5\b\5\2XZ\7$\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3"+
		"\2\2\2[\\\3\2\2\2\\\7\3\2\2\2][\3\2\2\2^`\n\2\2\2_^\3\2\2\2`a\3\2\2\2"+
		"a_\3\2\2\2ab\3\2\2\2b\t\3\2\2\2cd\7\37\2\2de\7 \2\2ef\7\30\2\2fg\5\16"+
		"\b\2gk\7\25\2\2hj\7$\2\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l~\3\2"+
		"\2\2mk\3\2\2\2no\7\35\2\2op\7\37\2\2pq\7\36\2\2qr\7\35\2\2rs\7 \2\2st"+
		"\7\36\2\2tu\7\30\2\2uv\5\16\b\2vz\7\25\2\2wy\7$\2\2xw\3\2\2\2y|\3\2\2"+
		"\2zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|z\3\2\2\2}c\3\2\2\2}n\3\2\2\2~\13\3\2"+
		"\2\2\177\u0080\7\n\2\2\u0080\u0081\7\26\2\2\u0081\u0082\5\16\b\2\u0082"+
		"\u0083\7\24\2\2\u0083\u0084\5\16\b\2\u0084\u0085\7\27\2\2\u0085\u0089"+
		"\7\25\2\2\u0086\u0088\7$\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0105\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008c\u008d\7\13\2\2\u008d\u008e\7\26\2\2\u008e\u008f\5\16\b\2\u008f"+
		"\u0090\7\27\2\2\u0090\u0094\7\25\2\2\u0091\u0093\7$\2\2\u0092\u0091\3"+
		"\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0105\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\7\f\2\2\u0098\u0099\7\26"+
		"\2\2\u0099\u009a\5\16\b\2\u009a\u009b\7\24\2\2\u009b\u009c\5\16\b\2\u009c"+
		"\u009d\7\27\2\2\u009d\u00a1\7\25\2\2\u009e\u00a0\7$\2\2\u009f\u009e\3"+
		"\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u0105\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5\7\f\2\2\u00a5\u00a6\7\26"+
		"\2\2\u00a6\u00a7\5\16\b\2\u00a7\u00a8\7\24\2\2\u00a8\u00a9\5\16\b\2\u00a9"+
		"\u00aa\7\24\2\2\u00aa\u00ab\5\16\b\2\u00ab\u00ac\7\27\2\2\u00ac\u00b0"+
		"\7\25\2\2\u00ad\u00af\7$\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u0105\3\2\2\2\u00b2\u00b0\3\2"+
		"\2\2\u00b3\u00b4\7\r\2\2\u00b4\u00b5\7\26\2\2\u00b5\u00b6\5\32\16\2\u00b6"+
		"\u00b7\7\24\2\2\u00b7\u00b8\5\16\b\2\u00b8\u00b9\7\27\2\2\u00b9\u00bd"+
		"\7\25\2\2\u00ba\u00bc\7$\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd"+
		"\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u0105\3\2\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00c0\u00c1\7\16\2\2\u00c1\u00c2\7\26\2\2\u00c2\u00c3\5\16\b\2\u00c3"+
		"\u00c4\7\24\2\2\u00c4\u00c5\5\16\b\2\u00c5\u00c6\7\24\2\2\u00c6\u00c7"+
		"\5\16\b\2\u00c7\u00c8\7\27\2\2\u00c8\u00cc\7\25\2\2\u00c9\u00cb\7$\2\2"+
		"\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd"+
		"\3\2\2\2\u00cd\u0105\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7\17\2\2"+
		"\u00d0\u00d1\7\26\2\2\u00d1\u00d2\5\16\b\2\u00d2\u00d3\7\27\2\2\u00d3"+
		"\u00d7\7\25\2\2\u00d4\u00d6\7$\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2"+
		"\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u0105\3\2\2\2\u00d9"+
		"\u00d7\3\2\2\2\u00da\u00db\7\20\2\2\u00db\u00dc\7\26\2\2\u00dc\u00dd\7"+
		"\27\2\2\u00dd\u00e1\7\25\2\2\u00de\u00e0\7$\2\2\u00df\u00de\3\2\2\2\u00e0"+
		"\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u0105\3\2"+
		"\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\20\2\2\u00e5\u00e6\7\26\2\2\u00e6"+
		"\u00e7\5\16\b\2\u00e7\u00e8\7\27\2\2\u00e8\u00ec\7\25\2\2\u00e9\u00eb"+
		"\7$\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u0105\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\7\21"+
		"\2\2\u00f0\u00f1\7\26\2\2\u00f1\u00f2\7\27\2\2\u00f2\u00f6\7\25\2\2\u00f3"+
		"\u00f5\7$\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2"+
		"\2\2\u00f6\u00f7\3\2\2\2\u00f7\u0105\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9"+
		"\u00fa\7\22\2\2\u00fa\u00fb\7\26\2\2\u00fb\u00fc\5\16\b\2\u00fc\u00fd"+
		"\7\27\2\2\u00fd\u0101\7\25\2\2\u00fe\u0100\7$\2\2\u00ff\u00fe\3\2\2\2"+
		"\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0105"+
		"\3\2\2\2\u0103\u0101\3\2\2\2\u0104\177\3\2\2\2\u0104\u008c\3\2\2\2\u0104"+
		"\u0097\3\2\2\2\u0104\u00a4\3\2\2\2\u0104\u00b3\3\2\2\2\u0104\u00c0\3\2"+
		"\2\2\u0104\u00cf\3\2\2\2\u0104\u00da\3\2\2\2\u0104\u00e4\3\2\2\2\u0104"+
		"\u00ef\3\2\2\2\u0104\u00f9\3\2\2\2\u0105\r\3\2\2\2\u0106\u0107\5\20\t"+
		"\2\u0107\17\3\2\2\2\u0108\u010d\5\22\n\2\u0109\u010a\t\3\2\2\u010a\u010c"+
		"\5\22\n\2\u010b\u0109\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2"+
		"\u010d\u010e\3\2\2\2\u010e\21\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0115"+
		"\5\24\13\2\u0111\u0112\t\4\2\2\u0112\u0114\5\24\13\2\u0113\u0111\3\2\2"+
		"\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\23"+
		"\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0123\5\34\17\2\u0119\u0123\7\3\2\2"+
		"\u011a\u0123\7 \2\2\u011b\u0123\5\30\r\2\u011c\u0123\5\26\f\2\u011d\u0123"+
		"\5\32\16\2\u011e\u011f\7\26\2\2\u011f\u0120\5\16\b\2\u0120\u0121\7\27"+
		"\2\2\u0121\u0123\3\2\2\2\u0122\u0118\3\2\2\2\u0122\u0119\3\2\2\2\u0122"+
		"\u011a\3\2\2\2\u0122\u011b\3\2\2\2\u0122\u011c\3\2\2\2\u0122\u011d\3\2"+
		"\2\2\u0122\u011e\3\2\2\2\u0123\25\3\2\2\2\u0124\u0125\7\26\2\2\u0125\u0126"+
		"\7!\2\2\u0126\u0127\7\24\2\2\u0127\u0128\7!\2\2\u0128\u0129\7\24\2\2\u0129"+
		"\u012a\7!\2\2\u012a\u012b\7\27\2\2\u012b\27\3\2\2\2\u012c\u012d\5\26\f"+
		"\2\u012d\31\3\2\2\2\u012e\u012f\7\26\2\2\u012f\u0130\7\26\2\2\u0130\u0135"+
		"\5\26\f\2\u0131\u0132\7\24\2\2\u0132\u0134\5\26\f\2\u0133\u0131\3\2\2"+
		"\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0138"+
		"\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u0139\7\27\2\2\u0139\u013a\7\27\2\2"+
		"\u013a\33\3\2\2\2\u013b\u013c\t\5\2\2\u013c\35\3\2\2\2\37\")/\65;AGN["+
		"akz}\u0089\u0094\u00a1\u00b0\u00bd\u00cc\u00d7\u00e1\u00ec\u00f6\u0101"+
		"\u0104\u010d\u0115\u0122\u0135";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from ShowParser.g4 by ANTLR 4.7.2

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
public class ShowParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SHOW=1, FIGURELIST=2, PROPNUMBER=3, FIGURECODE=4, DOT=5, COMMA=6, SEMICOLON=7, 
		LCURLYBRACE=8, RCURLYBRACE=9, DASH=10, ARROW=11, VERSIONNUMBER=12, DIGIT=13, 
		BREAKLINE=14, WS=15;
	public static final int
		RULE_start = 0, RULE_figurelist = 1, RULE_figureitem = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "figurelist", "figureitem"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Show'", "'FigureList'", null, null, "'.'", "','", "';'", "'{'", 
			"'}'", "'-'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SHOW", "FIGURELIST", "PROPNUMBER", "FIGURECODE", "DOT", "COMMA", 
			"SEMICOLON", "LCURLYBRACE", "RCURLYBRACE", "DASH", "ARROW", "VERSIONNUMBER", 
			"DIGIT", "BREAKLINE", "WS"
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
	public String getGrammarFileName() { return "ShowParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ShowParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(ShowParser.SHOW, 0); }
		public TerminalNode PROPNUMBER() { return getToken(ShowParser.PROPNUMBER, 0); }
		public TerminalNode SEMICOLON() { return getToken(ShowParser.SEMICOLON, 0); }
		public TerminalNode BREAKLINE() { return getToken(ShowParser.BREAKLINE, 0); }
		public FigurelistContext figurelist() {
			return getRuleContext(FigurelistContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ShowParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShowParserListener ) ((ShowParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShowParserListener ) ((ShowParserListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ShowParserVisitor ) return ((ShowParserVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6);
			match(SHOW);
			setState(7);
			match(PROPNUMBER);
			setState(8);
			match(SEMICOLON);
			setState(9);
			match(BREAKLINE);
			setState(10);
			figurelist();
			setState(11);
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

	public static class FigurelistContext extends ParserRuleContext {
		public TerminalNode FIGURELIST() { return getToken(ShowParser.FIGURELIST, 0); }
		public TerminalNode LCURLYBRACE() { return getToken(ShowParser.LCURLYBRACE, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(ShowParser.BREAKLINE); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(ShowParser.BREAKLINE, i);
		}
		public List<FigureitemContext> figureitem() {
			return getRuleContexts(FigureitemContext.class);
		}
		public FigureitemContext figureitem(int i) {
			return getRuleContext(FigureitemContext.class,i);
		}
		public TerminalNode RCURLYBRACE() { return getToken(ShowParser.RCURLYBRACE, 0); }
		public FigurelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_figurelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShowParserListener ) ((ShowParserListener)listener).enterFigurelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShowParserListener ) ((ShowParserListener)listener).exitFigurelist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ShowParserVisitor ) return ((ShowParserVisitor<? extends T>)visitor).visitFigurelist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FigurelistContext figurelist() throws RecognitionException {
		FigurelistContext _localctx = new FigurelistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_figurelist);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			match(FIGURELIST);
			setState(14);
			match(LCURLYBRACE);
			setState(15);
			match(BREAKLINE);
			setState(16);
			figureitem();
			setState(21);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(17);
					match(BREAKLINE);
					setState(18);
					figureitem();
					}
					} 
				}
				setState(23);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(24);
			match(BREAKLINE);
			setState(25);
			match(RCURLYBRACE);
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

	public static class FigureitemContext extends ParserRuleContext {
		public TerminalNode FIGURECODE() { return getToken(ShowParser.FIGURECODE, 0); }
		public TerminalNode ARROW() { return getToken(ShowParser.ARROW, 0); }
		public TerminalNode VERSIONNUMBER() { return getToken(ShowParser.VERSIONNUMBER, 0); }
		public TerminalNode SEMICOLON() { return getToken(ShowParser.SEMICOLON, 0); }
		public FigureitemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_figureitem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShowParserListener ) ((ShowParserListener)listener).enterFigureitem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShowParserListener ) ((ShowParserListener)listener).exitFigureitem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ShowParserVisitor ) return ((ShowParserVisitor<? extends T>)visitor).visitFigureitem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FigureitemContext figureitem() throws RecognitionException {
		FigureitemContext _localctx = new FigureitemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_figureitem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(FIGURECODE);
			setState(28);
			match(ARROW);
			setState(29);
			match(VERSIONNUMBER);
			setState(30);
			match(SEMICOLON);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21#\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\26"+
		"\n\3\f\3\16\3\31\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\2\2\5\2\4\6"+
		"\2\2\2 \2\b\3\2\2\2\4\17\3\2\2\2\6\35\3\2\2\2\b\t\7\3\2\2\t\n\7\5\2\2"+
		"\n\13\7\t\2\2\13\f\7\20\2\2\f\r\5\4\3\2\r\16\7\2\2\3\16\3\3\2\2\2\17\20"+
		"\7\4\2\2\20\21\7\n\2\2\21\22\7\20\2\2\22\27\5\6\4\2\23\24\7\20\2\2\24"+
		"\26\5\6\4\2\25\23\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30"+
		"\32\3\2\2\2\31\27\3\2\2\2\32\33\7\20\2\2\33\34\7\13\2\2\34\5\3\2\2\2\35"+
		"\36\7\6\2\2\36\37\7\r\2\2\37 \7\16\2\2 !\7\t\2\2!\7\3\2\2\2\3\27";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
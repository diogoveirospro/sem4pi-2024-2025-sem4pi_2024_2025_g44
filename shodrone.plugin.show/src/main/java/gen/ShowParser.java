// Generated from ShowParser.g4 by ANTLR 4.13.2

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
public class ShowParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
		"\u0004\u0001\u000f!\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u0014\b\u0001\n\u0001\f\u0001"+
		"\u0017\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0000\u0000\u0003\u0000"+
		"\u0002\u0004\u0000\u0000\u001e\u0000\u0006\u0001\u0000\u0000\u0000\u0002"+
		"\r\u0001\u0000\u0000\u0000\u0004\u001b\u0001\u0000\u0000\u0000\u0006\u0007"+
		"\u0005\u0001\u0000\u0000\u0007\b\u0005\u0003\u0000\u0000\b\t\u0005\u0007"+
		"\u0000\u0000\t\n\u0005\u000e\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000"+
		"\u000b\f\u0005\u0000\u0000\u0001\f\u0001\u0001\u0000\u0000\u0000\r\u000e"+
		"\u0005\u0002\u0000\u0000\u000e\u000f\u0005\b\u0000\u0000\u000f\u0010\u0005"+
		"\u000e\u0000\u0000\u0010\u0015\u0003\u0004\u0002\u0000\u0011\u0012\u0005"+
		"\u000e\u0000\u0000\u0012\u0014\u0003\u0004\u0002\u0000\u0013\u0011\u0001"+
		"\u0000\u0000\u0000\u0014\u0017\u0001\u0000\u0000\u0000\u0015\u0013\u0001"+
		"\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0018\u0001"+
		"\u0000\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0018\u0019\u0005"+
		"\u000e\u0000\u0000\u0019\u001a\u0005\t\u0000\u0000\u001a\u0003\u0001\u0000"+
		"\u0000\u0000\u001b\u001c\u0005\u0004\u0000\u0000\u001c\u001d\u0005\u000b"+
		"\u0000\u0000\u001d\u001e\u0005\f\u0000\u0000\u001e\u001f\u0005\u0007\u0000"+
		"\u0000\u001f\u0005\u0001\u0000\u0000\u0000\u0001\u0015";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
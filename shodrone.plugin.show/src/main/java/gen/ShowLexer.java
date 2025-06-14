package gen;
// Generated from ShowLexer.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ShowLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SHOW=1, FIGURELIST=2, PROPNUMBER=3, FIGURECODE=4, DOT=5, COMMA=6, SEMICOLON=7, 
		LCURLYBRACE=8, RCURLYBRACE=9, DASH=10, ARROW=11, VERSIONNUMBER=12, DIGIT=13, 
		BREAKLINE=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SHOW", "FIGURELIST", "PROPNUMBER", "FIGURECODE", "DOT", "COMMA", "SEMICOLON", 
			"LCURLYBRACE", "RCURLYBRACE", "DASH", "ARROW", "VERSIONNUMBER", "DIGIT", 
			"BREAKLINE", "WS"
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


	public ShowLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ShowLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000fj\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0004\u000bY\b\u000b\u000b\u000b\f\u000bZ\u0001\f\u0001\f\u0001"+
		"\r\u0003\r`\b\r\u0001\r\u0001\r\u0001\u000e\u0004\u000ee\b\u000e\u000b"+
		"\u000e\f\u000ef\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u0001\u0000\u0002\u0001\u000009\u0002\u0000\t\t  l\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0003$\u0001\u0000\u0000\u0000"+
		"\u0005/\u0001\u0000\u0000\u0000\u0007:\u0001\u0000\u0000\u0000\tD\u0001"+
		"\u0000\u0000\u0000\u000bF\u0001\u0000\u0000\u0000\rH\u0001\u0000\u0000"+
		"\u0000\u000fJ\u0001\u0000\u0000\u0000\u0011L\u0001\u0000\u0000\u0000\u0013"+
		"N\u0001\u0000\u0000\u0000\u0015P\u0001\u0000\u0000\u0000\u0017S\u0001"+
		"\u0000\u0000\u0000\u0019\\\u0001\u0000\u0000\u0000\u001b_\u0001\u0000"+
		"\u0000\u0000\u001dd\u0001\u0000\u0000\u0000\u001f \u0005S\u0000\u0000"+
		" !\u0005h\u0000\u0000!\"\u0005o\u0000\u0000\"#\u0005w\u0000\u0000#\u0002"+
		"\u0001\u0000\u0000\u0000$%\u0005F\u0000\u0000%&\u0005i\u0000\u0000&\'"+
		"\u0005g\u0000\u0000\'(\u0005u\u0000\u0000()\u0005r\u0000\u0000)*\u0005"+
		"e\u0000\u0000*+\u0005L\u0000\u0000+,\u0005i\u0000\u0000,-\u0005s\u0000"+
		"\u0000-.\u0005t\u0000\u0000.\u0004\u0001\u0000\u0000\u0000/0\u0005P\u0000"+
		"\u000001\u0005R\u0000\u000012\u0005O\u0000\u000023\u0005P\u0000\u0000"+
		"34\u0001\u0000\u0000\u000045\u0003\u0013\t\u000056\u0003\u0019\f\u0000"+
		"67\u0003\u0019\f\u000078\u0003\u0019\f\u000089\u0003\u0019\f\u00009\u0006"+
		"\u0001\u0000\u0000\u0000:;\u0005F\u0000\u0000;<\u0005I\u0000\u0000<=\u0005"+
		"G\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0003\u0013\t\u0000?@\u0003"+
		"\u0019\f\u0000@A\u0003\u0019\f\u0000AB\u0003\u0019\f\u0000BC\u0003\u0019"+
		"\f\u0000C\b\u0001\u0000\u0000\u0000DE\u0005.\u0000\u0000E\n\u0001\u0000"+
		"\u0000\u0000FG\u0005,\u0000\u0000G\f\u0001\u0000\u0000\u0000HI\u0005;"+
		"\u0000\u0000I\u000e\u0001\u0000\u0000\u0000JK\u0005{\u0000\u0000K\u0010"+
		"\u0001\u0000\u0000\u0000LM\u0005}\u0000\u0000M\u0012\u0001\u0000\u0000"+
		"\u0000NO\u0005-\u0000\u0000O\u0014\u0001\u0000\u0000\u0000PQ\u0005-\u0000"+
		"\u0000QR\u0005>\u0000\u0000R\u0016\u0001\u0000\u0000\u0000ST\u0003\u0019"+
		"\f\u0000TU\u0003\t\u0004\u0000UV\u0003\u0019\f\u0000VX\u0003\t\u0004\u0000"+
		"WY\u0003\u0019\f\u0000XW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000"+
		"ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\u0018\u0001\u0000"+
		"\u0000\u0000\\]\u0007\u0000\u0000\u0000]\u001a\u0001\u0000\u0000\u0000"+
		"^`\u0005\r\u0000\u0000_^\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000"+
		"`a\u0001\u0000\u0000\u0000ab\u0005\n\u0000\u0000b\u001c\u0001\u0000\u0000"+
		"\u0000ce\u0007\u0001\u0000\u0000dc\u0001\u0000\u0000\u0000ef\u0001\u0000"+
		"\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0001"+
		"\u0000\u0000\u0000hi\u0006\u000e\u0000\u0000i\u001e\u0001\u0000\u0000"+
		"\u0000\u0004\u0000Z_f\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
package gen;
// Generated from ShowLexer.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ShowLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21l\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\6\r[\n\r\r\r\16\r\\\3\16\3\16\3\17\5\17b\n\17\3\17\3\17\3\20\6\20g\n"+
		"\20\r\20\16\20h\3\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2\4\3\2\62;\4\2\13\13\"\"\2n\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5&\3\2\2"+
		"\2\7\61\3\2\2\2\t<\3\2\2\2\13F\3\2\2\2\rH\3\2\2\2\17J\3\2\2\2\21L\3\2"+
		"\2\2\23N\3\2\2\2\25P\3\2\2\2\27R\3\2\2\2\31U\3\2\2\2\33^\3\2\2\2\35a\3"+
		"\2\2\2\37f\3\2\2\2!\"\7U\2\2\"#\7j\2\2#$\7q\2\2$%\7y\2\2%\4\3\2\2\2&\'"+
		"\7H\2\2\'(\7k\2\2()\7i\2\2)*\7w\2\2*+\7t\2\2+,\7g\2\2,-\7N\2\2-.\7k\2"+
		"\2./\7u\2\2/\60\7v\2\2\60\6\3\2\2\2\61\62\7R\2\2\62\63\7T\2\2\63\64\7"+
		"Q\2\2\64\65\7R\2\2\65\66\3\2\2\2\66\67\5\25\13\2\678\5\33\16\289\5\33"+
		"\16\29:\5\33\16\2:;\5\33\16\2;\b\3\2\2\2<=\7H\2\2=>\7K\2\2>?\7I\2\2?@"+
		"\3\2\2\2@A\5\25\13\2AB\5\33\16\2BC\5\33\16\2CD\5\33\16\2DE\5\33\16\2E"+
		"\n\3\2\2\2FG\7\60\2\2G\f\3\2\2\2HI\7.\2\2I\16\3\2\2\2JK\7=\2\2K\20\3\2"+
		"\2\2LM\7}\2\2M\22\3\2\2\2NO\7\177\2\2O\24\3\2\2\2PQ\7/\2\2Q\26\3\2\2\2"+
		"RS\7/\2\2ST\7@\2\2T\30\3\2\2\2UV\5\33\16\2VW\5\13\6\2WX\5\33\16\2XZ\5"+
		"\13\6\2Y[\5\33\16\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\32\3\2"+
		"\2\2^_\t\2\2\2_\34\3\2\2\2`b\7\17\2\2a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd"+
		"\7\f\2\2d\36\3\2\2\2eg\t\3\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2"+
		"ij\3\2\2\2jk\b\20\2\2k \3\2\2\2\6\2\\ah\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
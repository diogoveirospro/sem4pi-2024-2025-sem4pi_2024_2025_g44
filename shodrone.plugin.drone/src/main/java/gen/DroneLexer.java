package gen;
// Generated from DroneLexer.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DroneLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PI", "TYPES", "VARIABLES", "INSTRUCTIONS", "PROGRAMMING", "LANGUAGE", 
			"VERSION", "TAKEOFF", "LAND", "MOVE", "MOVEPATH", "MOVECIRCLE", "HOOVER", 
			"LIGHTSON", "LIGHTSOFF", "BLINK", "DOT", "COMMA", "SEMICOLON", "LPAREN", 
			"RPAREN", "ASSIGN", "ADD", "DASH", "MUL", "DIV", "LT", "GT", "TYPE_NAME", 
			"IDENTIFIER", "FLOAT", "INTEGER", "WS", "NEWLINE"
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


	public DroneLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DroneLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u0143\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\5\36\u011f\n\36\3\37\3\37\7\37\u0123\n\37\f\37\16"+
		"\37\u0126\13\37\3 \6 \u0129\n \r \16 \u012a\3 \3 \6 \u012f\n \r \16 \u0130"+
		"\3!\6!\u0134\n!\r!\16!\u0135\3\"\6\"\u0139\n\"\r\"\16\"\u013a\3\"\3\""+
		"\3#\6#\u0140\n#\r#\16#\u0141\2\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$\3\2\7\4\2C\\c|\6\2\62;C\\"+
		"aac|\3\2\62;\4\2\13\13\"\"\4\2\f\f\17\17\2\u014e\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5J\3\2\2\2\7P\3\2\2"+
		"\2\tZ\3\2\2\2\13g\3\2\2\2\rs\3\2\2\2\17|\3\2\2\2\21\u0084\3\2\2\2\23\u008c"+
		"\3\2\2\2\25\u0091\3\2\2\2\27\u0096\3\2\2\2\31\u009f\3\2\2\2\33\u00aa\3"+
		"\2\2\2\35\u00b1\3\2\2\2\37\u00ba\3\2\2\2!\u00c4\3\2\2\2#\u00ca\3\2\2\2"+
		"%\u00cc\3\2\2\2\'\u00ce\3\2\2\2)\u00d0\3\2\2\2+\u00d2\3\2\2\2-\u00d4\3"+
		"\2\2\2/\u00d6\3\2\2\2\61\u00d8\3\2\2\2\63\u00da\3\2\2\2\65\u00dc\3\2\2"+
		"\2\67\u00de\3\2\2\29\u00e0\3\2\2\2;\u011e\3\2\2\2=\u0120\3\2\2\2?\u0128"+
		"\3\2\2\2A\u0133\3\2\2\2C\u0138\3\2\2\2E\u013f\3\2\2\2GH\7R\2\2HI\7K\2"+
		"\2I\4\3\2\2\2JK\7V\2\2KL\7{\2\2LM\7r\2\2MN\7g\2\2NO\7u\2\2O\6\3\2\2\2"+
		"PQ\7X\2\2QR\7c\2\2RS\7t\2\2ST\7k\2\2TU\7c\2\2UV\7d\2\2VW\7n\2\2WX\7g\2"+
		"\2XY\7u\2\2Y\b\3\2\2\2Z[\7K\2\2[\\\7p\2\2\\]\7u\2\2]^\7v\2\2^_\7t\2\2"+
		"_`\7w\2\2`a\7e\2\2ab\7v\2\2bc\7k\2\2cd\7q\2\2de\7p\2\2ef\7u\2\2f\n\3\2"+
		"\2\2gh\7r\2\2hi\7t\2\2ij\7q\2\2jk\7i\2\2kl\7t\2\2lm\7c\2\2mn\7o\2\2no"+
		"\7o\2\2op\7k\2\2pq\7p\2\2qr\7i\2\2r\f\3\2\2\2st\7n\2\2tu\7c\2\2uv\7p\2"+
		"\2vw\7i\2\2wx\7w\2\2xy\7c\2\2yz\7i\2\2z{\7g\2\2{\16\3\2\2\2|}\7x\2\2}"+
		"~\7g\2\2~\177\7t\2\2\177\u0080\7u\2\2\u0080\u0081\7k\2\2\u0081\u0082\7"+
		"q\2\2\u0082\u0083\7p\2\2\u0083\20\3\2\2\2\u0084\u0085\7v\2\2\u0085\u0086"+
		"\7c\2\2\u0086\u0087\7m\2\2\u0087\u0088\7g\2\2\u0088\u0089\7Q\2\2\u0089"+
		"\u008a\7h\2\2\u008a\u008b\7h\2\2\u008b\22\3\2\2\2\u008c\u008d\7n\2\2\u008d"+
		"\u008e\7c\2\2\u008e\u008f\7p\2\2\u008f\u0090\7f\2\2\u0090\24\3\2\2\2\u0091"+
		"\u0092\7o\2\2\u0092\u0093\7q\2\2\u0093\u0094\7x\2\2\u0094\u0095\7g\2\2"+
		"\u0095\26\3\2\2\2\u0096\u0097\7o\2\2\u0097\u0098\7q\2\2\u0098\u0099\7"+
		"x\2\2\u0099\u009a\7g\2\2\u009a\u009b\7R\2\2\u009b\u009c\7c\2\2\u009c\u009d"+
		"\7v\2\2\u009d\u009e\7j\2\2\u009e\30\3\2\2\2\u009f\u00a0\7o\2\2\u00a0\u00a1"+
		"\7q\2\2\u00a1\u00a2\7x\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7E\2\2\u00a4"+
		"\u00a5\7k\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7e\2\2\u00a7\u00a8\7n\2\2"+
		"\u00a8\u00a9\7g\2\2\u00a9\32\3\2\2\2\u00aa\u00ab\7j\2\2\u00ab\u00ac\7"+
		"q\2\2\u00ac\u00ad\7q\2\2\u00ad\u00ae\7x\2\2\u00ae\u00af\7g\2\2\u00af\u00b0"+
		"\7t\2\2\u00b0\34\3\2\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4"+
		"\7i\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7u\2\2\u00b7"+
		"\u00b8\7Q\2\2\u00b8\u00b9\7p\2\2\u00b9\36\3\2\2\2\u00ba\u00bb\7n\2\2\u00bb"+
		"\u00bc\7k\2\2\u00bc\u00bd\7i\2\2\u00bd\u00be\7j\2\2\u00be\u00bf\7v\2\2"+
		"\u00bf\u00c0\7u\2\2\u00c0\u00c1\7Q\2\2\u00c1\u00c2\7h\2\2\u00c2\u00c3"+
		"\7h\2\2\u00c3 \3\2\2\2\u00c4\u00c5\7d\2\2\u00c5\u00c6\7n\2\2\u00c6\u00c7"+
		"\7k\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7m\2\2\u00c9\"\3\2\2\2\u00ca\u00cb"+
		"\7\60\2\2\u00cb$\3\2\2\2\u00cc\u00cd\7.\2\2\u00cd&\3\2\2\2\u00ce\u00cf"+
		"\7=\2\2\u00cf(\3\2\2\2\u00d0\u00d1\7*\2\2\u00d1*\3\2\2\2\u00d2\u00d3\7"+
		"+\2\2\u00d3,\3\2\2\2\u00d4\u00d5\7?\2\2\u00d5.\3\2\2\2\u00d6\u00d7\7-"+
		"\2\2\u00d7\60\3\2\2\2\u00d8\u00d9\7/\2\2\u00d9\62\3\2\2\2\u00da\u00db"+
		"\7,\2\2\u00db\64\3\2\2\2\u00dc\u00dd\7\61\2\2\u00dd\66\3\2\2\2\u00de\u00df"+
		"\7>\2\2\u00df8\3\2\2\2\u00e0\u00e1\7@\2\2\u00e1:\3\2\2\2\u00e2\u00e3\7"+
		"R\2\2\u00e3\u00e4\7q\2\2\u00e4\u00e5\7u\2\2\u00e5\u00e6\7k\2\2\u00e6\u00e7"+
		"\7v\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7q\2\2\u00e9\u011f\7p\2\2\u00ea"+
		"\u00eb\7R\2\2\u00eb\u00ec\7q\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7p\2\2"+
		"\u00ee\u011f\7v\2\2\u00ef\u00f0\7X\2\2\u00f0\u00f1\7g\2\2\u00f1\u00f2"+
		"\7e\2\2\u00f2\u00f3\7v\2\2\u00f3\u00f4\7q\2\2\u00f4\u011f\7t\2\2\u00f5"+
		"\u00f6\7N\2\2\u00f6\u00f7\7k\2\2\u00f7\u00f8\7p\2\2\u00f8\u00f9\7g\2\2"+
		"\u00f9\u00fa\7c\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7X\2\2\u00fc\u00fd"+
		"\7g\2\2\u00fd\u00fe\7n\2\2\u00fe\u00ff\7q\2\2\u00ff\u0100\7e\2\2\u0100"+
		"\u0101\7k\2\2\u0101\u0102\7v\2\2\u0102\u011f\7{\2\2\u0103\u0104\7C\2\2"+
		"\u0104\u0105\7p\2\2\u0105\u0106\7i\2\2\u0106\u0107\7w\2\2\u0107\u0108"+
		"\7n\2\2\u0108\u0109\7c\2\2\u0109\u010a\7t\2\2\u010a\u010b\7X\2\2\u010b"+
		"\u010c\7g\2\2\u010c\u010d\7n\2\2\u010d\u010e\7q\2\2\u010e\u010f\7e\2\2"+
		"\u010f\u0110\7k\2\2\u0110\u0111\7v\2\2\u0111\u011f\7{\2\2\u0112\u0113"+
		"\7F\2\2\u0113\u0114\7k\2\2\u0114\u0115\7u\2\2\u0115\u0116\7v\2\2\u0116"+
		"\u0117\7c\2\2\u0117\u0118\7p\2\2\u0118\u0119\7e\2\2\u0119\u011f\7g\2\2"+
		"\u011a\u011b\7V\2\2\u011b\u011c\7k\2\2\u011c\u011d\7o\2\2\u011d\u011f"+
		"\7g\2\2\u011e\u00e2\3\2\2\2\u011e\u00ea\3\2\2\2\u011e\u00ef\3\2\2\2\u011e"+
		"\u00f5\3\2\2\2\u011e\u0103\3\2\2\2\u011e\u0112\3\2\2\2\u011e\u011a\3\2"+
		"\2\2\u011f<\3\2\2\2\u0120\u0124\t\2\2\2\u0121\u0123\t\3\2\2\u0122\u0121"+
		"\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		">\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0129\t\4\2\2\u0128\u0127\3\2\2\2"+
		"\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c"+
		"\3\2\2\2\u012c\u012e\7\60\2\2\u012d\u012f\t\4\2\2\u012e\u012d\3\2\2\2"+
		"\u012f\u0130\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131@\3"+
		"\2\2\2\u0132\u0134\t\4\2\2\u0133\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135"+
		"\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136B\3\2\2\2\u0137\u0139\t\5\2\2"+
		"\u0138\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b"+
		"\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\b\"\2\2\u013dD\3\2\2\2\u013e"+
		"\u0140\t\6\2\2\u013f\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u013f\3\2"+
		"\2\2\u0141\u0142\3\2\2\2\u0142F\3\2\2\2\n\2\u011e\u0124\u012a\u0130\u0135"+
		"\u013a\u0141\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
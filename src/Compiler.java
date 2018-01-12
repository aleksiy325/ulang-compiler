import org.antlr.runtime.*;
import java.io.*;

public class Compiler {
	public static void main (String[] args) throws Exception {
		ANTLRInputStream input;

		if (args.length == 0 ) {
			System.out.println("Usage: Test filename.ul");
			return;
		}
		else {
			input = new ANTLRInputStream(new FileInputStream(args[0]));
		}

		// The name of the grammar here is "ulNoActions",
		// so ANTLR generates ulNoActionsLexer and ulNoActionsParser
		ulangLexer lexer = new ulangLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ulangParser parser = new ulangParser(tokens);

		try {
			parser.program();
		}
		catch (RecognitionException e )	{
			// A lexical or parsing error occured.
			// ANTLR will have already printed information on the
			// console due to code added to the grammar.  So there is
			// nothing to do here.
			System.exit(1);
		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(1);
		}
	}
}

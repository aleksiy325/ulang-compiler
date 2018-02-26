import org.antlr.runtime.*;
import java.util.List;
import java.io.*;


public class Compiler {
    public static void main (String[] args) throws Exception {
        ANTLRInputStream input;
        FileInputStream filestream;

        if (args.length == 0 ) {
            System.out.println("Usage: Test filename.ul");
            return;
        } else {
            filestream = new FileInputStream(args[0]);
            input = new ANTLRInputStream(filestream);
        }

        // The name of the grammar here is "ulNoActions",
        // so ANTLR generates ulNoActionsLexer and ulNoActionsParser
        ulangLexer lexer = new ulangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ulangParser parser = new ulangParser(tokens);
        filestream = new FileInputStream(args[0]);
        InputStreamReader inputstream = new InputStreamReader(filestream);
        TypeChecker visitor = new TypeChecker(inputstream);

        try {
            Program p = parser.program();
            p.accept(visitor);
        } catch (RecognitionException e )   {
            // A lexical or parsing error occured.
            // ANTLR will have already printed information on the
            // console due to code added to the grammar.  So there is
            // nothing to do here.
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}

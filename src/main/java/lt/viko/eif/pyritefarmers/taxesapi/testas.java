package lt.viko.eif.pyritefarmers.taxesapi;

import lt.viko.eif.pyritefarmers.taxesapi.APIs.SkyScanner;
import lt.viko.eif.pyritefarmers.taxesapi.models.Options;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class testas {
    public static void main(String[] args) throws IOException, ParseException {
        SkyScanner.getBrowseQuotesResult(new Options(true));
    }
}

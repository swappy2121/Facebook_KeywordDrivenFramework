package keyworddriventest;

import keywordengine.Keyword_Driven;
import org.testng.annotations.Test;

public class Execution_keywordDriven {
    public Keyword_Driven keywordEngine;

    @Test
    public void loginTestWithKeywordDriven() {
        keywordEngine = new Keyword_Driven();
        keywordEngine.startExecution("login");
    }
}

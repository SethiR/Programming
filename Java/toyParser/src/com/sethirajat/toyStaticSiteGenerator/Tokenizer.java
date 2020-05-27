package com.sethirajat.toyStaticSiteGenerator;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class Tokenizer {

    private class TokenInfo{
        public Pattern regex;
        public int token;

        public TokenInfo(Pattern regex, int token) {
            this.regex = regex;
            this.token = token;
        }
    }

    private LinkedList<TokenInfo> tokenInfos;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
    }

    public void add(String regex, int token){
        tokenInfos.add(
                new TokenInfo(Pattern.compile("^("+regex+")"), token)
        );
    }
}

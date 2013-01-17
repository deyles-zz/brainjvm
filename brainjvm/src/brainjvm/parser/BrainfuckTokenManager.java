/**
 * Copyright (c) 2013, Dan Eyles (dan@irlgaming.com)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of IRL Gaming nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL IRL Gaming BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package brainjvm.parser;

import brainjvm.parser.BrainfuckToken;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * A simple management interface to Brainfuck tokens
 * @class BrainfuckTokenManager
 * @implements ListIterator
 * @author dan
 */
public class BrainfuckTokenManager
implements ListIterator<BrainfuckToken> {
    
    /**
     * @type ArrayList<BrainfuckToken>
     */
    private ArrayList<BrainfuckToken> tokens;
    
    /**
     * @type integer
     */
    private int pointer = 0;
    
    /**
     * @constructor
     */
    public BrainfuckTokenManager() {
        tokens = new ArrayList<BrainfuckToken>();
    }
    
    /**
     * Returns the tokens encapsulated by the BrainfuckTokenManager instance
     * @return ArrayList<BrainfuckToken>
     */
    public ArrayList<BrainfuckToken> getTokens() {
        return tokens;
    }
    
    /**
     * Adds a token to the token manager
     * @param t char the token to add
     */
    public void consume(char t) {
        BrainfuckToken token = new BrainfuckToken(t);
        add(token);
    }
    
    /**
     * Functions below satisfy the ListIterator interface contract
     * @see ListIterator
     */
    
    public boolean hasNext() {
        return pointer < tokens.size();
    }
    
    public boolean hasPrevious() {
        return pointer > 0;
    }
    
    public BrainfuckToken next() {
        return tokens.get(pointer++);
    }
    
    public int nextIndex() {
        return pointer;
    }
    
    public BrainfuckToken previous() {
        return tokens.get(--pointer);
    }
    
    public int previousIndex() {
        return pointer - 1;
    }
    
    public void remove() {

    }
    
    public void set(BrainfuckToken token) {
        
    }
    
    public void add(BrainfuckToken token) {
        if(tokens.size() > 0) {
            BrainfuckToken prev = tokens.get(tokens.size() - 1);
            token.setPreviousToken(prev);
            prev.setNextToken(token);
        }
        tokens.add(token);        
    }
    
}
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

/**
 * A class representing a Brainfuck program token
 * @class BrainfuckToken
 * @author dan
 */
public class BrainfuckToken {

    /**
     * @type char
     */
    public static final char TOKEN_POINTER_ADVANCE = '>';
    public static final char TOKEN_POINTER_RETREAT = '<';
    public static final char TOKEN_HEAP_INCREMENT  = '+';
    public static final char TOKEN_HEAP_DECREMENT  = '-';
    public static final char TOKEN_STDOUT_WRITE    = '.';
    public static final char TOKEN_STDIN_READ      = ',';
    public static final char TOKEN_LOOP_OPEN       = '[';
    public static final char TOKEN_LOOP_CLOSE      = ']';
    
    /**
     * @type integer
     */
    public static final int TYPE_UNKNOWN           = -1;
    public static final int TYPE_POINTER_ADVANCE   = 0;
    public static final int TYPE_POINTER_RETREAT   = 1;
    public static final int TYPE_HEAP_INCREMENT    = 2;
    public static final int TYPE_HEAP_DECREMENT    = 3;
    public static final int TYPE_STDOUT_WRITE      = 4;
    public static final int TYPE_STDIN_READ        = 5;
    public static final int TYPE_LOOP_OPEN         = 6;
    public static final int TYPE_LOOP_CLOSE        = 7;
    
    /**
     * Returns an integer value representing the token type for the provided char
     * @param t char the character to determine the token type for
     * @return integer
     */
    public static int determineTypeCode(char t) {
        switch(t) {
            case BrainfuckToken.TOKEN_POINTER_ADVANCE:
                return BrainfuckToken.TYPE_POINTER_ADVANCE;
                
            case BrainfuckToken.TOKEN_POINTER_RETREAT:
                return BrainfuckToken.TYPE_POINTER_RETREAT;
                
            case BrainfuckToken.TOKEN_HEAP_INCREMENT:
                return BrainfuckToken.TYPE_HEAP_INCREMENT;
                
            case BrainfuckToken.TOKEN_HEAP_DECREMENT:
                return BrainfuckToken.TYPE_HEAP_DECREMENT;
                
            case BrainfuckToken.TOKEN_STDOUT_WRITE:
                return BrainfuckToken.TYPE_STDOUT_WRITE;
                
            case BrainfuckToken.TOKEN_STDIN_READ:
                return BrainfuckToken.TYPE_STDIN_READ;
                
            case BrainfuckToken.TOKEN_LOOP_OPEN:
                return BrainfuckToken.TYPE_LOOP_OPEN;
                
            case BrainfuckToken.TOKEN_LOOP_CLOSE:
                return BrainfuckToken.TYPE_LOOP_CLOSE;
        }
        return BrainfuckToken.TYPE_UNKNOWN;
    }
    
    private char token;
    private int  type;
    
    private BrainfuckToken prev;
    private BrainfuckToken next;
    
    /**
     * @constructor
     * @param ntoken the token char to encapsulate 
     */
    public BrainfuckToken(char ntoken) {
        token = ntoken;
        type  = BrainfuckToken.determineTypeCode(ntoken);
    }
    
    /**
     * Returns the token encapsulated by the BrainfuckToken instance
     * @return char
     */
    public char getToken() {
        return token;
    }
    
    /**
     * Returns the integer type code for the BrainfuckToken instance
     * @return integer
     */
    public int getType() {
        return type;
    }
    
    /**
     * Returns the previous token in the stream, null if this is the first token
     * @return BrainfuckToken|null
     */
    public BrainfuckToken getPreviousToken() {
        return prev;
    }
    
    /**
     * Sets the previous token in the stream
     * @param token the previous token
     */
    public void setPreviousToken(BrainfuckToken token) {
        prev = token;
    }
    
    /**
     * Returns the next token in the stream, null of this is the last token
     * @return BrainfuckToken|null
     */
    public BrainfuckToken getNextToken() {
        return next;
    }
    
    /**
     * Sets the next token in the stream
     * @param token the next token in the stream
     */
    public void setNextToken(BrainfuckToken token) {
        next = token;
    }
    
}